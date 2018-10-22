package com.geekapps.rsstestapp.data.network.pojo.detail_information;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.security.PublicKey;

public class DetailInformationItem {

    @SerializedName("collectionId")
    @Expose
    private Integer collectionId;
    @SerializedName("trackId")
    @Expose
    private Integer trackId;
    @SerializedName("artistName")
    @Expose
    private String artistName;
    @SerializedName("collectionName")
    @Expose
    private String collectionName;
    @SerializedName("artworkUrl100")
    @Expose
    private String artworkUrl100;
    @SerializedName("collectionPrice")
    @Expose
    private Double collectionPrice;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("primaryGenreName")
    @Expose
    private String primaryGenreName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("longDescription")
    @Expose
    private String longDescription;

    @SerializedName("wrapperType")
    @Expose
    private String wrapperType = "audiobook";

    public DetailInformationItem() {
    }

    public DetailInformationItem(Integer id, String name, String artist, String logo, Double price, String currency,
                                 String country, String releaseDate, String genre, String description) {
        this.artistName = artist;
        this.artworkUrl100 = logo;
        this.collectionId = id;
        this.collectionName = name;
        this.collectionPrice = price;
        this.currency = currency;
        this.country = country;
        this.releaseDate = releaseDate;
        this.primaryGenreName = genre;
        this.description = description;
    }

    public Integer getId() {
        return wrapperType.equals("track") ? trackId : collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return collectionName == null ? "No information" : collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public Double getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(Double collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    public String getDescription() {
        if (longDescription != null || description != null)
            return wrapperType.equals("track") ? longDescription : description;
        return "No information";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWrapperType() {
        return wrapperType;
    }

    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }
}
