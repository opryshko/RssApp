package com.geekapps.rsstestapp.ui.categories.movies;

import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;

import io.reactivex.Observable;

public interface MoviesModel {

    Observable<MediaContent> getTop25Movies();
}
