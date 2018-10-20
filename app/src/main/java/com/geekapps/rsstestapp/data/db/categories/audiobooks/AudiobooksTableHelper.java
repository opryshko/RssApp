package com.geekapps.rsstestapp.data.db.categories.audiobooks;

import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;

import java.util.List;

public interface AudiobooksTableHelper {
    void addAudiobookItem(MediaItem audiobook);

    void addAudiobooktems(List<MediaItem> audiobooks);

    MediaItem getAudiobook(int id);

    List<MediaItem> getAllAudiobooks();

    List<MediaItem> getFavouriteAudiobooks();

    long getAudiobooksCount();

    int updateAudiobook(MediaItem audiobook);

    void updateAllAudiobooks(List<MediaItem> audiobooks);

    void deleteAudiobook(MediaItem audiobook);

    void deleteAllAudiobook();
}
