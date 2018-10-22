package com.geekapps.rsstestapp.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.geekapps.rsstestapp.R;

import butterknife.Unbinder;

public abstract class BaseMvpFragment extends Fragment implements BaseMvpView {

    protected Unbinder unbinder;
    protected View rootView;
    @Nullable
    protected Toolbar toolbar;
    private View mLoadingView;
    private ActionBar actionBar;
    protected CallBack baseCallBack;

    protected void initLoadingView() {
        setLoadingView(rootView.findViewById(R.id.loading_view));
    }

    protected void initToolbar() {
        setHasOptionsMenu(true);
        toolbar.setNavigationOnClickListener(view -> getFragmentManager().popBackStack());
    }

    protected void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

    protected void inflateMenu(int resId) {
        toolbar.inflateMenu(resId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CallBack) {
            baseCallBack = (CallBack) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement CallBack");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isAllowActionBarInit())
            setHasOptionsMenu(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected boolean isAllowActionBarInit() {
        return true;
    }

    @Override
    public void showLoading() {
        if (getLoadingView() != null) {
            hideKeyboard();
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
        if (getActivity() != null)
            new AlertDialog.Builder(getActivity())
                    .setTitle("Error")
                    .setMessage(errorMessage)
                    .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                    .show();
    }


    @Override
    public Context getContext() {
        return getActivity();
    }

    protected View getLoadingView() {
        initLoadingView();

        return mLoadingView;
    }

    public void setLoadingView(View mLoadingView) {
        this.mLoadingView = mLoadingView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    private void onBackPressed() {
    }

    public interface CallBack {
        void addFragment(Fragment fragment);

        void replaceFragment(Fragment fragment);
    }
}


