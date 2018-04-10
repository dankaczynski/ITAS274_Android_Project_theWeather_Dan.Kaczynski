package com.example.dan.theweather.service;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.AsyncTask;
import com.example.dan.theweather.data.Channel;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class YahooWeatherService {
    private WeatherServiceCallback callback;
    private String location;
    private Exception error;

    public YahooWeatherService(WeatherServiceCallback callback) {
        this.callback = callback;
    }


    public String getLocation() {
        return location;
    }

    /**
     * The method that runs when the app launches to refresh weather data for the location.
     * It is set to run in the background while the Loading dialog displays until data is retrieved.
     */
    @SuppressLint("StaticFieldLeak")
    public void refreshWeather(final String location) {

        AsyncTask<String, Void, String> execute = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {


                //
                // This is the statement being run against the Yahoo Weather API, inserting our location variable to grab the location from our code.
                // Could be used to allow the user to input a location or with GPS locating so it's not just a single location.
                //
                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")", location);

                //
                // The Yahoo Weather endpoint we are connecting to for our data, this is where we run the above statement against to get the data from their servers
                //
                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));

                /**
                 * Connects to the endpoint through URLConnection and reads the data while building a string that we can manipulate.
                 */
                try {
                    URL url = new URL(endpoint);

                    URLConnection connection = url.openConnection();

                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    return result.toString();

                } catch (Exception e) {
                    error = e;

                }


                return null;
            }

            @Override
            protected void onPostExecute(String s) {

                if (s == null && error != null) {

                    callback.serviceFailure(error);
                    return;

                }

                /**
                 *
                 * Puts the data into the JSON Object unless it can't find the data, then it gives a No Weather Info Found message.
                 */
                try {
                    JSONObject data = new JSONObject(s);

                    JSONObject queryResults = data.optJSONObject("query");

                    int count = queryResults.optInt("count");

                    if (count == 0) {

                        callback.serviceFailure(new LocationWeatherException("No Weather Information Found for " + location));
                        return;

                    }

                    Channel channel = new Channel();
                    channel.populate(queryResults.optJSONObject("Results").optJSONObject("channel"));

                    callback.serviceSuccess(channel);

                } catch (JSONException e) {
                    callback.serviceFailure(e);
                }
            }

        }.execute(location);

    }

    public class LocationWeatherException extends Exception {
        public LocationWeatherException(String message) {
            super(message);
        }
    }

}
