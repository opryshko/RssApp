package com.geekapps.rsstestapp.ui.categories.audiobooks;

import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;

import io.reactivex.Observable;

public interface AudiobooksModel {

    Observable<MediaContent> getTop25Audiobooks();
}
