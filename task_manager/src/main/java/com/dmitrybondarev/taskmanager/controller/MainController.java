package com.dmitrybondarev.taskmanager.controller;

import com.dmitrybondarev.taskmanager.model.DataBase;
import com.dmitrybondarev.taskmanager.view.CommandLine;
import com.dmitrybondarev.taskmanager.view.View;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class MainController {

    private static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";

    private static final Logger log = Logger.getLogger(MainController.class);

    private static Properties properties;

    static {

        try {
            InputStream is = MainController.class.getClassLoader().getResourceAsStream("config.properties");
            properties = new Properties();
            properties.load(is);
        } catch (IOException e) {
            log.fatal("file " + PATH_TO_PROPERTIES + " doesn't find");
        }
    }


        public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        InputValidationService inputValidationService = new InputValidationService();
        DataBaseController dataBaseController = new DataBaseController(dataBase);
        SaverAndLoaderService saverAndLoaderService = new SaverAndLoaderService(dataBase, inputValidationService);

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
        log.info("Program was load successfully");
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
                    log.info("User chose Exit program");
                    isWorking = false;
                    break;
            }
        }
        saverAndLoaderService.saveDataBase();
        view.printExit();
        log.info("Program was exit successfully");
    }

    public static Properties getProperties() {
        return properties;
    }
}
