package com.geekapps.rsstestapp.data.db.categories;

import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;

import java.util.List;

public interface CategoryTableHelper {
    void addMediaItem(MediaItem media);

    void addMediaItems(List<MediaItem> medias);

    MediaItem getMedia(int id);

    List<MediaItem> getAllMedias();

    List<MediaItem> getFavouriteMedias();

    long getMediasCount();

    int updateMedia(MediaItem media);

    void updateAllMedias(List<MediaItem> medias);

    void deleteMedia(MediaItem media);

    void deleteAllMedias();
}
