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

    private SaverAndLoaderService saverAndLoaderService;

    public DataBaseController(DataBase dataBase, SaverAndLoaderService saverAndLoaderService) {
        this.dataBase = dataBase;
        this.saverAndLoaderService = saverAndLoaderService;
    }

    public int createNewTask(String[] args) throws ParseException {
        String title = args[0].trim();
        String description = args[1].trim();
        String date = args[2].trim();
        String time = args[3].trim();

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
