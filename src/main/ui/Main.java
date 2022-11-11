package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.FileNotFoundException;

public class Main {


    public static void main(String[] args) {
        try {
            new Gui();
        } catch (FileNotFoundException e) {
            System.out.println("Application cannot be run! File not found!");
        }
//        try {
//            new HousingApp();
//        } catch (FileNotFoundException e) {
//            System.out.println("Application cannot be run! File not found!");
//        }
//    }

    }
}
