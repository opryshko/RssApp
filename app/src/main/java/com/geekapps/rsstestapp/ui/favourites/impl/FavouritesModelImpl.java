package com.geekapps.rsstestapp.ui.favourites.impl;

import android.content.Context;

import com.geekapps.rsstestapp.data.db.categories.AudiobooksTableAdapterImpl;
import com.geekapps.rsstestapp.data.db.categories.CategoryTableAdapter;
import com.geekapps.rsstestapp.data.db.categories.MoviesTableAdapterImpl;
import com.geekapps.rsstestapp.data.db.categories.PodcastsTableAdapterImpl;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;
import com.geekapps.rsstestapp.mvp.BaseMvpModel;
import com.geekapps.rsstestapp.ui.favourites.FavouritesModel;

import java.util.List;

public class FavouritesModelImpl extends BaseMvpModel implements FavouritesModel {
    private CategoryTableAdapter audiobooksTableHelper;
    private CategoryTableAdapter moviesTableHelper;
    private CategoryTableAdapter podcastsTableHelper;

    public FavouritesModelImpl(Context context) {
        audiobooksTableHelper = new AudiobooksTableAdapterImpl(context);
        moviesTableHelper = new MoviesTableAdapterImpl(context);
        podcastsTableHelper = new PodcastsTableAdapterImpl(context);
    }

    @Override
    public List<MediaItem> getFavouriteAudiobooks() {
        return audiobooksTableHelper.getFavouriteMedias();
    }

    @Override
    public List<MediaItem> getFavouritesMovies() {
        return moviesTableHelper.getFavouriteMedias();
    }

    @Override
    public List<MediaItem> getFavouritesPodcasts() {
        return podcastsTableHelper.getFavouriteMedias();
    }
}
