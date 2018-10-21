package com.geekapps.rsstestapp.ui.categories.audiobooks;

import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;
import com.geekapps.rsstestapp.ui.categories.CategoryModel;

import io.reactivex.Observable;

public interface AudiobooksModel extends CategoryModel {

    Observable<MediaContent> getTop25Audiobooks();
}
