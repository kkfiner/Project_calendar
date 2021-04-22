package com.example.project_calendar.weather;

import android.app.Activity;
import android.content.SharedPreferences;

public class CityPreference {
//store last city user entered using sharedPreferences
    SharedPreferences prefs;

    public CityPreference(Activity activity){
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    // If the user has not chosen a city yet, return
    // Sydney as the default city
    String getCity(){
        return prefs.getString("city", "Sydney");
    }

    public void setCity(String city){
        prefs.edit().putString("city", city).commit();
    }


}