package com.geekapps.rsstestapp.ui.favourites.impl;

import com.geekapps.rsstestapp.data.network.RssTestAppClient;
import com.geekapps.rsstestapp.data.network.RssTestAppService;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;
import com.geekapps.rsstestapp.mvp.BaseMvpModel;
import com.geekapps.rsstestapp.ui.favourites.FavouritesModel;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class FavouritesModelImpl extends BaseMvpModel implements FavouritesModel {

}
