package com.geekapps.rsstestapp.ui.audiobooks.impl;

import com.geekapps.rsstestapp.data.network.pojo.Audiobooks;
import com.geekapps.rsstestapp.mvp.BaseMvpPresenter;
import com.geekapps.rsstestapp.ui.audiobooks.AudiobooksModel;
import com.geekapps.rsstestapp.ui.audiobooks.AudiobooksView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AudiobooksPresenter extends BaseMvpPresenter {
    private AudiobooksView view;
    private AudiobooksModel model;

    public AudiobooksPresenter(AudiobooksView view) {
        super(view);
        this.view = view;
        this.model = new AudiobooksModelImpl();
    }

    public void getTop25Audiobooks() {
        view.showLoading();
        model.getTop25Audiobooks().subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleGetTop25AudiobooksResponse, this::showError);

    }

    private void handleGetTop25AudiobooksResponse(Audiobooks audiobooks) {
        view.hideLoading();
    }
}
