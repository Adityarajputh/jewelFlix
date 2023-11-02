package com.example.otpactiviy.utils;

import com.example.otpactiviy.data.ApiModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @FormUrlEncoded
    @Headers({"api-key: 97c7038e2ab7ba61cc27f400c1b52eb17dde25f2a8ce76f778"})
    @POST("customer-login/generate_login_otp")
    Call<ApiModel> generateOTP(@Field("mno") String number/*@Body MultipartBody body*/);

    @FormUrlEncoded
    @Headers({"api-key: 97c7038e2ab7ba61cc27f400c1b52eb17dde25f2a8ce76f778"})
    @POST("customer-login/verify_login_otp")
    Call<ApiModel> verifyOTP(@Field("mno") String number,@Field("otp") String otp);
}
