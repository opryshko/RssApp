package com.geekapps.rsstestapp.data.network;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RssTestAppService {

    @POST("Account/GetVerificationCode")
    Observable<Response<ResponseBody>> getVerificationCode(@Body HashMap<String, String> body);

    @POST("Account/ConfirmPhone")
    Observable<Response<ResponseBody>> confirmPhone(@Body HashMap<String, String> body);

    @POST("Account/Register")
    Observable<Response<ResponseBody>> register(@Body HashMap<String, String> body);

    @POST("Account/login")
    Observable<Response<ResponseBody>> login(@Body HashMap<String, String> body);


    @GET("Account/GetTemplates")
    Observable<Response<ResponseBody>> getTagTemplateList();
}
