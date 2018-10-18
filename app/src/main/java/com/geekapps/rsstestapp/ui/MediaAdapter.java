package com.geekapps.rsstestapp.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekapps.rsstestapp.R;
import com.geekapps.rsstestapp.data.GlideImageLoader;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;
import com.geekapps.rsstestapp.ui.categories.CategoryView;

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
        GlideImageLoader.loadImage(mediaContent.getFeed().getResults().get(position).getArtworkUrl100(), holder.logo, categoryView.getContext());
        holder.name.setText(mediaContent.getFeed().getResults().get(position).getName());
        holder.artist.setText(mediaContent.getFeed().getResults().get(position).getArtistName());
        holder.star.setImageResource(R.drawable.ic_star_border);
        holder.star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.isSelected)
                    holder.setStarSelection(false);
                else holder.setStarSelection(true);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mediaContent.getFeed().getResults().size();
    }


    public static class MediaViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView artist;
        ImageView logo;
        ImageView star;
        Boolean isSelected;

        MediaViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            artist = (TextView) itemView.findViewById(R.id.artist);
            logo = (ImageView) itemView.findViewById(R.id.logo);
            star = (ImageView) itemView.findViewById(R.id.star);
            isSelected = false;
        }

        private void setStarSelection(boolean state) {
            if (state)
                star.setImageResource(R.drawable.ic_star);
            else
                star.setImageResource(R.drawable.ic_star_border);
            isSelected = state;
        }


    }

}