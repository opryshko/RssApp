package com.geekapps.rsstestapp.ui.audiobooks;

import com.geekapps.rsstestapp.data.network.pojo.MediaContent;

import io.reactivex.Observable;

public interface AudiobooksModel {

    Observable<MediaContent> getTop25Audiobooks();
}
