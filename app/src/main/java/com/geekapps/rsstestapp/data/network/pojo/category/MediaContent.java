
package com.geekapps.rsstestapp.data.network.pojo.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MediaContent {

    @SerializedName("feed")
    @Expose
    private Feed feed;

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

}
