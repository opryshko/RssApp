package com.geekapps.rsstestapp.ui.categories.movies.impl;

import com.geekapps.rsstestapp.data.db.categories.MoviesTableHelperImpl;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;
import com.geekapps.rsstestapp.mvp.BaseMvpPresenter;
import com.geekapps.rsstestapp.ui.categories.BaseCategoryPresenter;
import com.geekapps.rsstestapp.ui.categories.movies.MoviesModel;
import com.geekapps.rsstestapp.ui.categories.movies.MoviesView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MoviesPresenter extends BaseCategoryPresenter {
    private MoviesView view;
    private MoviesModel model;

    public MoviesPresenter(MoviesView view) {
        super(view);
        this.view = view;
        this.model = new MoviesModelImpl();
        this.categoryTableHelper = new MoviesTableHelperImpl(view.getContext());
    }

    public void getTop25Movies() {
        view.showLoading();
        model.getTop25Movies().subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleGetTop25MoviesResponse, this::loadDataFromDb);

    }

    private void handleGetTop25MoviesResponse(MediaContent mediaContent) {
        List<MediaItem> medias = getPreparedMediasToDisplay(mediaContent);

        view.initRecyclerView(medias);
        view.hideLoading();
        categoryTableHelper.updateAllMedias(medias);
    }

    private void loadDataFromDb(Throwable throwable) {
        if (categoryTableHelper.getMediasCount() > 0) {
            view.initRecyclerView(categoryTableHelper.getAllMedias());
            view.hideLoading();
        }
    }
}
