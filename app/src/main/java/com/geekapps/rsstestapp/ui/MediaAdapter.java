package com.geekapps.rsstestapp.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekapps.rsstestapp.R;
import com.geekapps.rsstestapp.data.GlideImageLoader;
import com.geekapps.rsstestapp.data.network.pojo.MediaContent;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MediaViewHolder> {

    MediaContent mediaContent;
    Context context;

    public MediaAdapter(MediaContent mediaContent, Context context) {
        this.mediaContent = mediaContent;
        this.context = context;
    }

    @NonNull
    @Override
    public MediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_item, parent, false);
        return new MediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaViewHolder holder, int position) {
        holder.name.setText(mediaContent.getFeed().getResults().get(position).getName());
        holder.artist.setText(mediaContent.getFeed().getResults().get(position).getArtistName());
        GlideImageLoader.loadImage(mediaContent.getFeed().getResults().get(position).getArtworkUrl100(), holder.logo, context);
    }

    @Override
    public int getItemCount() {
        return mediaContent.getFeed().getResults().size();
    }

    public static class MediaViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView artist;
        ImageView logo;

        MediaViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            artist = (TextView) itemView.findViewById(R.id.artist);
            logo = (ImageView) itemView.findViewById(R.id.logo);
        }
    }

}