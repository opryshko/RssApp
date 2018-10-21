package com.geekapps.rsstestapp.ui.detail_information;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekapps.rsstestapp.R;
import com.geekapps.rsstestapp.data.GlideImageLoader;
import com.geekapps.rsstestapp.mvp.BaseMvpFragment;
import com.geekapps.rsstestapp.ui.detail_information.impl.DetailInformationPresenter;

import java.text.DateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailInformationFragment extends BaseMvpFragment implements DetailInformationView {

    @BindView(R.id.tv_artist)
    TextView tvArtistName;
    @BindView(R.id.tv_name)
    TextView tvCollectionName;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_country)
    TextView tvCountry;
    @BindView(R.id.tv_date)
    TextView tvReleaseDate;
    @BindView(R.id.tv_genre)
    TextView tvGenre;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.reload_data_view)
    View reloadDataView;

    DetailInformationPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_detail_information, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        presenter = new DetailInformationPresenter(this);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadDetailInformation(getItemIdFromBundle());
    }

    private Integer getItemIdFromBundle() {
        Bundle bundle = this.getArguments();
        if (bundle != null)
            return bundle.getInt("SelectedItemId");
        return -1;
    }

    @Override
    public void setLogo(String url) {
        if (ivLogo != null)
            GlideImageLoader.loadRectImage(url, ivLogo, getContext());
    }

    @Override
    public void setCollectionName(String collectionName) {
        if (tvCollectionName != null)
            tvCollectionName.setText(collectionName);
    }

    @Override
    public void setArtistName(String artistName) {
        if (tvArtistName != null)
            tvArtistName.setText(artistName);
    }

    @Override
    public void setPrice(String price) {
        if (tvPrice != null)
            tvPrice.setText(price);
    }

    @Override
    public void setCountry(String country) {
        if (tvCountry != null)
            tvCountry.setText(country);
    }

    @Override
    public void setReleaseDate(Date releaseDate) {
        if (tvReleaseDate != null)
            tvReleaseDate.setText(DateFormat.getDateTimeInstance().format(releaseDate));
    }

    @Override
    public void setGenre(String genre) {
        if (tvGenre != null)
            tvGenre.setText(genre);
    }

    @Override
    public void setDescription(Spanned description) {
        if (tvDescription != null)
            tvDescription.setText(description);
    }

    @Override
    public void showReloadDataView() {
        reloadDataView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideReloadDataView() {
        reloadDataView.setVisibility(View.GONE);
    }

    @Override
    @OnClick(R.id.tv_try_again)
    public void onClickTryAgain() {
        presenter.loadDetailInformation(getItemIdFromBundle());
    }
}
