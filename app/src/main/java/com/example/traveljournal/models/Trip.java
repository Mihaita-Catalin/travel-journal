package com.example.traveljournal.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "trips_table")
public class Trip {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String title;
    private String destination;
    private String type;
    private int price;
    private double rating;
    private boolean isFavorite;

    public Trip(String title, String destination, String type, int price, double rating,
                boolean isFavorite) {
        this.title = title;
        this.destination = destination;
        this.type = type;
        this.price = price;
        this.rating = rating;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
