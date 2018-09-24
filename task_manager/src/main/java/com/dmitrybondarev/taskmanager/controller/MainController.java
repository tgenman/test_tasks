package com.dmitrybondarev.taskmanager.controller;

import com.dmitrybondarev.taskmanager.model.DataBase;
import com.dmitrybondarev.taskmanager.model.Task;
import com.dmitrybondarev.taskmanager.service.SaverAndLoaderService;
import com.dmitrybondarev.taskmanager.view.CommandLine;
import com.dmitrybondarev.taskmanager.view.View;

import java.text.ParseException;
import java.util.Collection;

public class MainController {

    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        SaverAndLoaderService saverAndLoaderService = new SaverAndLoaderService(dataBase);
        View view = new CommandLine();

        MainController mainController = new MainController(
                view,
                saverAndLoaderService,
                dataBase);

        mainController.startProgram();
    }

    private View view;

    private SaverAndLoaderService saverAndLoaderService;

    private DataBase dataBase;

    public MainController(View view,
                          SaverAndLoaderService saverAndLoaderService,
                          DataBase dataBase) {
        this.view = view;
        this.saverAndLoaderService = saverAndLoaderService;
        this.dataBase = dataBase;
    }

    public void startProgram() {
        saverAndLoaderService.loadDataBase();
        boolean isWorking = true;

        view.printWelcomeMessage();

        while (isWorking) {
            view.printMenu();
            int pointOfMenu = view.readPointOfMenu();
            switch (pointOfMenu) {
                case 1:
                    controlCreateNewTask();
                    break;
                case 2:
                    controlShowAllTasks();
                    break;
                case 3:
                    controlDeleteTaskById();
                    break;
                case 4:
                    controlFindTaskByKeyWord();
                    break;
                case 5:
                    controlSaveAndExit();
                    isWorking = false;
                    break;
            }
        }
    }

    private void controlCreateNewTask() {
        String[] newTask = view.createNewTaskAction();
        String title = newTask[0].trim();
        String description = newTask[1].trim();
        String date = newTask[2].trim();
        String time = newTask[3].trim();

        try {
            dataBase.addNewTask(title, description, date, time);
        } catch (ParseException e) {
//            TODO Where catch this exception?
            e.printStackTrace();
        }
    }

    private void controlShowAllTasks() {
//      TODO Add Date sorting
        Collection<Task> allTasks = dataBase.getAllTasks();
        view.printTasks(allTasks);
    }

    private void controlDeleteTaskById() {
        int id = view.deleteTaskAction();
        boolean result = dataBase.deleteTaskById(id);
        if (result) view.printTaskWasDeleted(id);
        else view.printCantFindId(id);
    }

    private void controlFindTaskByKeyWord() {
        String keyWord = view.findTaskByKeyWordAction();
        Collection<Task> tasksByKeyWord = dataBase.getTasksByKeyWord(keyWord);
        view.printTasks(tasksByKeyWord);
    }

    private void controlSaveAndExit() {
        saverAndLoaderService.saveDataBase();
        view.printExit();
    }
}
