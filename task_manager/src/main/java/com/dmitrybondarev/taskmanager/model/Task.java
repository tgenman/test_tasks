package com.dmitrybondarev.taskmanager.model;

public class Task {

    private static int idCounter = 1;

    private int id;

    private String title;

    private String description;

    private String dateAndTime;

    public Task(String title, String description, String dateAndTime) {
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

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}
