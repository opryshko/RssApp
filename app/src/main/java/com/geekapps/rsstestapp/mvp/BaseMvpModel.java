package com.geekapps.rsstestapp.mvp;

import com.geekapps.rsstestapp.data.error.NetworkErrorChecker;

import okhttp3.ResponseBody;
import retrofit2.Response;

public abstract class BaseMvpModel {

    protected void checkForError(Response<ResponseBody> responseBodyResponse) {
        NetworkErrorChecker networkErrorChecker = new NetworkErrorChecker(responseBodyResponse);
        if (!networkErrorChecker.isSuccessful()) {
            throw networkErrorChecker.createException();
        }
    }
}
