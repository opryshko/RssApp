package com.geekapps.rsstestapp.mvp;

public abstract class BaseMvpPresenter {

    private BaseMvpView mView;

    public BaseMvpPresenter(BaseMvpView view) {
        this.mView = view;
    }

    protected void showError(Throwable throwable) {
        mView.hideLoading();
        if (throwable.getMessage().contains("Unable to resolve host")) {
            mView.showError("Your request cannot be completed because you are not" +
                    "connected to the internet. Verify your network connection and try again.");
        } else {
            mView.showError(throwable.getMessage());
        }
    }

    protected void showError(String error) {
        mView.hideLoading();
        if (error.contains("Unable to resolve host")) {
            mView.showError("Your request cannot be completed because you are not " +
                    "connected to the internet. Verify your network connection and try again.");
        } else {
            mView.showError(error);
        }
    }

    protected String getStringResource(int resource) {
        return mView.getContext().getResources().getString(resource);
    }
}
