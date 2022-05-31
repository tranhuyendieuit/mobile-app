package com.example.nikestoreapp.api;

import com.example.nikestoreapp.model.Account;
import com.example.nikestoreapp.model.BestSeller;
import com.example.nikestoreapp.model.MyOrder;
import com.example.nikestoreapp.model.NewRelease;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitConfig {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").setLenient().create();
    RetrofitConfig retrofit = new Retrofit.Builder()
            .baseUrl("https://nikestoreapp.000webhostapp.com/")
            .client(new OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(RetrofitConfig.class);

    @POST("newrealeses.php")
    Call<ArrayList<NewRelease>> getInfor_NewRealeses();

    @POST("bestseller.php")
    Call<ArrayList<BestSeller>> getInfor_BestSeller();

    @FormUrlEncoded
    @POST("order.php")
    Call<String> insert_oder(@Field("email") String email,
                              @Field("ordernumber") String ordernumber,
                              @Field("date") String date,
                              @Field("total") String total);
    @FormUrlEncoded
    @POST("myorder.php")
    Call<ArrayList<MyOrder>> get_myOrder(@Field("email") String email);
}
