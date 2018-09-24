package com.dmitrybondarev.taskmanager.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputValidationService {
//TODO properties
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
//TODO properties
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    public static String dateToString(Date date) {
        return dateFormat.format(date);
    }

    public static String timeToString(Date time) {
        return timeFormat.format(time);
    }

    public boolean checkTitle(String title) {
        return !title.equals("");
    }

    public boolean checkDescription(String description) {
        return !description.equals("");
    }

    public boolean checkDate(String date) {
        String[] split = date.split("[.]");
        if (split.length != 3) return false;
        int day = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int year = Integer.parseInt(split[2]);

        if (day < 0 || day > 31) return false;
        if (month < 1 || month > 12) return false;
        if (year < 0) return false;

        return true;
    }

    public boolean checkTime(String time) {
        String[] split = time.split("[.]");
        if (split.length != 2) return false;
        int hours = Integer.parseInt(split[0]);
        int minutes = Integer.parseInt(split[1]);

        if (hours < 0 || hours > 24) return false;
        if (minutes < 0 || minutes > 60) return false;

        return true;
    }


    public Date stringToDate(String date) throws ParseException {
        Date result = dateFormat.parse(date);
        return result;
    }

    public Date stringToTime(String time) throws ParseException {
        Date result = timeFormat.parse(time);
        return new Date();
    }
}
