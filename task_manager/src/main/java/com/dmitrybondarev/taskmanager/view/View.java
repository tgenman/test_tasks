package com.dmitrybondarev.taskmanager.view;

import com.dmitrybondarev.taskmanager.controller.DataBaseController;

public interface View {

    int chooseActionFromMenu();

    void createNewTaskAction(DataBaseController dataBaseController);

    void printAllTasks(DataBaseController dataBaseController);

    void deleteTaskAction(DataBaseController dataBaseController);

    void findTaskByKeyWordAction(DataBaseController dataBaseController);

    void printWelcomeMessage();

    void printExit();
}
