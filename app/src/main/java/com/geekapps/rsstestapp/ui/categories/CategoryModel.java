package com.geekapps.rsstestapp.ui.categories;

import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;

import java.util.List;

public interface CategoryModel {

    long getMediasCount();

    List<MediaItem> getAllMedias();

    void updateAllMedias(List<MediaItem> medias);

    void updateMedia(MediaItem media);
}
