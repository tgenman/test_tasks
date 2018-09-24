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
                    view.createNewTaskAction(dataBaseController);
                    break;
                case 2:
                    view.printAllTasks(dataBaseController);
                    break;
                case 3:
                    view.deleteTaskAction(dataBaseController);
                    break;
                case 4:
                    view.findTaskByKeyWordAction(dataBaseController);
                    break;
                case 5:
                    isWorking = false;
                    break;
            }
        }
        saverAndLoaderService.saveDataBase();
        view.printExit();
    }
}
