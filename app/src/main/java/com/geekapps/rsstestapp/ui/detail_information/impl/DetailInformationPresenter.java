package com.geekapps.rsstestapp.ui.detail_information.impl;

import android.os.Build;
import android.text.Html;

import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;
import com.geekapps.rsstestapp.data.network.pojo.detail_information.DetailInformation;
import com.geekapps.rsstestapp.mvp.BaseMvpPresenter;
import com.geekapps.rsstestapp.ui.detail_information.DetailInformationModel;
import com.geekapps.rsstestapp.ui.detail_information.DetailInformationView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DetailInformationPresenter extends BaseMvpPresenter {
    private DetailInformationView view;
    private DetailInformationModel model;

    public DetailInformationPresenter(DetailInformationView view) {
        super(view);
        this.view = view;
        this.model = new DetailInformationModelImpl();
    }

    public void getDetailInformation(Integer id) {
        view.showLoading();
        model.getDetailInformation(id).subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleGetDetailInformationResponse, this::showError);

    }

    private void handleGetDetailInformationResponse(DetailInformation detailInformation) {
        setInformation(detailInformation);
        view.hideLoading();
    }

    private void setInformation(DetailInformation detailInformation) {
        view.setArtistName(detailInformation.getResults().get(0).getArtistName());
        view.setCollectionName(detailInformation.getResults().get(0).getCollectionName());
        view.setCountry(detailInformation.getResults().get(0).getCountry());
        view.setGenre(detailInformation.getResults().get(0).getPrimaryGenreName());
        view.setLogo(detailInformation.getResults().get(0).getArtworkUrl100());
        view.setReleaseDate(detailInformation.getResults().get(0).getReleaseDate());

        view.setPrice(detailInformation.getResults().get(0).getCollectionPrice().toString() + " " + detailInformation.getResults().get(0).getCurrency());
        if (detailInformation.getResults().get(0).getDescription() != null)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.setDescription(Html.fromHtml(detailInformation.getResults().get(0).getDescription(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                view.setDescription(Html.fromHtml(detailInformation.getResults().get(0).getDescription()));
            }
    }

}
