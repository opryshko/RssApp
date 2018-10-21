package com.geekapps.rsstestapp.ui.favourites;

import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;

import java.util.List;

public interface FavouritesModel {
    List<MediaItem> getFavouriteAudiobooks();

    List<MediaItem> getFavouritesMovies();

    List<MediaItem> getFavouritesPodcasts();

}
