package com.geekapps.rsstestapp.ui.movies;

import com.geekapps.rsstestapp.data.network.pojo.Audiobooks;

import io.reactivex.Observable;

public interface MoviesModel {

    Observable<Audiobooks> getTop25Movies();
}
