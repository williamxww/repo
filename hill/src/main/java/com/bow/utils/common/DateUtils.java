package com.bow.utils.common;

import java.text.SimpleDateFormat;
import java.util.Date;



public class DateUtils
{
    public static final SimpleDateFormat commonDate = new SimpleDateFormat("yyyy-MM-dd");

    public static String format(Date date, SimpleDateFormat formatPattern) {
        return formatPattern.format(date);
    }

    public static String format(Date date, String formatPattern) {
        SimpleDateFormat format = new SimpleDateFormat(formatPattern);
        return format.format(date);
    }
}
