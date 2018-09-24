package com.dmitrybondarev.taskmanager.controller;

import com.dmitrybondarev.taskmanager.model.DataBase;
import com.dmitrybondarev.taskmanager.model.Task;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DataBaseController {

    private DataBase dataBase;

    public DataBaseController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public int createNewTask(String title, String description, Date date, Date time) {
        return dataBase.addNewTask(title, description, date, time);
    }

    public Map<Date, List<Task>> getAllTasks() {
        return dataBase.getAllTasks();
    }

    public Collection<Task> getTasksByKeyWord(String keyWord) {
        return dataBase.getTasksByKeyWord(keyWord);
    }

    public boolean deleteTaskById(int id) {
        return dataBase.deleteTaskById(id);
    }
}
