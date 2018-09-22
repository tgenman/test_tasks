package com.dmitrybondarev.taskmanager.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

    private static int idCounter = 1;

    private int id;

    private String title;

    private String description;

    private Date dateAndTime;

    Task(String title, String description, Date dateAndTime) {
        this.id = idCounter++;
        this.title = title;
        this.description = description;
        this.dateAndTime = dateAndTime;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public static void main(String[] args) {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        String s = "2019.09.22 12:50:44";
        try {
            Date parse = formatForDateNow.parse(s);

            System.out.println(formatForDateNow.format(parse));
        } catch (ParseException e) {
            e.printStackTrace();
        }


//        System.out.println();
//        System.out.println(dateNow.toString());
//        System.out.println("Текущая дата " + formatForDateNow.format(dateNow));
    }
}
