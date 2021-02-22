package com.example.traveljournal.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitObj {

    private static Retrofit retrofit;
    public static String BaseUrl = "https://api.openweathermap.org/";
    public static String AppId = "8495f0722e8613f89b25f89b19bb1864";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
