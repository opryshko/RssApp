package com.geekapps.rsstestapp.ui.categories.movies;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geekapps.rsstestapp.R;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;
import com.geekapps.rsstestapp.mvp.BaseMvpFragment;
import com.geekapps.rsstestapp.ui.MediaAdapter;
import com.geekapps.rsstestapp.ui.categories.movies.impl.MoviesPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesFragment extends BaseMvpFragment implements MoviesView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    MoviesPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_movies, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        presenter = new MoviesPresenter(this);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getTop25Movies();
    }

    @Override
    public void replaceFragment() {

    }

    @Override
    public void initRecyclerView(MediaContent mediaContent) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MediaAdapter(mediaContent, this));
    }
}

