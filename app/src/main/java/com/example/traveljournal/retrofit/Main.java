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

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }
}
