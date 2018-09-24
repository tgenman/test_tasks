package com.dmitrybondarev.taskmanager.controller;

import com.dmitrybondarev.taskmanager.model.DataBase;
import com.dmitrybondarev.taskmanager.view.CommandLine;
import com.dmitrybondarev.taskmanager.view.View;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class MainController {

    private static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";

    private static Properties properties;

    static {
        try {
            Properties prop = new Properties();
            FileInputStream fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);
            properties = prop;
        } catch (IOException e) {
//            TODO log "file PATH_TO_PROPERTIES doesn't find"
            System.out.println("Error");
        }
    }

    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        SaverAndLoaderService saverAndLoaderService = new SaverAndLoaderService(dataBase);
        DataBaseController dataBaseController = new DataBaseController(dataBase);

        InputValidationService inputValidationService = new InputValidationService();
        Scanner scanner = new Scanner(System.in);
        View view = new CommandLine(inputValidationService, scanner);

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

    private static Properties loadProperties() throws IOException {
        Properties prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
        prop.load(fileInputStream);
        return prop;
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

    public static Properties getProperties() {
        return properties;
    }
}
