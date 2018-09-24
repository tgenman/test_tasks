package com.dmitrybondarev.taskmanager.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

    private static int idCounter = 1;

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");

    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    private int id;

    private String title;

    private String description;

    private Date date;

    private Date time;

    public Task(String title, String description, String date, String time) throws ParseException {
        this.id = idCounter++;
        this.title = title;
        this.description = description;
        this.date = dateFormat.parse(date);
        this.time = timeFormat.parse(time);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public String toString() {
        return  "id=" + id
                + "   " + title.toUpperCase()
                + "   " + description
                + "   " + dateFormat.format(date)
                + "  " + timeFormat.format(time);
    }

    public static String dateToString(Date date) {
        return dateFormat.format(date);
    }

//    public static String timeToString(Date time) {
//        return timeFormat.format(time);
//    }
}
