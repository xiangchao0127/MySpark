import service.IWeather;
import service.WeatherImpl;

import java.util.HashMap;

public class BootStrap {
    public static void main(String[] args) {
        IWeather weather =new WeatherImpl();
        // TODO: 2017/11/6 等待业务逻辑实现
        HashMap hashMap = weather.generatorData();
        weather.addData(hashMap);
    }
}
