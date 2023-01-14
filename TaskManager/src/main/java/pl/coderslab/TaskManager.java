package pl.coderslab;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.SortedMap;

public class TaskManager {
    public static void main(String[] args) {

    /* lista dostępnych do wyboru opcji: **/
//        System.out.println(ConsoleColors.BLUE + "Please select an option" + ConsoleColors.RESET);
//        optionsList();

    /* wybór opcji: **/
        String option="";
        Scanner scan = new Scanner(System.in);
        System.out.println(ConsoleColors.BLUE + "Please select an option" + ConsoleColors.RESET);
        optionsList();
            while (!option.equals("exit")){
                switch (option= scan.nextLine()) {
                    case "list":
                        tasksList();
                        break;
                    case "exit":
                        System.out.println(ConsoleColors.RED + "bye, bye" + ConsoleColors.RESET);
                        break;
                    default:
                        System.out.println("wybierz jedną z dostępnych opcji!");

            }
        }
    }

    /* METODY GŁÓWNE **/
    /* metoda z listą opcji do wybrou:**/
    public static void optionsList() {
        String optionsList[] = {"add", "remove", "list", "exit"};
        for (int i = 0; i < optionsList.length; i++) {
            System.out.println(optionsList[i]);
        }
    }

    /* metoda zamieniająca plik CSV na tablicę dwuwymiarową:**/
    public static String[][] listArray() {
        try {
            File file = new File("tasks.csv");
            Scanner tasksFromFile = new Scanner(file);
            int counter=0;
            String[] liniaStr= new String[rows()];
            while (tasksFromFile.hasNextLine()) {
                String linia=tasksFromFile.nextLine();
                liniaStr[counter]=linia;
                String[] str= liniaStr[counter].split(",");
                for (int i = 0; i < cols(); i++) {
                    tasks[counter][i]=str[i];
                }
                counter++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku tasks.");
        }
        return tasks;
    }

    /* metoda wyświetlająca listę zadań**/
    public static void tasksList() {
        for (int i = 0; i < rows(); i++) {
            StringBuilder linia= new StringBuilder();
            for (int j = 0; j < cols(); j++) {
                linia.append(listArray()[i][j] + ",");
            }
            String lineNr=Integer.toString(i);
            System.out.println(lineNr + " " + ":" + " " + linia);
        }
    }

    /*dodająca zadanie**/


    /* METODY POMOCNICZE**/

    /* liczba wierszy w tablicy tasks**/
    public static int rows() {
        int rows=0;
        try {
            File file = new File("tasks.csv");
            Scanner tasksFromFile = new Scanner(file);
            long lines = 0;
            Path path = Paths.get("tasks.csv");
            try {
                lines = Files.lines(path).count();
            } catch (IOException e) {
                e.printStackTrace();
            }
            rows = (int) lines;

        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku tasks.");
        }
        return rows;
    }

    /* liczba kolumn w tablicy tasks**/
    public static int cols(){
        int cols=0;
        try {
            File file = new File("tasks.csv");
            Scanner tasksline = new Scanner(file);
            String tasks=tasksline.nextLine();
            String[] tasksCols=tasks.split(",");
            cols=tasksCols.length;

        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku tasks.");
        }
        return cols;
    }

    /* deklaracja dwuwymiarowej tablicy**/
    static String[][] tasks= new String[rows()][cols()];


}

