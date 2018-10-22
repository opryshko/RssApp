package com.geekapps.rsstestapp.ui.categories.podcasts.impl;

import com.geekapps.rsstestapp.R;
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
        this.model = new PodcastsModelImpl(view.getContext());
    }

    public void loadTop25Podcasts() {
        view.hideReloadDataView();
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
