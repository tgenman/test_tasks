package com.dmitrybondarev.taskmanager.controller;

import com.dmitrybondarev.taskmanager.model.DataBase;
import com.dmitrybondarev.taskmanager.model.Task;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Date;

public class SaverAndLoaderServiceTest {

//    private SaverAndLoaderService saverAndLoaderService;
//
//    private DataBase dataBase;
//
//    @Before
//    public void initializeDataBase() {
//        dataBase = new DataBase();
//        InputValidationService inputValidationService = new InputValidationService();
//        saverAndLoaderService = new SaverAndLoaderService(dataBase, inputValidationService);
//    }
//
//    @Test
//    public void testSave() {
//        dataBase.addNewTask("title1", "desc1", new Date(), new Date());
//        dataBase.addNewTask("title2", "desc2", new Date(), new Date());
//        dataBase.addNewTask("title3", "desc3", new Date(), new Date());
//
//        saverAndLoaderService.saveDataBase();
//    }
//
//    @Test
//    public void testLoad() {
//        saverAndLoaderService.loadDataBase();
//        Collection<Task> allTasks = dataBase.getAllTasksAggregatedByDate();
//        System.out.println(allTasks.size());
//        for (Task task : allTasks) {
//            System.out.println(task.toString());
//        }
//    }
//
}