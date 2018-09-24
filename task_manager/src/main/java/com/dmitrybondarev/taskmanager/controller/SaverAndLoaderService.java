package com.dmitrybondarev.taskmanager.controller;

import com.dmitrybondarev.taskmanager.model.DataBase;

public class SaverAndLoaderService {

    private static String pathToDataBaseFile
            = MainController.getProperties().getProperty("pathToDataBaseFile");

    private DataBase dataBase;

//    TODO Implement loging

    public SaverAndLoaderService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void saveDataBase() {
//        TODO implement saving to file
    }

    public void loadDataBase() {
//         TODO implement loading from file
    }

}
