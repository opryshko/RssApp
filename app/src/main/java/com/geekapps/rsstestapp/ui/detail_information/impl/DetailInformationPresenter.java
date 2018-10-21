package com.geekapps.rsstestapp.ui.detail_information.impl;

import android.os.Build;
import android.text.Html;

import com.geekapps.rsstestapp.data.db.detail_information.DetailInformationTableHelper;
import com.geekapps.rsstestapp.data.db.detail_information.DetailInformationTableHelperImpl;
import com.geekapps.rsstestapp.data.network.pojo.detail_information.DetailInformation;
import com.geekapps.rsstestapp.data.network.pojo.detail_information.DetailInformationItem;
import com.geekapps.rsstestapp.mvp.BaseMvpPresenter;
import com.geekapps.rsstestapp.ui.detail_information.DetailInformationModel;
import com.geekapps.rsstestapp.ui.detail_information.DetailInformationView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DetailInformationPresenter extends BaseMvpPresenter {
    private DetailInformationView view;
    private DetailInformationModel model;
    private DetailInformationTableHelper detailInformationTableHelper;
    private Integer currentCollectionId;

    public DetailInformationPresenter(DetailInformationView view) {
        super(view);
        this.view = view;
        this.model = new DetailInformationModelImpl();
        this.detailInformationTableHelper = new DetailInformationTableHelperImpl(view.getContext());
    }

    public void getDetailInformation(Integer id) {
        view.showLoading();
        currentCollectionId = id;
        model.getDetailInformation(id).subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleGetDetailInformationResponse, this::loadDataFromDb);
    }

    private void handleGetDetailInformationResponse(DetailInformation detailInformation) {
        DetailInformationItem detailInformationItem = detailInformation.getResults().get(0);

        setInformation(detailInformationItem);
        view.hideLoading();
        detailInformationTableHelper.replaceDetailItem(detailInformationItem);
    }

    private void setInformation(DetailInformationItem detailInformationItem) {
        view.setArtistName(detailInformationItem.getArtistName());
        view.setCollectionName(detailInformationItem.getCollectionName());
        view.setCountry(detailInformationItem.getCountry());
        view.setGenre(detailInformationItem.getPrimaryGenreName());
        view.setLogo(detailInformationItem.getArtworkUrl100());

        DateFormat formatter = new SimpleDateFormat(("yyyy-MM-dd'T'HH:mm:ss"));
        Date date = new Date();
        try {
            view.setReleaseDate(formatter.parse(detailInformationItem.getReleaseDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat.getDateTimeInstance().format(date);

        view.setPrice(detailInformationItem.getCollectionPrice().toString() + " " + detailInformationItem.getCurrency());
        if (detailInformationItem.getDescription() != null)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.setDescription(Html.fromHtml(detailInformationItem.getDescription(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                view.setDescription(Html.fromHtml(detailInformationItem.getDescription()));
            }
    }

    private void loadDataFromDb(Throwable throwable) {
        DetailInformationItem detailInformationItem = detailInformationTableHelper.getDetail(currentCollectionId);
        if (detailInformationItem != null) {
            setInformation(detailInformationItem);
            view.hideLoading();
            return;
        }
        showError(throwable);
    }

}
