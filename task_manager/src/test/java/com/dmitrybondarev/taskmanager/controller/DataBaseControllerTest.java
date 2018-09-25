package com.dmitrybondarev.taskmanager.controller;


import com.dmitrybondarev.taskmanager.model.DataBase;
import com.dmitrybondarev.taskmanager.model.Task;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class DataBaseControllerTest {

    @Test
    public void  getAllTasksAggregatedByDate() throws ParseException {
//      Test class
        DataBase dataBase = new DataBase();
        InputValidationService iVService = new InputValidationService();
        DataBaseController dataBaseController = new DataBaseController(dataBase);

//      Input values and Run
        dataBaseController.createNewTask("title1", "desc1",
                iVService.stringToDate("27.01.2012"), iVService.stringToTime("13:00"));
        dataBaseController.createNewTask("title2", "desc2",
                iVService.stringToDate("23.04.2014"), iVService.stringToTime("14:00"));
        dataBaseController.createNewTask("title3", "desc3",
                iVService.stringToDate("27.01.2012"), iVService.stringToTime("15:00"));

//      actual results
        Map<Date, List<Task>> result = dataBaseController.getAllTasksAggregatedByDate();
        TreeSet<Date> dates = new TreeSet<>(result.keySet());
        int actulaSizeDates = dates.size();
        Iterator<Date> iteratorDate = dates.iterator();
        Date date1 = iteratorDate.next();
        Date date2 = iteratorDate.next();
        String actualDate1 = InputValidationService.dateToString(date1);
        String actualDate2 = InputValidationService.dateToString(date2);

        List<Task> tasks1 = result.get(date1);
        List<Task> tasks2 = result.get(date2);

        Iterator<Task> iteratorTask1 = tasks1.iterator();
        Iterator<Task> iteratorTask2 = tasks2.iterator();

        String actualTitle11 = iteratorTask1.next().getTitle();
        String actualTitle12 = iteratorTask1.next().getTitle();
        String actualTitle2 = iteratorTask2.next().getTitle();


//      Expected results
        int expectedSizeDates = 2;
        String expectedDate1 = "27.01.2012";
        String expectedDate2 = "23.04.2014";

        String expectedTitle11 = "title1";
        String expectedTitle12 = "title3";
        String expectedTitle2 = "title2";


//      assert
        Assert.assertEquals(expectedSizeDates, actulaSizeDates);
        Assert.assertEquals(expectedDate1, actualDate1);
        Assert.assertEquals(expectedDate2, actualDate2);

        Assert.assertEquals(expectedTitle11, actualTitle11);
        Assert.assertEquals(expectedTitle12, actualTitle12);
        Assert.assertEquals(expectedTitle2, actualTitle2);
    }

}