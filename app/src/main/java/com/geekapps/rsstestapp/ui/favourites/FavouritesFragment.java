package com.geekapps.rsstestapp.ui.favourites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geekapps.rsstestapp.R;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;
import com.geekapps.rsstestapp.mvp.BaseMvpFragment;
import com.geekapps.rsstestapp.ui.categories.BaseCategoryPresenter;
import com.geekapps.rsstestapp.ui.detail_information.DetailInformationFragment;
import com.geekapps.rsstestapp.ui.favourites.impl.FavouritesPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouritesFragment extends BaseMvpFragment implements FavouritesView, BaseCategoryPresenter.CallBack {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_placeholder)
    TextView tvPlaceholder;
    @BindView(R.id.text_placeholder_view)
    View textPlaceholderView;
    private DetailInformationFragment detailInformationFragment;
    private FavouritesPresenter presenter;
    private FavouriteMediaAdapter favouriteMediaAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_favourites, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        presenter = new FavouritesPresenter(this);
        BaseCategoryPresenter.callBack = (BaseCategoryPresenter.CallBack) this;
        initRecyclerView();
        detailInformationFragment = new DetailInformationFragment();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadFavouriteGroups();
    }

    @Override
    public void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        favouriteMediaAdapter = new FavouriteMediaAdapter(this);
        recyclerView.setAdapter(favouriteMediaAdapter);
    }

    @Override
    public void addFavouritesGroup(String title, List<MediaItem> medias) {
        favouriteMediaAdapter.addGroup(title, medias);
        favouriteMediaAdapter.notifyDataSetChanged();
    }

    @Override
    public void clearFavoritesList() {
        favouriteMediaAdapter.removeAll();
    }

    @Override
    public void showEmptyListPlaceholder() {
        tvPlaceholder.setText(R.string.empty_favourites_placeholder);
        textPlaceholderView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyListPlaceholder() {
        textPlaceholderView.setVisibility(View.GONE);
    }

    @Override
    public void showDetailInformation(Integer id) {
        putItemIdToBundle(id);
        this.baseCallBack.addFragment(detailInformationFragment);
    }

    public void putItemIdToBundle(Integer id) {
        Bundle bundle = new Bundle();
        bundle.putInt("SelectedItemId", id);
        detailInformationFragment.setArguments(bundle);
    }

    @Override
    public void onUpdateFavourites() {
        presenter.updateFavouriteGroups();
        favouriteMediaAdapter.notifyDataSetChanged();
    }
}
