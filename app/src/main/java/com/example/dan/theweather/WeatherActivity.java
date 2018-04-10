package com.example.dan.theweather;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dan.theweather.data.Channel;
import com.example.dan.theweather.data.Item;
import com.example.dan.theweather.service.WeatherServiceCallback;
import com.example.dan.theweather.service.YahooWeatherService;

public class WeatherActivity extends AppCompatActivity implements WeatherServiceCallback {

    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;


    private YahooWeatherService service;
    private ProgressDialog dialog;


    /**
     * On launch of the application, a dialog displaying a loading message while also refreshing the service to update the weather for our location    *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weatherIconImageView = (ImageView)findViewById(R.id.weatherIconImageView);
        temperatureTextView = (TextView)findViewById(R.id.temperatureTextView);
        conditionTextView = (TextView)findViewById(R.id.conditionTextView);
        locationTextView = (TextView)findViewById(R.id.locationTextView);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Slowly Loading...");
        dialog.show();

        //
        //Where we specify what city we want weather data for
        //
        service.refreshWeather("Nanaimo, BC");



    }

    /**
     *
     * The method that runs when the service succcessfully connects to our location data.
     * It hides the loading dialog, while specifying what image to grab based on the getCode of the current condition.
     * It then sets our TextViews after the ImageView to grab the temperature data, using special characters to get a degree symbol
     * as well as condition and location information.
     */
    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();

        Item item = channel.getItem();

        //Specifies the weather icon to use based on the condition.
        int resource = getResources().getIdentifier("drawable/p" + item.getCondition().getCode(), null, getPackageName());

        Drawable weatherIconDrawable = getResources().getDrawable(resource);
        weatherIconImageView.setImageDrawable(weatherIconDrawable);

        // Edits the Text Views to display the Temperature, Condition and Location of the city specified using various methods on Item and Channel.
        temperatureTextView.setText(item.getCondition().getTemperature()+ "\u00B0" +channel.getUnits().getTemperature());
        conditionTextView.setText(item.getCondition().getDescription());
        locationTextView.setText(service.getLocation());

    }

    /**
     *
     * Deals with the failure of the service, for example if a city that did not exist was specified, it would display a toast message with the Exception info.
     */
    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();

    }
}
