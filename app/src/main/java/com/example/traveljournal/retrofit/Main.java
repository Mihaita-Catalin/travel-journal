package com.example.traveljournal.retrofit;

import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    public float temp;
    @SerializedName("humidity")
    public float humidity;

    public Main(float temp, float humidity) {
        this.temp = temp;
        this.humidity = humidity;
    }
}
