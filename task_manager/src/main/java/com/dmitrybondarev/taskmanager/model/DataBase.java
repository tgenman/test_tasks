package com.dmitrybondarev.taskmanager.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase {

    private Map<Integer, Task> tracker;

    public DataBase() {
        this.tracker = new HashMap<>();
    }

    public int addNewTask(String title, String description, Date date, Date time) {
        Task task = new Task(title, description, date, time);
        tracker.put(task.getId(), task);
        return task.getId();
    }

    public Collection<Task> getAllTasks() {
        return tracker.values();
    }

    public Collection<Task> getTasksByKeyWord(String keyWord) {
        Collection<Task> allTasks = tracker.values();
        Collection<Task> resultTasks = new ArrayList<>();
        for (Task task : allTasks) {
            if (task.getTitle().contains(keyWord)
                    ||task.getDescription().contains(keyWord)) {
                resultTasks.add(task);
            }
        }
        return resultTasks;
    }

    public boolean deleteTaskById(int id) {
        if (!tracker.containsKey(id)) return false;
        tracker.remove(id);
        return true;
    }
}
