package com.sallyjayz.gads2020leadershipboard.client;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmissionClient {
    private static final String BASE_URL = "https://docs.google.com/forms/d/e/";
    private static Retrofit retrofit;
    private static HttpLoggingInterceptor loggingInterceptor;
    private static OkHttpClient okHttpClient;

    public static Retrofit getClient() {

        loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;

    }
}
