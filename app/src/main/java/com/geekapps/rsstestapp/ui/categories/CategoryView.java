package com.geekapps.rsstestapp.ui.categories;

import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;
import com.geekapps.rsstestapp.mvp.BaseMvpView;

public interface CategoryView extends BaseMvpView {

    void replaceFragment();

    void initRecyclerView(MediaContent mediaContent);
}
