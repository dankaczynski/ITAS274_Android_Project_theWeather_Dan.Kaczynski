package com.example.dan.theweather.data;

import org.json.JSONObject;



public class Units implements JSONPopulator {

    private String temperature;

    /**
     * Method that gets the temperature.
     */
    public String getTemperature() {
        return temperature;
    }


    /**
     * Populates the JSON Object with temperature data so we can use and display it later.  the API is in JSON.
     */
    @Override
    public void populate(JSONObject data) {

        temperature = data.optString("temperature");

    }
}
