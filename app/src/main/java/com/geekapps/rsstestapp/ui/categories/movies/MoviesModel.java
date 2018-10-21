package com.geekapps.rsstestapp.ui.categories.movies;

import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;
import com.geekapps.rsstestapp.ui.categories.CategoryModel;

import io.reactivex.Observable;

public interface MoviesModel extends CategoryModel{

    Observable<MediaContent> getTop25Movies();
}
