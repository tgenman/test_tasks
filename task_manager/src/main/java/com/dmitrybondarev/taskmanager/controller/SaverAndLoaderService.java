package com.dmitrybondarev.taskmanager.controller;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class SaverAndLoaderService {

    private static String pathToDataBaseFile
            = MainController.getProperties().getProperty("pathToDataBaseFile");

    private static final Logger log = Logger.getLogger(MainController.class);

    private DataBaseController dataBaseController;

    private InputValidationService inputValidationService;

    public SaverAndLoaderService(DataBaseController dataBaseController, InputValidationService inputValidationService) {
        this.dataBaseController = dataBaseController;
        this.inputValidationService = inputValidationService;
    }

    public void saveDataBase() {
//        TODO implement saving to file
    }

    public void loadDataBase() {
        File f = new File(pathToDataBaseFile);
        if (f.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(pathToDataBaseFile));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] row = line.split("[|]");

                    String title = row[0];
                    String description = row[1];
                    String dateString = row[2];
                    String timeString = row[3];

                    Date date = inputValidationService.stringToDate(dateString);
                    Date time = inputValidationService.stringToTime(timeString);

                    dataBaseController.createNewTask(title, description, date, time);
                }
//            } catch (FileNotFoundException e) {
//                log.error("Cant find file " + pathToDataBaseFile);
            } catch (IOException e) {
                log.error("Some IOException");
            } catch (ParseException e) {
                log.error("Parse error. DataBase has damage.");
            }
            log.info("Successfully load data from file");
        }
        log.info("Cant find file " + pathToDataBaseFile);
    }
}
