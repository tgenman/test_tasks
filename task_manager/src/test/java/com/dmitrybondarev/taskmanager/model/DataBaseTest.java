package com.dmitrybondarev.taskmanager.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class DataBaseTest {

    private DataBase dataBase;

    @Before
    public void initDataBase() {
//      Test class
        dataBase = new DataBase();

//      Input values and Run
        dataBase.addNewTask("title1", "desc1", new Date(), new Date());
        dataBase.addNewTask("title2", "desc2", new Date(), new Date());
        dataBase.addNewTask("title3", "desc3", new Date(), new Date());
    }

    @Test
    public void  testCreateTask() {
//      Expected results
        int expectedSize = 3;
        String expectedTitle1 = "title1";
        String expectedTitle2 = "title2";
        String expectedTitle3 = "title3";
        String expectedDesc1 = "desc1";
        String expectedDesc2 = "desc2";
        String expectedDesc3 = "desc3";

//      actual results
        Collection<Task> allTasks = dataBase.getAllTasks();
        int actualSize = allTasks.size();
        Iterator<Task> iterator = allTasks.iterator();
        Task task1 = iterator.next();
        String actualTitle1 = task1.getTitle();
        String actualDesc1 = task1.getDescription();
        Task task2 = iterator.next();
        String actualTitle2 = task2.getTitle();
        String actualDesc2 = task2.getDescription();
        Task task3 = iterator.next();
        String actualTitle3 = task3.getTitle();
        String actualDesc3 = task3.getDescription();

//      assert
        Assert.assertEquals(expectedSize, actualSize);
        Assert.assertEquals(expectedTitle1, actualTitle1);
        Assert.assertEquals(expectedTitle2, actualTitle2);
        Assert.assertEquals(expectedTitle3, actualTitle3);
        Assert.assertEquals(expectedDesc1, actualDesc1);
        Assert.assertEquals(expectedDesc2, actualDesc2);
        Assert.assertEquals(expectedDesc3, actualDesc3);
    }

//    TODO Fix test

//    @Test
//    public void  testGetTaskById() {
////      Expected results
//        Task task = dataBase.getAllTasks().iterator().next();
//        int id = task.getId();
//        String expectedTitle = task.getTitle();
//        String expectedDesc = task.getDescription();
//
////      actual results
////        Task taskById = dataBase.getTaskById(id);
//        String actualTitle = taskById.getTitle();
//        String actualDesc = taskById.getDescription();
//
////      assert
//        Assert.assertEquals(expectedTitle, actualTitle);
//        Assert.assertEquals(expectedDesc, actualDesc);
//    }

    @Test
    public void  testGetTaskByKeyWord() {
//      Input values
        String keyWord1 = "tle2";
        String keyWord2 = "sc1";
        String keyWord3 = "title";

//      Expected results
        String expectedTitle1 = "title2";
        String expectedTitle2 = "title1";
        String expectedDesc1 = "desc2";
        String expectedDesc2 = "desc1";
        int expectedSize1 = 1;
        int expectedSize2 = 1;
        int expectedSize3 = 3;

//      actual results
        Collection<Task> tasksByKeyWord1 = dataBase.getTasksByKeyWord(keyWord1);
        Collection<Task> tasksByKeyWord2 = dataBase.getTasksByKeyWord(keyWord2);
        Collection<Task> tasksByKeyWord3 = dataBase.getTasksByKeyWord(keyWord3);
        int actualSize1 = tasksByKeyWord1.size();
        int actualSize2 = tasksByKeyWord2.size();
        int actualSize3 = tasksByKeyWord3.size();
        Task actualTask1 = tasksByKeyWord1.iterator().next();
        String actualTitle1 = actualTask1.getTitle();
        String actualDesc1 = actualTask1.getDescription();
        Task actualTask2 = tasksByKeyWord2.iterator().next();
        String actualTitle2 = actualTask2.getTitle();
        String actualDesc2 = actualTask2.getDescription();

//      assert
        Assert.assertEquals(expectedSize1,actualSize1);
        Assert.assertEquals(expectedSize2,actualSize2);
        Assert.assertEquals(expectedSize3,actualSize3);
        Assert.assertEquals(expectedTitle1, actualTitle1);
        Assert.assertEquals(expectedTitle2, actualTitle2);
        Assert.assertEquals(expectedDesc1, actualDesc1);
        Assert.assertEquals(expectedDesc2, actualDesc2);
    }

    @Test
    public void  testDeleteTaskById() {
//      Expected results
        int expectedSize = 2;

//      Run
        Task task = dataBase.getAllTasks().iterator().next();
        int id = task.getId();
        dataBase.deleteTaskById(id);

//      actual results
        int actualSize = dataBase.getAllTasks().size();

//      assert
        Assert.assertEquals(expectedSize, actualSize);
    }





}