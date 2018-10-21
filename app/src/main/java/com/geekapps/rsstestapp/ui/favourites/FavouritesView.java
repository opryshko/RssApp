package com.geekapps.rsstestapp.ui.favourites;

import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;
import com.geekapps.rsstestapp.mvp.BaseMvpView;

import java.util.List;

public interface FavouritesView extends BaseMvpView{
    void initRecyclerView(List<MediaItem> medias);
}
