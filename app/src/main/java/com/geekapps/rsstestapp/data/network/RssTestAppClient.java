package com.geekapps.rsstestapp.data.network;

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
    private static final String ENDPOINT = "https://rss.itunes.apple.com/api/v1/us/";
    private static Retrofit retrofit;
    private static OkHttpClient.Builder client = new OkHttpClient.Builder();

    public static Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client.connectTimeout(10, TimeUnit.SECONDS);
        client.readTimeout(10, TimeUnit.SECONDS).build();

        client.addInterceptor(interceptor);
        client.addInterceptor(new AcceptHeaderInterceptor());
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(ENDPOINT)
                    .client(client.build())
                    .build();
        }
        return retrofit;
    }

    public static class AcceptHeaderInterceptor implements Interceptor {

        public AcceptHeaderInterceptor() {
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            builder.header("Accept", "application/json");
            builder.header("Content-Type", "application/json");

            return chain.proceed(builder.build());
        }
    }
}
