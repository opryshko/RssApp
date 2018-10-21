package com.geekapps.rsstestapp.ui.detail_information;

import android.text.Spanned;

import com.geekapps.rsstestapp.mvp.BaseMvpView;

import java.util.Date;

public interface DetailInformationView extends BaseMvpView {
    void setLogo(String url);

    void setCollectionName(String collectionName);

    void setArtistName(String artistName);

    void setPrice(String price);

    void setCountry(String country);

    void setReleaseDate(Date releaseDate);

    void setGenre(String genre);

    void setDescription(Spanned description);
}
