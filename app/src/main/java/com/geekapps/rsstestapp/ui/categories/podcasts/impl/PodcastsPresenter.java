package com.geekapps.rsstestapp.ui.categories.podcasts.impl;

import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;
import com.geekapps.rsstestapp.mvp.BaseMvpPresenter;
import com.geekapps.rsstestapp.ui.categories.podcasts.PodcastsModel;
import com.geekapps.rsstestapp.ui.categories.podcasts.PodcastsView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PodcastsPresenter extends BaseMvpPresenter {
    private PodcastsView view;
    private PodcastsModel model;
    
    public PodcastsPresenter(PodcastsView view) {
        super(view);
        this.view = view;
        this.model = new PodcastsModelImpl();
    }

    public void getTop25Podcasts() {
        view.showLoading();
        model.getTop25Podcasts().subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleGetTop25PodcastsResponse, this::showError);

    }

    private void handleGetTop25PodcastsResponse(MediaContent mediaContent) {
        view.initRecyclerView(mediaContent);
        view.hideLoading();
    }
}
