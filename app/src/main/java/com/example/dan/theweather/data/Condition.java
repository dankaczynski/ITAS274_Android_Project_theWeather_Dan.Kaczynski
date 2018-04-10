package com.example.dan.theweather.data;

import org.json.JSONObject;



public class Condition implements JSONPopulator {

    private int code;
    private int temperature;
    private String description;


    /**
     * Method that gets code for the Weather condition that we use to display the correct image
     * corresponding to the weather
     */
    public int getCode() {
        return code;
    }

    /**
     * Method that gets the temperature.
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * Method that gets the description.
     * Example from API: "description": "Yahoo! Weather for Nome, AK, US",
     */
    public String getDescription() {
        return description;
    }

    /**
     * Populates the JSON Object with condition data so we can use and display it later.  Condition data contains the Code, Temperature, and Description.
     * the API data is formatted in JSON.
     */
    @Override
    public void populate(JSONObject data) {

        code = data.optInt("code");
        temperature = data.optInt("temp");
        description = data.optString("text");
    }
}
