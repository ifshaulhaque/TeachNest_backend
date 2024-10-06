package com.example.TeachNest.utils;

import com.example.TeachNest.entities.StudentAttendance;

import java.util.Date;

public class IdUtils {
    public static String getAttendanceId(
            StudentAttendance studentAttendance
    ) {
        return getAttendanceId(
                studentAttendance.getDate(),
                studentAttendance.getBatchId()
        );
    }

    public static String getAttendanceId(
            Date date,
            String batchId
    ) {
        return DateUtils.dateFormatDayMonthYear(date) +"~"+ batchId;
    }
}
