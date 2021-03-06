package com.geekapps.rsstestapp.ui.categories;

import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.geekapps.rsstestapp.R;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;
import com.geekapps.rsstestapp.mvp.BaseMvpFragment;
import com.geekapps.rsstestapp.ui.detail_information.DetailInformationFragment;

import java.util.List;

import butterknife.BindView;

public abstract class BaseCategoryFragment extends BaseMvpFragment implements CategoryView {

    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;
    protected DetailInformationFragment detailInformationFragment;

    @Override
    public void showDetailInformation(Integer id) {
        putItemIdToBundle(id);
        this.baseCallBack.addFragment(detailInformationFragment);
    }

    @Override
    public void initRecyclerView(List<MediaItem> medias) {
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new MediaAdapter(medias, this));
        }
    }

    public void putItemIdToBundle(Integer id) {
        Bundle bundle = new Bundle();
        bundle.putInt("SelectedItemId", id);
        detailInformationFragment.setArguments(bundle);
    }
}
