package com.example.otpactiviy.utils;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstanceClass {
    public static ApiInterface apiCall = null;
    public static Retrofit retrofit = null;

    public static ApiInterface getApiCall() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
//                .connectTimeout(60, TimeUnit.SECONDS)
//                .readTimeout(60, TimeUnit.SECONDS)
//                .writeTimeout(60, TimeUnit.SECONDS)
                /*.addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                                .addHeader("api-key", "97c7038e2ab7ba61cc27f400c1b52eb17dde25f2a8ce76f7785")
                                .build();
                        return chain.proceed(newRequest);
                    }
                })*/
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .retryOnConnectionFailure(false)
                .build();


        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://tansh.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiCall = retrofit.create(ApiInterface.class);
        return apiCall;
    }
}
