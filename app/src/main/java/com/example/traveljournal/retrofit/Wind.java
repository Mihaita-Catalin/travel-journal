package com.example.traveljournal.retrofit;

import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("speed")
    public float speed;

    public Wind(float speed) {
        this.speed = speed;
    }
}
