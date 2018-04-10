package com.example.dan.theweather.data;

import org.json.JSONObject;



public class Item implements JSONPopulator {

    private Condition condition;


    /**
     * Method that gets the weather condition.
     */
    public Condition getCondition() {
        return condition;
    }


    /**
     * Populates the JSON Object with condition data so we can use and display it later.  the API data is formatted in JSON.
     */
    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));


    }
}
