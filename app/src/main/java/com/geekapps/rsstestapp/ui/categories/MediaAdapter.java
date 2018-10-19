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
import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MediaViewHolder> {

    MediaContent mediaContent;
    CategoryView categoryView;


    public MediaAdapter(MediaContent mediaContent, CategoryView categoryView) {
        this.mediaContent = mediaContent;
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
        GlideImageLoader.loadImage(mediaContent.getFeed().getResults().get(position).getArtworkUrl100(), holder.ivLogo, categoryView.getContext());
        holder.tvName.setText(mediaContent.getFeed().getResults().get(position).getName());
        holder.tvArtist.setText(mediaContent.getFeed().getResults().get(position).getArtistName());
        holder.ivStar.setImageResource(R.drawable.ic_star_border);
        holder.ivStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.isSelected)
                    holder.setStarSelection(false);
                else holder.setStarSelection(true);

            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryView.showDetailInformation(mediaContent.getFeed().getResults().get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mediaContent.getFeed().getResults().size();
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
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvArtist = (TextView) itemView.findViewById(R.id.artist);
            ivLogo = (ImageView) itemView.findViewById(R.id.logo);
            ivStar = (ImageView) itemView.findViewById(R.id.star);
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