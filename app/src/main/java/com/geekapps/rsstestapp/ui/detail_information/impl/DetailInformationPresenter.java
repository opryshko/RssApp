package com.geekapps.rsstestapp.ui.detail_information.impl;

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
        view.hideLoading();
    }
}
