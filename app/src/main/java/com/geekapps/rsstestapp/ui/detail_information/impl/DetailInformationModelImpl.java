package com.geekapps.rsstestapp.ui.detail_information.impl;

import com.geekapps.rsstestapp.data.network.RssTestAppClient;
import com.geekapps.rsstestapp.data.network.RssTestAppService;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;
import com.geekapps.rsstestapp.data.network.pojo.detail_information.DetailInformation;
import com.geekapps.rsstestapp.mvp.BaseMvpModel;
import com.geekapps.rsstestapp.ui.detail_information.DetailInformationModel;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class DetailInformationModelImpl extends BaseMvpModel implements DetailInformationModel {

    private RssTestAppService api = RssTestAppClient.getClientForDetailInformation().create(RssTestAppService.class);

    @Override
    public Observable<DetailInformation> getDetailInformation(Integer id) {
        return api.getDetailInformation(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::checkForError)
                .map(this::parseGetDetailInformation);
    }

    private DetailInformation parseGetDetailInformation(Response<ResponseBody> bodyResponse) throws IOException {
        return new GsonBuilder().create().fromJson(bodyResponse.body().string(), DetailInformation.class);
    }
}
