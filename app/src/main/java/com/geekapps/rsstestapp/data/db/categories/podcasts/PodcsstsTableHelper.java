package com.geekapps.rsstestapp.data.db.categories.podcasts;

import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;

import java.util.List;

public interface PodcsstsTableHelper {
    void addPodcastItem(MediaItem podcast);

    void addPodcastItems(List<MediaItem> podcasts);

    MediaItem getPodcast(int id);

    List<MediaItem> getAllPodcasts();

    List<MediaItem> getFavouritePodcasts();

    long getPodcastsCount();

    int updatePodcast(MediaItem podcast);

    void updateAllPodcasts(List<MediaItem> podcasts);

    void deletePodcast(MediaItem podcast);

    void deleteAllPodcast();
}
