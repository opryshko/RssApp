package com.geekapps.rsstestapp.ui.categories.audiobooks.impl;

import com.geekapps.rsstestapp.R;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;
import com.geekapps.rsstestapp.ui.categories.BaseCategoryPresenter;
import com.geekapps.rsstestapp.ui.categories.audiobooks.AudiobooksModel;
import com.geekapps.rsstestapp.ui.categories.audiobooks.AudiobooksView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AudiobooksPresenter extends BaseCategoryPresenter {
    private AudiobooksView view;
    private AudiobooksModel model;

    public AudiobooksPresenter(AudiobooksView view) {
        super(view);
        this.view = view;
        this.model = new AudiobooksModelImpl(view.getContext());
    }

    public void loadTop25Audiobooks() {
        view.hideReloadDataView();
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
        model.updateAllMedias(medias);
    }

    public void updateMediaItem(MediaItem media) {
        model.updateMedia(media);
        if (media.isFavourite())
            view.showMessage(getStringResource(R.string.add_to_favourites));
        else
            view.showMessage(getStringResource(R.string.remove_from_favourites));
        callBack.onUpdateFavourites();
    }

    private void loadDataFromDb(Throwable throwable) {
        if (model.getMediasCount() > 0) {
            view.initRecyclerView(model.getAllMedias());
            view.hideLoading();
            return;
        }
        showError(throwable);
        view.showReloadDataView();
    }

    private List<MediaItem> getMediasWithFavouriteMarks(List<MediaItem> medias) {
        MediaItem localMedia;

        if (model.getMediasCount() > 0) {
            for (MediaItem media : medias) {
                localMedia = findMedia(model.getAllMedias(), media.getId());
                if (localMedia != null)
                    media.setFavourite(localMedia.isFavourite());
            }
        }

        return medias;
    }

    private List<MediaItem> getPreparedMediasToDisplay(MediaContent mediaContent) {
        List<MediaItem> medias = initMediaPositions(mediaContent.getFeed().getResults());

        return getMediasWithFavouriteMarks(medias);
    }
}
