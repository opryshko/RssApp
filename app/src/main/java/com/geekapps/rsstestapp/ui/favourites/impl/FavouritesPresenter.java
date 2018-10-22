package com.geekapps.rsstestapp.ui.favourites.impl;

import com.geekapps.rsstestapp.R;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;
import com.geekapps.rsstestapp.mvp.BaseMvpPresenter;
import com.geekapps.rsstestapp.ui.favourites.FavouritesModel;
import com.geekapps.rsstestapp.ui.favourites.FavouritesView;

import java.util.List;

public class FavouritesPresenter extends BaseMvpPresenter {

    private FavouritesView view;
    private FavouritesModel model;

    public FavouritesPresenter(FavouritesView view) {
        super(view);
        this.view = view;
        this.model = new FavouritesModelImpl(view.getContext());
    }

    public void loadFavouriteGroups() {
        addFavouriteGroups();
    }

    public void updateFavouriteGroups() {
        view.clearFavoritesList();
        addFavouriteGroups();
    }

    private void addFavouriteGroups() {
        List<MediaItem> audiobooks = model.getFavouriteAudiobooks();
        List<MediaItem> movies = model.getFavouritesMovies();
        List<MediaItem> podcasts = model.getFavouritesPodcasts();

        if (!audiobooks.isEmpty())
            view.addFavouritesGroup(getStringResource(R.string.audiobooks), audiobooks);
        if (!movies.isEmpty())
            view.addFavouritesGroup(getStringResource(R.string.movies), movies);
        if (!podcasts.isEmpty())
            view.addFavouritesGroup(getStringResource(R.string.podcasts), podcasts);

        if (audiobooks.isEmpty() && movies.isEmpty() && podcasts.isEmpty())
            view.showEmptyListPlaceholder();
        else view.hideEmptyListPlaceholder();
    }
}
