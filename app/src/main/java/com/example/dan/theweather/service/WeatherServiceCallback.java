package com.example.dan.theweather.service;

import com.example.dan.theweather.data.Channel;


public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);

    void serviceFailure(Exception exception);
}
