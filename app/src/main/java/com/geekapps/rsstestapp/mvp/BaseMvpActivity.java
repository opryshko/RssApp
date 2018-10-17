package com.geekapps.rsstestapp.mvp;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.geekapps.rsstestapp.R;

public abstract class BaseMvpActivity extends AppCompatActivity implements BaseMvpView, BaseMvpFragment.CallBack {

    protected View rootView;
    private View mLoadingView;

    protected void initLoadingView() {
        setLoadingView(rootView.findViewById(R.id.loading_view));
    }

    @Override
    public void showLoading() {
        if (getLoadingView() != null) {
            getLoadingView().setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        if (getLoadingView() != null) {
            getLoadingView().setVisibility(View.GONE);
        }
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String errorMessage) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(errorMessage)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    protected View getLoadingView() {
        if (mLoadingView == null) initLoadingView();
        return mLoadingView;
    }

    public void setLoadingView(View mLoadingView) {
        this.mLoadingView = mLoadingView;
    }
}


