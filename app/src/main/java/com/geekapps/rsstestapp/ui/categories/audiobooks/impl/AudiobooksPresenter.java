package com.geekapps.rsstestapp.ui.categories.audiobooks.impl;

import com.geekapps.rsstestapp.data.db.categories.CategoryTableHelper;
import com.geekapps.rsstestapp.data.db.categories.AudiobooksTableHelperImpl;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;
import com.geekapps.rsstestapp.mvp.BaseMvpPresenter;
import com.geekapps.rsstestapp.ui.categories.audiobooks.AudiobooksModel;
import com.geekapps.rsstestapp.ui.categories.audiobooks.AudiobooksView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AudiobooksPresenter extends BaseMvpPresenter {
    private AudiobooksView view;
    private AudiobooksModel model;
    private CategoryTableHelper categoryTableHelper;

    public AudiobooksPresenter(AudiobooksView view) {
        super(view);
        this.view = view;
        this.model = new AudiobooksModelImpl();
        this.categoryTableHelper = new AudiobooksTableHelperImpl(view.getContext());
    }

    public void getTop25Audiobooks() {
        view.showLoading();
        model.getTop25Audiobooks().subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleGetTop25AudiobooksResponse, this::loadDataFromDb);

    }

    private void handleGetTop25AudiobooksResponse(MediaContent mediaContent) {
        List<MediaItem> medias = getPreparedMediasToDisplay(mediaContent);

        view.initRecyclerView(medias);
        view.hideLoading();
        categoryTableHelper.updateAllMedias(medias);
    }

    public void updateMediaItem(MediaItem media) {
        categoryTableHelper.updateMedia(media);
    }

    private void loadDataFromDb(Throwable throwable) {
        if (categoryTableHelper.getMediasCount() > 0) {
            view.initRecyclerView(categoryTableHelper.getAllMedias());
            view.hideLoading();
            return;
        }
        showError(throwable);
    }

    private List<MediaItem> getMediasWithFavouriteMarks(List<MediaItem> medias) {
        MediaItem localMedia;

        if (categoryTableHelper.getMediasCount() > 0) {
            for (MediaItem media : medias) {
                localMedia = findMedia(categoryTableHelper.getAllMedias(), media.getId());
                if (localMedia != null)
                    media.setFavourite(localMedia.isFavourite());
            }
        }

        return medias;
    }

    private MediaItem findMedia(List<MediaItem> medias, Integer id) {
        for (MediaItem media : medias)
            if (media.getId().equals(id))
                return media;

        return null;
    }

    private List<MediaItem> initMediaPositions(List<MediaItem> medias) {
        for (int i = 0; i < medias.size(); i++)
            medias.get(i).setPosition(i + 1);
        return medias;
    }

    private List<MediaItem> getPreparedMediasToDisplay(MediaContent mediaContent) {
        List<MediaItem> medias = initMediaPositions(mediaContent.getFeed().getResults());

        return getMediasWithFavouriteMarks(medias);

    }
}
