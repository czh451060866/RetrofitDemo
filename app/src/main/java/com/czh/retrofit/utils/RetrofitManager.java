package com.czh.retrofit.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private RetrofitManager(){}

    public static class Singleton{
        private static Retrofit INSTANCE =  new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.99.157:9102")
                .build();
    }

    public static Retrofit getInstance(){
        return Singleton.INSTANCE;
    }
}
