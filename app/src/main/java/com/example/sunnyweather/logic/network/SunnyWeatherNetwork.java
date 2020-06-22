package com.example.sunnyweather.logic.network;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.sunnyweather.logic.model.PlaceResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SunnyWeatherNetwork {
    private PlaceService placeService = new ServiceCreator().create(PlaceService.class);
    PlaceResponse body;

    public PlaceResponse searchPlaces(String query) {

        Call<PlaceResponse> placeResponseCall = placeService.searchPlaces(query);
        Callback<PlaceResponse> placeResponseCallback = new Callback<PlaceResponse>() {
            @Override
            public void onResponse(Call<PlaceResponse> call, Response<PlaceResponse> response) {
                body = response.body();
            }
            @Override
            public void onFailure(Call<PlaceResponse> call, Throwable t) {
                t.printStackTrace();
            }
        };
        placeResponseCall.enqueue(placeResponseCallback);
        while(body == null) {
            
        }
        return body;
    }
}
