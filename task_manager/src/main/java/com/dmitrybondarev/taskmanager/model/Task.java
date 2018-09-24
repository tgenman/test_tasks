package com.dmitrybondarev.taskmanager.model;

import com.dmitrybondarev.taskmanager.controller.InputValidationService;

import java.util.Date;

public class Task {

    private static int idCounter = 1;

    private int id;

    private String title;

    private String description;

    private Date date;

    private Date time;

    public Task(String title, String description, Date date, Date time) {
        this.id = idCounter++;
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
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
                + "   " + InputValidationService.dateToString(date)
                + "  " + InputValidationService.timeToString(time);
    }
}
