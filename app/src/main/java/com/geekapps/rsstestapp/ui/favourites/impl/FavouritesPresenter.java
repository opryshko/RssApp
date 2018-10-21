package com.geekapps.rsstestapp.ui.favourites.impl;

import com.geekapps.rsstestapp.R;
import com.geekapps.rsstestapp.data.db.categories.AudiobooksTableHelperImpl;
import com.geekapps.rsstestapp.data.db.categories.CategoryTableHelper;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;
import com.geekapps.rsstestapp.mvp.BaseMvpPresenter;
import com.geekapps.rsstestapp.mvp.BaseMvpView;
import com.geekapps.rsstestapp.ui.categories.BaseCategoryPresenter;
import com.geekapps.rsstestapp.ui.favourites.FavouritesModel;
import com.geekapps.rsstestapp.ui.favourites.FavouritesView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FavouritesPresenter extends BaseMvpPresenter {

    public FavouritesPresenter(BaseMvpView view) {
        super(view);
    }
}
