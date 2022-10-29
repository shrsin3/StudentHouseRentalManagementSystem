package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new HousingApp();;
        } catch (FileNotFoundException e) {
            System.out.println("Application cannot be run! File not found!");
        }
    }

}
