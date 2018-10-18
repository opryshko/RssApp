package com.geekapps.rsstestapp.ui.detail_information;

import com.geekapps.rsstestapp.data.network.pojo.detail_information.DetailInformation;

import io.reactivex.Observable;

public interface DetailInformationModel {

    Observable<DetailInformation> getDetailInformation(Integer id);
}
