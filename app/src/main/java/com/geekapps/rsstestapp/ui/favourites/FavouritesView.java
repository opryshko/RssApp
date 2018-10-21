package com.geekapps.rsstestapp.ui.favourites;

import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;
import com.geekapps.rsstestapp.data.network.pojo.favourites.FavouritesListItem;
import com.geekapps.rsstestapp.mvp.BaseMvpView;

import java.util.List;

public interface FavouritesView extends BaseMvpView {
    void showDetailInformation(Integer id);

    void initRecyclerView();

    void addFavouritesGroup(String title, List<MediaItem> medias);

    void clearFavoritesList();

    void showEmptyListPlaceholder();

    void hideEmptyListPlaceholder();
}
