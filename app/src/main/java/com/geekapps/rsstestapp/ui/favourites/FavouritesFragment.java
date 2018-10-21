package com.geekapps.rsstestapp.ui.favourites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geekapps.rsstestapp.R;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;
import com.geekapps.rsstestapp.mvp.BaseMvpFragment;
import com.geekapps.rsstestapp.ui.categories.BaseCategoryFragment;
import com.geekapps.rsstestapp.ui.categories.MediaAdapter;
import com.geekapps.rsstestapp.ui.detail_information.DetailInformationFragment;
import com.geekapps.rsstestapp.ui.favourites.impl.FavouritesPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouritesFragment extends BaseMvpFragment implements FavouritesView {

    @Override
    public void initRecyclerView(List<MediaItem> medias) {

    }
}
