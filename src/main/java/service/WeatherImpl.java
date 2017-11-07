package service;

import model.DirectionEnum;
import model.Weather;
import util.DateUtil;
import util.MysqlUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class WeatherImpl implements IWeather{
    private static final String[] airQuality = {"优","良","劣"};
    private static final String[] cloudState = {"多云","下雨","多云转小雨","多云转阵雨","小雨转晴","小雨转多云","阴天","雷阵雨","多云转阴"};
    @Override
    public void addData(Weather weather) {
        MysqlUtil.insertOrUpdateOrdelete("insert into weather_ther values(?,?,?,?,?,?)",weather);
    }

    @Override
    public Weather generatorData() {
        Weather weather  = new Weather();
        weather.setNo(new Date().getTime()+new Random().nextInt(1000000)+""); //编号
        weather.setDate(new Date()); //时间
        weather.setDirection(DirectionEnum.getEnumByCode(String.valueOf(new Random().nextInt(4)+1))); //风向
        weather.setAirQuality(airQuality[new Random().nextInt(3)]); //空气质量
        weather.setCloudState(cloudState[new Random().nextInt(9)]); //多云或下雨
        weather.setTemp(new Random().nextInt(50)); //温度
        return weather;
    }
}
