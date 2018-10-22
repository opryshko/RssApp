package com.geekapps.rsstestapp.ui.categories.movies.impl;

import android.content.Context;

import com.geekapps.rsstestapp.data.db.categories.CategoryTableAdapter;
import com.geekapps.rsstestapp.data.db.categories.MoviesTableAdapterImpl;
import com.geekapps.rsstestapp.data.network.RssTestAppClient;
import com.geekapps.rsstestapp.data.network.RssTestAppService;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;
import com.geekapps.rsstestapp.mvp.BaseMvpModel;
import com.geekapps.rsstestapp.ui.categories.movies.MoviesModel;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class MoviesModelImpl extends BaseMvpModel implements MoviesModel {

    private RssTestAppService api = RssTestAppClient.getClientForCategory().create(RssTestAppService.class);
    private CategoryTableAdapter categoryTableAdapter;

    public MoviesModelImpl(Context context) {
        categoryTableAdapter = new MoviesTableAdapterImpl(context);
    }

    @Override
    public Observable<MediaContent> getTop25Movies() {
        return api.getTop25Movies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::checkForError)
                .map(this::parseGetTop25Movies);
    }

    private MediaContent parseGetTop25Movies(Response<ResponseBody> bodyResponse) throws IOException {
        return new GsonBuilder().create().fromJson(bodyResponse.body().string(), MediaContent.class);
    }

    @Override
    public long getMediasCount() {
        return categoryTableAdapter.getMediasCount();
    }

    @Override
    public List<MediaItem> getAllMedias() {
        return categoryTableAdapter.getAllMedias();
    }

    @Override
    public void updateAllMedias(List<MediaItem> medias) {
        categoryTableAdapter.updateAllMedias(medias);
    }

    @Override
    public void updateMedia(MediaItem media) {
        categoryTableAdapter.updateMedia(media);
    }
}
