package util;

import model.ErrorException;
import model.ReturnExceptionEnum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final String FORMAT = "yyyy-MM-dd";
    public static Date str2Date(String str){
        Date date = null;
        try {
          date = new SimpleDateFormat(FORMAT).parse(str);
        } catch (ParseException e) {
            throw new ErrorException(ReturnExceptionEnum.User_Define_Exception,"时间格式错误");
        }
        return date;
    }

    public static String date2Str(Date date){
      return   new SimpleDateFormat(FORMAT).format(date);
    }
}
