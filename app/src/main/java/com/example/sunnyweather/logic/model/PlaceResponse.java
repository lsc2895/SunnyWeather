package com.example.sunnyweather.logic.model;


import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.util.List;

public class PlaceResponse {
    String status;
    List<Place> places;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public String getStatus() {
        return status;
    }

    public List<Place> getPlaces() {
        return places;
    }
}
