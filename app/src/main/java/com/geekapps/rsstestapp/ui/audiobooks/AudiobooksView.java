package com.geekapps.rsstestapp.ui.audiobooks;

import com.geekapps.rsstestapp.data.network.pojo.MediaContent;
import com.geekapps.rsstestapp.mvp.BaseMvpView;

public interface AudiobooksView extends BaseMvpView {

    void replaceFragment();

    void initRecyclerView(MediaContent mediaContent);
}
