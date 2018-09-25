package com.dmitrybondarev.taskmanager.controller;

import com.dmitrybondarev.taskmanager.model.DataBase;
import com.dmitrybondarev.taskmanager.model.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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

    public Map<Date, List<Task>> getAllTasksAggregatedByDate() {
        Map<Date, List<Task>> result = new HashMap<>();
        Collection<Task> allTasks = dataBase.getAllTasks();

        for (Task task : allTasks) {
            List<Task> taskList = result.get(task.getDate());
            if (taskList != null) {
                taskList.add(task);
            } else {
                ArrayList<Task> newTaskList = new ArrayList<>();
                newTaskList.add(task);
                result.put(task.getDate(), newTaskList);
            }
        }
        return result;
    }

    public Collection<Task> getTasksByKeyWord(String keyWord) {
        return dataBase.getTasksByKeyWord(keyWord);
    }

    public boolean deleteTaskById(int id) {
        return dataBase.deleteTaskById(id);
    }
}
