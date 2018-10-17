package com.geekapps.rsstestapp.mvp;

import android.content.Context;
import android.support.annotation.Nullable;

public interface BaseMvpView {

    void showLoading();

    void hideLoading();

    void showMessage(String message);

    void showError(String errorMessage);

    @Nullable
    Context getContext();


}
