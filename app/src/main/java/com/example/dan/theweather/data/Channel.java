package com.example.dan.theweather.data;

import org.json.JSONObject;



public class Channel implements JSONPopulator {
    private Units units;
    private Item item;

    /**
     * Where we grab the units for our location, which includes temperature data.  Channel is what the API calls this section.
     */
    public Units getUnits() {
        return units;
    }

    /**
     * Where we grab the Items for our location, which includes the exact name as well as the condition of the current weather, such as Mostly Clear or Cloudy
     */
    public Item getItem() {
        return item;
    }

    /**
     * Populates our JSON Objects with Unit and Item Data.  The API is formatted in JSON and we essentially parse that data and show what we want from the Query we run.
     */
    @Override
    public void populate(JSONObject data) {

        units = new Units();
        units.populate(data.optJSONObject("units"));

        item = new Item();
        item.populate(data.optJSONObject("item"));

    }
}
