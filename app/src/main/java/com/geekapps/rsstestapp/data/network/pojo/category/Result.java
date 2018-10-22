
package com.geekapps.rsstestapp.data.network.pojo.category;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result extends MediaItem {

    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("artistId")
    @Expose
    private String artistId;
    @SerializedName("artistUrl")
    @Expose
    private String artistUrl;
    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;
    @SerializedName("url")
    @Expose
    private String url;

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtistUrl() {
        return artistUrl;
    }

    public void setArtistUrl(String artistUrl) {
        this.artistUrl = artistUrl;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
