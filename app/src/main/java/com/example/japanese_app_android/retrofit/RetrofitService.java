package com.example.japanese_app_android.retrofit;


import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private Retrofit retrofit;

    public RetrofitService() {
        initializeRetrofit();
    }

    private OkHttpClient createDefaultOkHttpClient() {
        TokenInterceptor tokenInterceptor = new TokenInterceptor();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient().newBuilder()
                .addInterceptor(tokenInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    /**
     * https://stackoverflow.com/questions/36811202/java-net-connectexception-fail-to-connect-to-localhost-127-0-0-1port-8080-co
     */
    private void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
//        .baseUrl("http://192.168.0.236:9000")
                .client(createDefaultOkHttpClient()).baseUrl("http://10.0.2.2:8081")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
