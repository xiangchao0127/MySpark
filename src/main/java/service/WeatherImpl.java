package service;

import util.DateUtil;
import util.MysqlUtil;

import java.util.Date;
import java.util.HashMap;

public class WeatherImpl implements IWeather{
    @Override
    public void addData(HashMap hashMap) {
        MysqlUtil.insertOrUpdateOrdelete("insert into weather values(?,?,?,?,?,?);",hashMap);
    }

    @Override
    public HashMap generatorData() {
        HashMap hashMap  = new HashMap();
        hashMap.put("No","");
        hashMap.put("date", DateUtil.date2Str(new Date()));
        hashMap.put("direction","");
        hashMap.put("airQuality","");
        hashMap.put("cloudState","");
        hashMap.put("temp",1);
        return hashMap;
    }
}
