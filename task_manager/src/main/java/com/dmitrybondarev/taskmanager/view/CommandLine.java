package com.dmitrybondarev.taskmanager.view;

import com.dmitrybondarev.taskmanager.controller.DataBaseController;
import com.dmitrybondarev.taskmanager.controller.InputValidationService;
import com.dmitrybondarev.taskmanager.model.Task;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class CommandLine implements View {

    private InputValidationService inputValidationService;

    private Scanner scanner;

    public CommandLine(InputValidationService inputValidationService, Scanner scanner) {
        this.inputValidationService = inputValidationService;
        this.scanner = scanner;
    }

    @Override
    public int chooseActionFromMenu() {
        printMenu();

        int pointOfMenu;
        while (true) {
            String pointOfMenuString = scanner.nextLine();

            try {
                pointOfMenu = Integer.parseInt(pointOfMenuString);
            } catch (NumberFormatException e) {
                printIncorrectInput();
                continue;
            }

            if (pointOfMenu > 0 && pointOfMenu < 6) break;
            printIncorrectInput();
        }
        return pointOfMenu;
    }

    @Override
    public void createNewTaskAction(DataBaseController dataBaseController) {
//      TODO Create input of date in few steps

        String title;
        String description;
        Date date;
        Date time;

        while (true) {
            System.out.println("Please, enter title");
            title = scanner.nextLine();
            if (inputValidationService.checkTitle(title)) break;
            printIncorrectInput();
        }

        while (true) {
            System.out.println("Please, enter description");
            description = scanner.nextLine();
            if (inputValidationService.checkDescription(description)) break;
            printIncorrectInput();
        }

//        TODO propetries dd.mm.yyyy
        while (true) {
            System.out.println("Please, enter date like pattern dd.mm.yyyy");
            String dateString = scanner.nextLine();
            if (!inputValidationService.checkDate(dateString)) {
                printIncorrectInput();
                continue;
            }
            try {
                date = inputValidationService.stringToDate(dateString);
                break;
            } catch (ParseException e) {
                printIncorrectInput();
            }
        }

//        TODO propetries HH:mm
        while (true) {
            System.out.println("Please, enter date like pattern HH:mm");
            String timeString = scanner.nextLine();
            if (!inputValidationService.checkTime(timeString)){
                printIncorrectInput();
                continue;
            }
            try {
                time = inputValidationService.stringToTime(timeString);
                break;
            } catch (ParseException e) {
                printIncorrectInput();
            }
        }

        int id = dataBaseController.createNewTask(title, description, date, time);

        System.out.println("Added task with id = " + id);


    }

    @Override
    public void printAllTasks(DataBaseController dataBaseController) {
        Map<Date, List<Task>> allTasks = dataBaseController.getAllTasks();

        if (allTasks.size() == 0) {
            System.out.println("None tasks.");
        }
        Set<Date> dates = allTasks.keySet();

        Set<Date> sortedDates = new TreeSet<>(dates);

        for (Date date : sortedDates) {
            System.out.println(InputValidationService.dateToString(date));
            for (Task task: allTasks.get(date)) {
                System.out.println(task.toString());
            }
        }
    }

    @Override
    public void deleteTaskAction(DataBaseController dataBaseController) {
        System.out.println("Enter id of task for deleting:");
        int id;

        while (true) {
            String inputString = scanner.nextLine();

            try {
                id = Integer.parseInt(inputString);
            } catch (NumberFormatException e) {
                printIncorrectInput();
                continue;
            }

            if (id > 0) break;
            printIncorrectInput();
        }
        boolean resultOfDeleting = dataBaseController.deleteTaskById(id);

        if (resultOfDeleting) System.out.println("task with id = " + id + " was deleting");
        else System.out.println("Cant find task with id = " + id);
    }

    @Override
    public void findTaskByKeyWordAction(DataBaseController dataBaseController) {
        System.out.println("Enter key word/phrase for searching");
        String key;

        while (true) {
            key = scanner.nextLine();
            if (!key.equals("")) break;
            printIncorrectInput();
        }

        dataBaseController.getTasksByKeyWord(key);
    }

    @Override
    public void printWelcomeMessage() {
        System.out.println("Welcome to the TASK MANAGER.");
        System.out.println("You can Create, Read, Find and Delete tasks.");
        System.out.println("Every task has title, description, date and time of execution.");
        System.out.println("Task Manager storage state in file. State save, when program close");
        System.out.println();

    }

    @Override
    public void printExit() {
        System.out.println("Data saved");
        System.out.println("GoodBye");
    }

    private void printIncorrectInput() {
        System.out.println("Incorrect input. Please, try again");
    }

    private void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. CREATE new Task");
        System.out.println("2. SHOW all Tasks");
        System.out.println("3. DELETE Task (you must know id");
        System.out.println("4. FIND Task by key word or phrase (in title and description");
        System.out.println("5. EXIT and Save");
        System.out.println("You should enter from 1 to 5:");
    }
}
