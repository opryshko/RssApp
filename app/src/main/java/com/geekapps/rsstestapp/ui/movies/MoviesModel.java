package com.geekapps.rsstestapp.ui.movies;

import com.geekapps.rsstestapp.data.network.pojo.MediaContent;

import io.reactivex.Observable;

public interface MoviesModel {

    Observable<MediaContent> getTop25Movies();
}
