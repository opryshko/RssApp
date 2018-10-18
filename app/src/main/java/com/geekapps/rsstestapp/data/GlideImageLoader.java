package com.geekapps.rsstestapp.data;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ViewTarget;
import com.geekapps.rsstestapp.R;

public class GlideImageLoader {

    public static ViewTarget<ImageView, Drawable> loadImage(String url, ImageView imageView, Context context) {
        return Glide.with(context)
                .setDefaultRequestOptions(new RequestOptions().circleCrop().placeholder(R.drawable.ic_placeholder_image).error(R.drawable.ic_broken_image))
                .load(url == null ? "" : url)
                .into(imageView);
    }
    public static ViewTarget<ImageView, Drawable> loadRectImage(String url, ImageView imageView, Context context) {
        return Glide.with(context)
                .setDefaultRequestOptions(new RequestOptions().centerCrop().placeholder(R.drawable.ic_placeholder_image).error(R.drawable.ic_broken_image))
                .load(url == null ? "" : url)
                .into(imageView);
    }
}
