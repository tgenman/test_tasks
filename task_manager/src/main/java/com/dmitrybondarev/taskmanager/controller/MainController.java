package com.dmitrybondarev.taskmanager.controller;

import com.dmitrybondarev.taskmanager.model.DataBase;
import com.dmitrybondarev.taskmanager.view.CommandLine;
import com.dmitrybondarev.taskmanager.view.View;

public class MainController {

    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        SaverAndLoaderService saverAndLoaderService = new SaverAndLoaderService(dataBase);
        DataBaseController dataBaseController = new DataBaseController(dataBase, saverAndLoaderService);
        View view = new CommandLine();

        MainController mainController = new MainController(
                view,
                saverAndLoaderService,
                dataBaseController);

        mainController.startProgram();
    }

    private View view;

    private SaverAndLoaderService saverAndLoaderService;

    private DataBaseController dataBaseController;

    MainController(
            View view,
            SaverAndLoaderService saverAndLoaderService,
            DataBaseController dataBaseController) {
        this.view = view;
        this.saverAndLoaderService = saverAndLoaderService;
        this.dataBaseController = dataBaseController;
    }

    void startProgram() {
        saverAndLoaderService.loadDataBase();
        view.printWelcomeMessage();

        boolean isWorking = true;

        while (isWorking) {
            int pointOfMenu = view.chooseActionFromMenu();
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
                    isWorking = false;
                    break;
            }
        }
        saverAndLoaderService.saveDataBase();
        view.printExit();
    }

    private void controlCreateNewTask() {
        view.createNewTaskAction(dataBaseController);
    }

    private void controlShowAllTasks() {
        view.printAllTasks(dataBaseController);
    }

    private void controlDeleteTaskById() {
        view.deleteTaskAction(dataBaseController);
    }

    private void controlFindTaskByKeyWord() {
        view.findTaskByKeyWordAction(dataBaseController);
    }

}
