package model;

import java.util.Date;

public class Weather {
    private String No; //编号 （时间+000000000001）用mysql触发器
    private Date date; //时间
    private DirectionEnum direction; //风向
    private String airQuality; //空气质量
    private String cloudState; //多云或下雨
    private String temp; //温度

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DirectionEnum getDirection() {
        return direction;
    }

    public void setDirection(DirectionEnum direction) {
        this.direction = direction;
    }

    public String getAirQuality() {
        return airQuality;
    }

    public void setAirQuality(String airQuality) {
        this.airQuality = airQuality;
    }

    public String getCloudState() {
        return cloudState;
    }

    public void setCloudState(String cloudState) {
        this.cloudState = cloudState;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "No='" + No + '\'' +
                ", date=" + date +
                ", direction=" + direction +
                ", airQuality='" + airQuality + '\'' +
                ", cloudState='" + cloudState + '\'' +
                ", temp='" + temp + '\'' +
                '}';
    }
}
