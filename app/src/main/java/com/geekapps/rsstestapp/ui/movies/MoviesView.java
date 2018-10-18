package com.geekapps.rsstestapp.ui.movies;

import com.geekapps.rsstestapp.data.network.pojo.Audiobooks;
import com.geekapps.rsstestapp.mvp.BaseMvpView;

public interface MoviesView extends BaseMvpView {

    void replaceFragment();

    void initRecyclerView(Audiobooks audiobooks);
}
