package com.example.sunnyweather.logic;

import android.view.KeyEvent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sunnyweather.logic.model.Place;
import com.example.sunnyweather.logic.model.PlaceResponse;
import com.example.sunnyweather.logic.network.SunnyWeatherNetwork;

import java.util.List;


public class Repository {
    MutableLiveData<List<Place>> placeResponseLiveData = new MutableLiveData<>();
    public MutableLiveData<List<Place>> searchPlaces(String query) {
        PlaceResponse placeResponse = new SunnyWeatherNetwork().searchPlaces(query);
        if (placeResponse!=null&&placeResponse.getStatus().equals("ok")) {
            List<Place> places = placeResponse.getPlaces();
            placeResponseLiveData.setValue(places);
        }
        return placeResponseLiveData;
    }
}
