package com.geekapps.rsstestapp.ui.audiobooks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geekapps.rsstestapp.R;
import com.geekapps.rsstestapp.mvp.BaseMvpFragment;
import com.geekapps.rsstestapp.ui.audiobooks.impl.AudiobooksPresenter;

import butterknife.ButterKnife;

public class AudiobooksFragment extends BaseMvpFragment implements AudiobooksView {

    AudiobooksPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_audiobooks, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        presenter = new AudiobooksPresenter(this);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getTop25Audiobooks();
    }

    @Override
    public void replaceFragment() {

    }

}
