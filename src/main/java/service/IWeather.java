package service;

import model.Weather;

import java.util.HashMap;

public interface IWeather {
    void addData(Weather weather);

    Weather generatorData();
}
