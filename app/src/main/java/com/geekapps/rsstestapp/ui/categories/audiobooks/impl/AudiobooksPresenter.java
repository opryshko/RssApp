package com.geekapps.rsstestapp.ui.categories.audiobooks.impl;

import com.geekapps.rsstestapp.R;
import com.geekapps.rsstestapp.data.db.categories.AudiobooksTableHelperImpl;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;
import com.geekapps.rsstestapp.ui.categories.BaseCategoryPresenter;
import com.geekapps.rsstestapp.ui.categories.audiobooks.AudiobooksModel;
import com.geekapps.rsstestapp.ui.categories.audiobooks.AudiobooksView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AudiobooksPresenter extends BaseCategoryPresenter {
    protected AudiobooksView view;
    protected AudiobooksModel model;

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
        if (media.isFavourite())
            view.showMessage(getStringResource(R.string.add_to_favourites));
        else
            view.showMessage(getStringResource(R.string.remove_from_favourites));
    }

    private void loadDataFromDb(Throwable throwable) {
        if (categoryTableHelper.getMediasCount() > 0) {
            view.initRecyclerView(categoryTableHelper.getAllMedias());
            view.hideLoading();
            return;
        }
        showError(throwable);
    }
}
