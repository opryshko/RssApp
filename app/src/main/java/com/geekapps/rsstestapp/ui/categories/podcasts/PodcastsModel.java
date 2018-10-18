package com.geekapps.rsstestapp.ui.categories.podcasts;

import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;

import io.reactivex.Observable;

public interface PodcastsModel {

    Observable<MediaContent> getTop25Podcasts();
}
