package com.geekapps.rsstestapp.ui.categories;

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

import java.util.List;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MediaViewHolder> {
    List<MediaItem> medias;
    CategoryView categoryView;

    public MediaAdapter(List<MediaItem> medias, CategoryView categoryView) {
        this.medias = medias;
        this.categoryView = categoryView;
    }

    @NonNull
    @Override
    public MediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_item, parent, false);

        return new MediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaViewHolder holder, int position) {
        GlideImageLoader.liadCircleImage(medias.get(position).getArtworkUrl100(), holder.ivLogo, categoryView.getContext());
        holder.tvName.setText(medias.get(position).getName());
        holder.tvArtist.setText(medias.get(position).getArtistName());
        holder.setStarSelection(medias.get(position).isFavourite());

        holder.ivStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.isSelected)
                    holder.setStarSelection(false);
                else holder.setStarSelection(true);
                medias.get(position).setFavourite(holder.isSelected);
                categoryView.updateMediaItem(medias.get(position));
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryView.showDetailInformation(medias.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return medias.size();
    }


    public static class MediaViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvName;
        TextView tvArtist;
        ImageView ivLogo;
        ImageView ivStar;
        Boolean isSelected;

        MediaViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvArtist = (TextView) itemView.findViewById(R.id.tv_artist);
            ivLogo = (ImageView) itemView.findViewById(R.id.iv_logo);
            ivStar = (ImageView) itemView.findViewById(R.id.iv_star);

            isSelected = false;
        }

        private void setStarSelection(boolean state) {
            if (state)
                ivStar.setImageResource(R.drawable.ic_star);
            else
                ivStar.setImageResource(R.drawable.ic_star_border);
            isSelected = state;
        }
    }
}