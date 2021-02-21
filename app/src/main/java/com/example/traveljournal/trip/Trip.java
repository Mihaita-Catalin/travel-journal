package com.example.traveljournal.trip;

public class Trip {

    private String title;
    private String destination;
    private double rating;
    private boolean isFavorite;

    public Trip(String title, String destination, double rating, boolean isFavorite) {
        this.title = title;
        this.destination = destination;
        this.rating = rating;
        this.isFavorite = isFavorite;
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
