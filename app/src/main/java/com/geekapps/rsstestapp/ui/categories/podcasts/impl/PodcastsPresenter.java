package com.geekapps.rsstestapp.ui.categories.podcasts.impl;

import com.geekapps.rsstestapp.R;
import com.geekapps.rsstestapp.data.db.categories.PodcastsTableHelperImpl;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;
import com.geekapps.rsstestapp.ui.categories.BaseCategoryPresenter;
import com.geekapps.rsstestapp.ui.categories.podcasts.PodcastsModel;
import com.geekapps.rsstestapp.ui.categories.podcasts.PodcastsView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PodcastsPresenter extends BaseCategoryPresenter {
    private PodcastsView view;
    private PodcastsModel model;

    public PodcastsPresenter(PodcastsView view) {
        super(view);
        this.view = view;
        this.model = new PodcastsModelImpl();
        this.categoryTableHelper = new PodcastsTableHelperImpl(view.getContext());
    }

    public void getTop25Podcasts() {
        view.showLoading();
        model.getTop25Podcasts().subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleGetTop25PodcastsResponse, this::loadDataFromDb);

    }

    private void handleGetTop25PodcastsResponse(MediaContent mediaContent) {
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
        callBack.onUpdateFavourites();
    }

    private void loadDataFromDb(Throwable throwable) {
        if (categoryTableHelper.getMediasCount() > 0) {
            view.initRecyclerView(categoryTableHelper.getAllMedias());
            view.hideLoading();
        }
    }
}
