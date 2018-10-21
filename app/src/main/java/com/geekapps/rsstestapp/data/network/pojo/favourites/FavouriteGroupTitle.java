package com.geekapps.rsstestapp.data.network.pojo.favourites;

public class FavouriteGroupTitle implements FavouritesListItem {
    private String title;

    public FavouriteGroupTitle(String title) {
        this.title = title;
    }

    @Override
    public Integer getItemType() {
        return FavouritesListItem.FAVOURITE_GROUP_TITLE_ITEM_TYPE;
    }

    public String getTitle() {
        return title;
    }

    public void SetTitle(String title) {
        this.title = title;
    }
}
