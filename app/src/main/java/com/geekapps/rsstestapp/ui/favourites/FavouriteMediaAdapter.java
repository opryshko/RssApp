package com.geekapps.rsstestapp.ui.favourites;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekapps.rsstestapp.R;
import com.geekapps.rsstestapp.data.GlideImageLoader;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;
import com.geekapps.rsstestapp.data.network.pojo.favourites.FavouriteGroupTitle;
import com.geekapps.rsstestapp.data.network.pojo.favourites.FavouritesListItem;

import java.util.ArrayList;
import java.util.List;

public class FavouriteMediaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<FavouritesListItem> favouritesListItems;
    private FavouritesView favouritesView;


    public FavouriteMediaAdapter(FavouritesView favouritesView) {
        this.favouritesListItems = new ArrayList<>();
        this.favouritesView = favouritesView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new MediaViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.favourite_media_item, parent, false));
            case 1:
                return new GroupTitleViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.favourite_group_title_item, parent, false));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                MediaViewHolder mediaViewHolder = (MediaViewHolder) holder;
                MediaItem mediaItem = (MediaItem) favouritesListItems.get(position);
                GlideImageLoader.liadCircleImage(mediaItem.getArtworkUrl100(), mediaViewHolder.ivLogo, favouritesView.getContext());
                mediaViewHolder.tvName.setText(mediaItem.getName());
                mediaViewHolder.tvArtist.setText(mediaItem.getArtistName());

                mediaViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        favouritesView.showDetailInformation(mediaItem.getId());
                    }
                });
                break;
            case 1:
                GroupTitleViewHolder groupTitleViewHolder = (GroupTitleViewHolder) holder;
                FavouriteGroupTitle favouriteGroupTitle = (FavouriteGroupTitle) favouritesListItems.get(position);
                groupTitleViewHolder.tvGroupTitle.setText(favouriteGroupTitle.getTitle());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return favouritesListItems.get(position).getItemType();
    }

    @Override
    public int getItemCount() {
        return favouritesListItems.size();
    }

    public void addGroup(String title, List<MediaItem> medias) {
        favouritesListItems.add(new FavouriteGroupTitle(title));
        favouritesListItems.addAll(medias);
    }

    public void removeAll() {
        favouritesListItems.clear();
    }

    class MediaViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvName;
        TextView tvArtist;
        ImageView ivLogo;

        MediaViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvArtist = (TextView) itemView.findViewById(R.id.tv_artist);
            ivLogo = (ImageView) itemView.findViewById(R.id.iv_logo);
        }
    }

    class GroupTitleViewHolder extends RecyclerView.ViewHolder {
        TextView tvGroupTitle;

        GroupTitleViewHolder(View itemView) {
            super(itemView);
            tvGroupTitle = (TextView) itemView.findViewById(R.id.tv_media_group_title);
        }
    }
}