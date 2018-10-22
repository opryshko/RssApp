package com.geekapps.rsstestapp.data.network.pojo.category;

import android.support.annotation.NonNull;

import com.geekapps.rsstestapp.data.network.pojo.favourites.FavouritesListItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MediaItem implements Comparable<MediaItem>, FavouritesListItem {

    @SerializedName("artistName")
    @Expose
    private String artistName;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("artworkUrl100")
    @Expose
    private String artworkUrl100;
    private Boolean isFavourite = false;
    private Integer position;

    public MediaItem() {
    }

    public MediaItem(Integer id, String name, String artistName, String logo, Boolean isFavourite, Integer position) {
        this.id = id;
        this.name = name;
        this.artistName = artistName;
        this.artworkUrl100 = logo;
        this.isFavourite = isFavourite;
        this.position = position;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public Boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean value) {
        isFavourite = value;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public int compareTo(@NonNull MediaItem mediaItem) {
        return this.getPosition().compareTo(mediaItem.getPosition());
    }

    @Override
    public Integer getItemType() {
        return FavouritesListItem.FAVOURITE_MEDIA_ITEM_TYPE;
    }
}
