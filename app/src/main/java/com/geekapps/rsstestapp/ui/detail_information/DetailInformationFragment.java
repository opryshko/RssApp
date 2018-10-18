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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailInformationFragment extends BaseMvpFragment implements DetailInformationView {

    @BindView(R.id.artist)
    TextView tvArtistName;
    @BindView(R.id.name)
    TextView tvCollectionName;
    @BindView(R.id.price)
    TextView tvPrice;
    @BindView(R.id.country)
    TextView tvCountry;
    @BindView(R.id.date)
    TextView tvReleaseDate;
    @BindView(R.id.genre)
    TextView tvGenre;
    @BindView(R.id.description)
    TextView tvDescription;
    @BindView(R.id.logo)
    ImageView ivLogo;

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
        presenter.getDetailInformation(getItemIdFromBundle());
    }

    private Integer getItemIdFromBundle() {
        Bundle bundle = this.getArguments();
        if (bundle != null)
            return bundle.getInt("SelectedItemId");
        return -1;
    }

    @Override
    public void setLogo(String url) {
        GlideImageLoader.loadRectImage(url, ivLogo, getContext());
    }

    @Override
    public void setCollectionName(String collectionName) {
        tvCollectionName.setText(collectionName);
    }

    @Override
    public void setArtistName(String artistName) {
        tvArtistName.setText(artistName);
    }

    @Override
    public void setPrice(String price) {
        tvPrice.setText(price);
    }

    @Override
    public void setCountry(String country) {
        tvCountry.setText(country);
    }

    @Override
    public void setReleaseDate(String releaseDate) {
        DateFormat formatter = new SimpleDateFormat(("yyyy-MM-dd'T'HH:mm:ss"));
        Date date = new Date();
        try {
            date = formatter.parse(releaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tvReleaseDate.setText(DateFormat.getDateTimeInstance().format(date));
    }

    @Override
    public void setGenre(String genre) {
        tvGenre.setText(genre);
    }

    @Override
    public void setDescription(Spanned description) {
        tvDescription.setText(description);
    }
}
