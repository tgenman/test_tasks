package com.dmitrybondarev.taskmanager.model;

import com.dmitrybondarev.taskmanager.model.exception.TaskNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DataBase {

    private Map<Integer, Task> tracker;

    public DataBase() {
        this.tracker = new HashMap<>();
    }

    public int createTask(String title, String description, Date dateAndTime) {
        Task task = new Task(title, description, dateAndTime);
        tracker.put(task.getId(), task);
        return task.getId();
    }

    public Collection<Task> getAllTasks() {
        return tracker.values();
    }

    public Task getTaskById(int id) {
        if (!tracker.containsKey(id)) {
            throw new TaskNotFoundException();
        }
        return tracker.get(id);
    }

    public Collection<Task> getTasksByKeyWord(String keyWord) {
        Collection<Task> allTasks = getAllTasks();
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
