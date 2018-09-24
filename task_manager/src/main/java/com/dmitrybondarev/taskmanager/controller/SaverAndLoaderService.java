package com.dmitrybondarev.taskmanager.controller;

import com.dmitrybondarev.taskmanager.model.DataBase;
import com.dmitrybondarev.taskmanager.model.Task;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;

public class SaverAndLoaderService {

    private static String pathToDataBaseFile
            = MainController.getProperties().getProperty("pathToDataBaseFile");

    private static final Logger log = Logger.getLogger(MainController.class);

    private DataBase dataBase;

    private InputValidationService inputValidationService;

    public SaverAndLoaderService(DataBase dataBase, InputValidationService inputValidationService) {
        this.dataBase = dataBase;
        this.inputValidationService = inputValidationService;
    }

    public void saveDataBase() {
        File f = new File(pathToDataBaseFile);
        if (f.exists()) {
            f.delete();
        }

        Collection<Task> allTasks = dataBase.getAllTasks();

        try {
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(pathToDataBaseFile), "Cp866"));
            for (Task task : allTasks) {
                String buffer = task.getTitle() + ";"
                                + task.getDescription() + ";"
                                + InputValidationService.dateToString(task.getDate()) + ";"
                                + InputValidationService.timeToString(task.getTime());
                pw.println(buffer);
            }
            pw.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        log.info("Successfully save data to file " + pathToDataBaseFile);
    }

    public void loadDataBase() {
        File f = new File(pathToDataBaseFile);
        if (f.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(pathToDataBaseFile));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] row = line.split(";");

                    String title = row[0];
                    String description = row[1];
                    String dateString = row[2];
                    String timeString = row[3];

                    Date date = inputValidationService.stringToDate(dateString);
                    Date time = inputValidationService.stringToTime(timeString);

                    dataBase.addNewTask(title, description, date, time);
                }
//            } catch (FileNotFoundException e) {
//                log.error("Cant find file " + pathToDataBaseFile);
            } catch (IOException e) {
                log.error("Some IOException");
            } catch (ParseException e) {
                log.error("Parse error. DataBase has damage.");
            }
            log.info("Successfully load data from file " + pathToDataBaseFile);
        } else {
            log.info("Cant find file " + pathToDataBaseFile);
        }
    }
}
