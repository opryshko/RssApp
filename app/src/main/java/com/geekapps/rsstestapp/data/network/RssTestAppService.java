package com.geekapps.rsstestapp.data.network;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RssTestAppService {

    @GET("audiobooks/top-audiobooks/all/25/non-explicit.json")
    Observable<Response<ResponseBody>> getTop25Audiobooks();

    @GET("movies/top-movies/all/25/non-explicit.json")
    Observable<Response<ResponseBody>> getTop25Movies();

    @GET("podcasts/top-podcasts/all/25/non-explicit.json")
    Observable<Response<ResponseBody>> getTop25Podcasts();

    @GET("lookup?")
    Observable<Response<ResponseBody>> getDetailInformation(@Query("id") Integer id);
}
