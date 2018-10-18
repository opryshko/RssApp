package com.geekapps.rsstestapp.data.network;

import com.geekapps.rsstestapp.data.network.pojo.detail_information.DetailInformation;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RssTestAppClient {
    private static final String CATEGORIES_ENDPOINT = "https://rss.itunes.apple.com/api/v1/us/";
    private static final String DETAIL_INFORMATION_ENDPOINT = "https://itunes.apple.com/";
    private static Retrofit retrofitForCategory;
    private static Retrofit retrofitForDetailInformation;
    private static OkHttpClient.Builder client = new OkHttpClient.Builder();

    private static OkHttpClient.Builder getBuilder() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client.connectTimeout(10, TimeUnit.SECONDS);
        client.readTimeout(10, TimeUnit.SECONDS).build();

        client.addInterceptor(interceptor);
        client.addInterceptor(new AcceptHeaderInterceptor());
        return client;
    }

    public static Retrofit getClientForCategory() {
        if (retrofitForCategory == null) {
            retrofitForCategory = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(CATEGORIES_ENDPOINT)
                    .client(getBuilder().build())
                    .build();
        }
        return retrofitForCategory;
    }

    public static Retrofit getClientForDetailInformation() {
        if (retrofitForDetailInformation == null) {
            retrofitForDetailInformation = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(DETAIL_INFORMATION_ENDPOINT)
                    .client(getBuilder().build())
                    .build();
        }
        return retrofitForDetailInformation;
    }

    public static class AcceptHeaderInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            builder.header("Accept", "application/json");
            builder.header("Content-Type", "application/json");

            return chain.proceed(builder.build());
        }
    }
}
