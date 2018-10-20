package com.geekapps.rsstestapp.data.db.categories.movies;

import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;

import java.util.List;

public interface MoviesTableHelper {
    void addMovieItem(MediaItem movie);

    void addMovieItems(List<MediaItem> movies);

    MediaItem getMovie(int id);

    List<MediaItem> getAllMovies();

    List<MediaItem> getFavouriteMovies();

    long getMoviesCount();

    int updateMovie(MediaItem movie);

    void updateAllMovies(List<MediaItem> movies);

    void deleteMovie(MediaItem movie);

    void deleteAllMovie();
}
