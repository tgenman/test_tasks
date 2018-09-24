package com.dmitrybondarev.taskmanager.view;

import com.dmitrybondarev.taskmanager.model.Task;

import java.util.Collection;
import java.util.Scanner;

public class CommandLine implements View {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public int readPointOfMenu() {
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
    public String[] createNewTaskAction() {
//      TODO Create input of date in few steps

        String[] splitString;

        while (true) {
            System.out.println("Please, enter information like pattern");
            System.out.println("TITLE; DESCRIPTION; DATE");

            String newTask = scanner.nextLine();
            splitString = newTask.split(";");

            if (splitString.length != 3
                    || splitString[0].equals("")) {
                printIncorrectInput();
                continue;
            }

            //TODO Add DATE Validation

            break;
        }
        return splitString;
    }

    @Override
    public int deleteTaskAction() {
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
        return id;
    }

    @Override
    public String findTaskByKeyWordAction() {
        System.out.println("Enter key word/phrase for searching");
        String key;

        while (true) {
            key = scanner.nextLine();
            if (!key.equals("")) break;
            printIncorrectInput();
        }

        return key;
    }

    @Override
    public void printWelcomeMessage() {
        System.out.println("Welcome to the Task Manager.");
        System.out.println("You can Create, Read, Find and Delete tasks.");
        System.out.println("Every task has title, description, date and time of execution.");
        System.out.println("Task Manager storage state in file. State save, when program close");
        System.out.println();

    }

    @Override
    public void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. Create new Task");
        System.out.println("2. Show all Tasks");
        System.out.println("3. Delete Task (you must know id");
        System.out.println("4. Find Task by key word or phrase (in title and description");
        System.out.println("5. Exit and Save");
        System.out.println("You should enter from 1 to 5:");
    }

    @Override
    public void printIdOfNewTask(int id) {
        System.out.println("Add task with id = " + id);
        System.out.println();
    }

    @Override
    public void printTasks(Collection<Task> list) {
        if (list.size() == 0) {
            System.out.println("None tasks.");
        }
        for (Task task : list) {
            System.out.println(task.toString());
        }
        System.out.println();
    }

    @Override
    public void printCantFindId(int id) {
        System.out.println("Task manager doesn't contain task with id = " + id);
    }

    @Override
    public void printTaskWasDeleted(int id) {
        System.out.println("Task with id = " + id + " was deleted");
    }

    @Override
    public void printExit() {
        System.out.println("Data saved");
        System.out.println("GoodBye");
    }

    private void printIncorrectInput() {
        System.out.println("Incorrect input. Please, try again");
    }
}
