package com.dmitrybondarev.taskmanager.controller;


import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

public class InputValidationServiceTest {

    @Test
    public void testConvertDateCycle() throws ParseException {
//      Test class
        InputValidationService inputValidationService = new InputValidationService();

//      Input and Expect value
        String date = "27.01.2012";

//      Run and Actual value
        Date date1 = inputValidationService.stringToDate(date);
        String actualDate = InputValidationService.dateToString(date1);

//      Assertion
        Assert.assertEquals(date, actualDate);
    }

    @Test
    public void testConvertTimeCycle() throws ParseException {
//      Test class
        InputValidationService inputValidationService = new InputValidationService();

//      Input and Expect value
        String time = "23:00";

//      Run and Actual value
        Date time1 = inputValidationService.stringToTime(time);
        String actualTime = InputValidationService.timeToString(time1);

//      Assertion
        Assert.assertEquals(time, actualTime);
    }

    @Test
    public void testCheckTitleAndDescriptionWhenTheyAreWrong() throws ParseException {
//      Test class
        InputValidationService inputValidationService = new InputValidationService();

//      Input
        String inputTitle = "";
        String inputDescription = "";

//      Run and Actual value
        boolean resultTitle = inputValidationService.checkTitle(inputTitle);
        boolean resultDescription = inputValidationService.checkDescription(inputDescription);

//      Assertion
        Assert.assertFalse(resultTitle);
        Assert.assertFalse(resultDescription);
    }

    @Test
    public void testCheckTitleAndDescriptionWhenTheyAreNotWrong() throws ParseException {
//      Test class
        InputValidationService inputValidationService = new InputValidationService();

//      Input
        String inputTitle = "title";
        String inputDescription = "desc";

//      Run and Actual value
        boolean resultTitle = inputValidationService.checkTitle(inputTitle);
        boolean resultDescription = inputValidationService.checkDescription(inputDescription);

//      Assertion
        Assert.assertTrue(resultTitle);
        Assert.assertTrue(resultDescription);
    }

    @Test
    public void testCheckDateWhenItIsNotWrong() throws ParseException {
//      Test class
        InputValidationService inputValidationService = new InputValidationService();

//      Input
        String input = "23.01.2018";

//      Run and Actual value
        boolean result = inputValidationService.checkDate(input);

//      Assertion
        Assert.assertTrue(result);
    }

    @Test
    public void testCheckDateWhenItIsWrong() throws ParseException {
//      Test class
        InputValidationService inputValidationService = new InputValidationService();

//      Input
        String input1 = "23:01.2018";
        String input2 = "33.01.2018";
        String input3 = "-23.01.2018";
        String input4 = "23.013.2018";
        String input5 = "23.-01.2018";
        String input6 = "23.01.-2018";

//      Run and Actual value
        boolean result1 = inputValidationService.checkDate(input1);
        boolean result2 = inputValidationService.checkDate(input2);
        boolean result3 = inputValidationService.checkDate(input3);
        boolean result4 = inputValidationService.checkDate(input4);
        boolean result5 = inputValidationService.checkDate(input5);
        boolean result6 = inputValidationService.checkDate(input6);

//      Assertion
        Assert.assertFalse(result1);
        Assert.assertFalse(result2);
        Assert.assertFalse(result3);
        Assert.assertFalse(result4);
        Assert.assertFalse(result5);
        Assert.assertFalse(result6);
    }

    @Test
    public void testCheckTimeWhenItIsNotWrong() throws ParseException {
//      Test class
        InputValidationService inputValidationService = new InputValidationService();

//      Input
        String input = "23:00";

//      Run and Actual value
        boolean result = inputValidationService.checkTime(input);

//      Assertion
        Assert.assertTrue(result);
    }

    @Test
    public void testCheckTimeWhenItIsWrong() throws ParseException {
//      Test class
        InputValidationService inputValidationService = new InputValidationService();

//      Input
        String input1 = "25:01";
        String input2 = "-23:01";
        String input3 = "23:-01";
        String input4 = "23:61";

//      Run and Actual value
        boolean result1 = inputValidationService.checkTime(input1);
        boolean result2 = inputValidationService.checkTime(input2);
        boolean result3 = inputValidationService.checkTime(input3);
        boolean result4 = inputValidationService.checkTime(input4);

//      Assertion
        Assert.assertFalse(result1);
        Assert.assertFalse(result2);
        Assert.assertFalse(result3);
        Assert.assertFalse(result4);
    }
}