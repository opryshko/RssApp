package com.geekapps.rsstestapp.ui.categories.podcasts.impl;

import android.content.Context;

import com.geekapps.rsstestapp.data.db.categories.CategoryTableAdapter;
import com.geekapps.rsstestapp.data.db.categories.PodcastsTableAdapterImpl;
import com.geekapps.rsstestapp.data.network.RssTestAppClient;
import com.geekapps.rsstestapp.data.network.RssTestAppService;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;
import com.geekapps.rsstestapp.mvp.BaseMvpModel;
import com.geekapps.rsstestapp.ui.categories.podcasts.PodcastsModel;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class PodcastsModelImpl extends BaseMvpModel implements PodcastsModel {
    private RssTestAppService api = RssTestAppClient.getClientForCategory().create(RssTestAppService.class);
    private CategoryTableAdapter categoryTableAdapter;

    public PodcastsModelImpl(Context context) {
        categoryTableAdapter = new PodcastsTableAdapterImpl(context);
    }

    @Override
    public Observable<MediaContent> getTop25Podcasts() {
        return api.getTop25Podcasts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::checkForError)
                .map(this::parseGetTop25Podcasts);
    }

    private MediaContent parseGetTop25Podcasts(Response<ResponseBody> bodyResponse) throws IOException {
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
