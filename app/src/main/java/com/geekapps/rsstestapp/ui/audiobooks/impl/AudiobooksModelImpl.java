package com.geekapps.rsstestapp.ui.audiobooks.impl;

import com.geekapps.rsstestapp.data.network.RssTestAppClient;
import com.geekapps.rsstestapp.data.network.RssTestAppService;
import com.geekapps.rsstestapp.data.network.pojo.MediaContent;
import com.geekapps.rsstestapp.mvp.BaseMvpModel;
import com.geekapps.rsstestapp.ui.audiobooks.AudiobooksModel;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class AudiobooksModelImpl extends BaseMvpModel implements AudiobooksModel {

    private RssTestAppService api = RssTestAppClient.getClient().create(RssTestAppService.class);

    @Override
    public Observable<MediaContent> getTop25Audiobooks() {
        return api.getTop25Audiobooks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::checkForError)
                .map(this::parseGetTop25Audiobooks);
    }

    private MediaContent parseGetTop25Audiobooks(Response<ResponseBody> bodyResponse) throws IOException {
        return new GsonBuilder().create().fromJson(bodyResponse.body().string(), MediaContent.class);
    }
}
