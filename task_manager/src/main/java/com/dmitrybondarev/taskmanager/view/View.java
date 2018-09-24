package com.dmitrybondarev.taskmanager.view;

import com.dmitrybondarev.taskmanager.model.Task;

import java.util.Collection;

public interface View {

    int readPointOfMenu();

    String[] createNewTaskAction();

    int deleteTaskAction();

    String findTaskByKeyWordAction();

    void printWelcomeMessage();

    void printMenu();

    void printIdOfNewTask(int id);

    void printTasks(Collection<Task> list);

    void printCantFindId(int id);

    void printTaskWasDeleted(int id);

    void printExit();
}
