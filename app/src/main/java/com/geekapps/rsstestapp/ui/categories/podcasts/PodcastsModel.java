package com.geekapps.rsstestapp.ui.categories.podcasts;

import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;
import com.geekapps.rsstestapp.ui.categories.CategoryModel;

import io.reactivex.Observable;

public interface PodcastsModel extends CategoryModel{

    Observable<MediaContent> getTop25Podcasts();
}
