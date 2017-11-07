import model.Weather;
import service.IWeather;
import service.WeatherImpl;

import java.util.HashMap;

public class BootStrap implements Runnable{
    public static void main(String[] args) {
        for (int i = 0; i <3 ; i++) {

        BootStrap bootStrap =new BootStrap();
        Thread thread =new Thread(bootStrap);
            System.out.println(thread.currentThread().getName() + " " + i);
        thread.start();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i <1000000; i++) {
            IWeather weather =new WeatherImpl();
            Weather weather1 = weather.generatorData();
            weather.addData(weather1);
        }
    }
}
