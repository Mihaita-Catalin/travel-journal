package com.example.traveljournal.retrofit;

import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("description")
    public String description;

    public Weather(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
