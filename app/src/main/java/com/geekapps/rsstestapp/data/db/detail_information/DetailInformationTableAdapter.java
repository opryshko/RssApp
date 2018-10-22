package com.geekapps.rsstestapp.data.db.detail_information;

import com.geekapps.rsstestapp.data.network.pojo.detail_information.DetailInformationItem;

import java.util.List;

public interface DetailInformationTableAdapter {

    void addDetailItem(DetailInformationItem detail);

    void addDetailItems(List<DetailInformationItem> details);

    void replaceDetailItem(DetailInformationItem detail);

    DetailInformationItem getDetail(int id);

    List<DetailInformationItem> getAllDetails();

    long getDetailsCount();

    int updateDetail(DetailInformationItem detail);

    void updateAllDetails(List<DetailInformationItem> details);

    void deleteDetail(DetailInformationItem detail);

    void deleteAllDetails();
}
