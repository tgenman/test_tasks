package com.dmitrybondarev.taskmanager.view;

import com.dmitrybondarev.taskmanager.controller.DataBaseController;
import com.dmitrybondarev.taskmanager.controller.InputValidationService;
import com.dmitrybondarev.taskmanager.controller.MainController;
import com.dmitrybondarev.taskmanager.model.Task;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class CommandLine implements View {

    private final static int SIZE_OF_MENU = 5;

    private static final Logger log = Logger.getLogger(CommandLine.class);

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
                if (pointOfMenu > 0 && pointOfMenu <= SIZE_OF_MENU) {
                    log.info("User enter correct point of menu = " + pointOfMenu);
                    break;
                }
                printIncorrectInput();
                log.info("User enter incorrect point of menu = " + pointOfMenuString);
            } catch (NumberFormatException e) {
                printIncorrectInput();
                log.info("User enter incorrect point of menu = " + pointOfMenuString);
            }
        }
        return pointOfMenu;
    }

    @Override
    public void createNewTaskAction(DataBaseController dataBaseController) {
        String title;
        String description;
        Date date;
        Date time;

        while (true) {
            System.out.println("Please, enter title");
            title = scanner.nextLine();
            if (inputValidationService.checkTitle(title)) break;
            printIncorrectInput();

            log.info("User enter incorrect title =" + title);
        }

        while (true) {
            System.out.println("Please, enter description");
            description = scanner.nextLine();
            if (inputValidationService.checkDescription(description)) break;
            printIncorrectInput();

            log.info("User enter incorrect description =" + description);

        }

        while (true) {
            System.out.println("Please, enter date like pattern "
                    + MainController.getProperties().getProperty("dateFormat"));
            String dateString = scanner.nextLine();
            if (!inputValidationService.checkDate(dateString)) {
                printIncorrectInput();

                log.info("User enter incorrect date =" + dateString);
                continue;
            }
            try {
                date = inputValidationService.stringToDate(dateString);
                break;
            } catch (ParseException e) {
                printIncorrectInput();

                log.info("User enter incorrect date =" + dateString);
            }
        }

        while (true) {
            System.out.println("Please, enter date like pattern "
                    + MainController.getProperties().getProperty("timeFormat").toLowerCase());
            String timeString = scanner.nextLine();
            if (!inputValidationService.checkTime(timeString)){
                printIncorrectInput();

                log.info("User enter incorrect date =" + timeString);
                continue;
            }
            try {
                time = inputValidationService.stringToTime(timeString);
                break;
            } catch (ParseException e) {
                printIncorrectInput();

                log.info("User enter incorrect date =" + timeString);
            }
        }

        int id = dataBaseController.createNewTask(title, description, date, time);

        System.out.println("Added task with id = " + id);

        log.info("User add task:" + id + "  "
                + title.toUpperCase() + "  "
                + description + "  "
                + InputValidationService.dateToString(date) + "  "
                + InputValidationService.timeToString(time));
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

        log.info("User print all tasks");
    }

    @Override
    public void deleteTaskAction(DataBaseController dataBaseController) {
        System.out.println("Enter id of task for deleting:");
        int id;

        while (true) {
            String inputString = scanner.nextLine();

            try {
                id = Integer.parseInt(inputString);
                if (id > 0) {
                    break;
                }
                printIncorrectInput();
                log.info("User enter incorrect id for deleting = " + id);
            } catch (NumberFormatException e) {
                printIncorrectInput();
                log.info("User enter incorrect id for deleting = " + inputString);
            }
        }
        boolean resultOfDeleting = dataBaseController.deleteTaskById(id);

        if (resultOfDeleting) {
            System.out.println("task with id = " + id + " was deleting");
            log.info("User delete task with id = " + id);
        }
        else {
            System.out.println("Cant find task with id = " + id);
            log.info("Task with id = " + id + " isn't finding");
        }
    }

    @Override
    public void findTaskByKeyWordAction(DataBaseController dataBaseController) {
        System.out.println("Enter key word/phrase for searching");
        String key;

        while (true) {
            key = scanner.nextLine();
            if (!key.equals("")) break;
            printIncorrectInput();
            log.info("User enter incorrect key for searching = " + key);
        }

        Collection<Task> tasksByKeyWord = dataBaseController.getTasksByKeyWord(key);
        if (tasksByKeyWord.size() == 0) System.out.println("None tasks with key = " + key);
        for (Task task : tasksByKeyWord) {
            System.out.println(task.toString());
        }

        log.info("Task manager show tasks, which contain key = " + key);
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
        System.out.println();
        System.out.println("Menu:");
        System.out.println("1. CREATE new Task");
        System.out.println("2. SHOW all Tasks");
        System.out.println("3. DELETE Task (you must know id");
        System.out.println("4. FIND Task by key word or phrase (in title and description");
        System.out.println("5. EXIT and Save");
        System.out.println();
        System.out.println("You should enter from 1 to 5:");
    }
}
