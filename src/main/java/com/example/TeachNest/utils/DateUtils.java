package com.example.TeachNest.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String dateFormatDayMonthYear(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}
