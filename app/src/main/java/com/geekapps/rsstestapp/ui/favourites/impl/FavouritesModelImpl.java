package com.geekapps.rsstestapp.ui.favourites.impl;

import android.content.Context;

import com.geekapps.rsstestapp.data.db.categories.AudiobooksTableHelperImpl;
import com.geekapps.rsstestapp.data.db.categories.CategoryTableHelper;
import com.geekapps.rsstestapp.data.db.categories.MoviesTableHelperImpl;
import com.geekapps.rsstestapp.data.db.categories.PodcastsTableHelperImpl;
import com.geekapps.rsstestapp.data.network.RssTestAppClient;
import com.geekapps.rsstestapp.data.network.RssTestAppService;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;
import com.geekapps.rsstestapp.data.network.pojo.favourites.FavouritesListItem;
import com.geekapps.rsstestapp.mvp.BaseMvpModel;
import com.geekapps.rsstestapp.ui.favourites.FavouritesModel;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class FavouritesModelImpl extends BaseMvpModel implements FavouritesModel {
    private CategoryTableHelper audiobooksTableHelper;
    private CategoryTableHelper moviesTableHelper;
    private CategoryTableHelper podcastsTableHelper;

    public FavouritesModelImpl(Context context) {
        audiobooksTableHelper = new AudiobooksTableHelperImpl(context);
        moviesTableHelper = new MoviesTableHelperImpl(context);
        podcastsTableHelper = new PodcastsTableHelperImpl(context);
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
