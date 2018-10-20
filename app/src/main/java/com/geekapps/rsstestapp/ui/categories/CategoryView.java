package com.geekapps.rsstestapp.ui.categories;

import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;
import com.geekapps.rsstestapp.mvp.BaseMvpView;

import java.util.List;

public interface CategoryView extends BaseMvpView {

    void showDetailInformation(Integer id);

    void initRecyclerView(List<MediaItem> medias);

    void updateMediaItem(MediaItem media);
}
