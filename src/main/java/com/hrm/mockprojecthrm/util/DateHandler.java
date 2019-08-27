package com.hrm.mockprojecthrm.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateHandler {
    public static LocalDate longToLocalDate(String s) {
        Long longValue = Long.valueOf(s + "L");
        LocalDate date = Instant.ofEpochMilli(longValue).atZone(ZoneId.systemDefault()).toLocalDate();
        return date;
    }
}
