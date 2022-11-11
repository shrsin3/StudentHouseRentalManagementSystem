package ui;

import model.House;
import model.HouseList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Gui extends JFrame {

    private static final String JSON_STORE = "./data/houseList.json";
    private HouseList myHouseList;
    private JInternalFrame controlPanel;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    int regNumber;

    public Gui() throws FileNotFoundException {

        myHouseList = new HouseList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
//        JLabel label = new JLabel("Student House Rental Management System", SwingConstants.CENTER);
//        label.setForeground(Color.BLACK);
//        label.setFont(new Font("Helvetica", Font.BOLD, 24));
//        label.setVerticalAlignment(JLabel.TOP);

        controlPanel = new JInternalFrame("Housing Application", false, false, false, false);
        controlPanel.setLayout(new BorderLayout());

        super.setTitle("Student House Rental Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700,700);
        controlPanel.pack();
        controlPanel.setVisible(true);
        this.add(controlPanel);
        //this.add(label, BorderLayout.CENTER);
        addButtonPanel();
        this.setVisible(true);
    }

    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,2));
        buttonPanel.add(new JButton(new RegisterUser()));
        buttonPanel.add(new JButton(new SaveHouses()));
        buttonPanel.add(new JButton(new LoadHouses()));
        buttonPanel.add(new JButton(new RemoveHouse()));
        buttonPanel.add(new JButton(new UserNumber()));
//        buttonPanel.add(new JButton(new DisplayHouses()));

        controlPanel.add(buttonPanel, BorderLayout.WEST);
    }

    private class RegisterUser extends AbstractAction {

        RegisterUser() {
            super("Add House");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                UserRegistration userRegister = new UserRegistration(null);
                House house = userRegister.registerHouse();
                myHouseList.addHouse(house);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Fill all fields", "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class SaveHouses extends AbstractAction {

        SaveHouses() {
            super("Save Houses");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                jsonWriter.open();
                jsonWriter.write(myHouseList);
                jsonWriter.close();
                System.out.println("House List has been saved " + " to " + JSON_STORE);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private class LoadHouses extends AbstractAction {

        LoadHouses() {
            super("Load Houses");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                myHouseList = jsonReader.read();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private class UserNumber extends AbstractAction {

        UserNumber() {
            super("UserNumber");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            RegistrationNumber reg = new RegistrationNumber(null);
            regNumber = reg.getRegistrationNumber();
            System.out.println(regNumber);

        }
    }

    private class RemoveHouse extends AbstractAction {

        RemoveHouse() {
            super("Remove House");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

            Boolean houseFound = false;
            House houseToBeRemoved = null;

            for (House house : myHouseList.getHouseList()) {
                if (regNumber == house.getRegistrationNumber()) {
                    houseToBeRemoved = house;
                    houseFound = true;
                    break;
                }
            }
            if (houseFound) {
                myHouseList.removeHouseByValue(houseToBeRemoved);
            } else {
                JOptionPane.showMessageDialog(null, "House not registered!",
                        "System Error", JOptionPane.ERROR_MESSAGE);
            }



        }
    }


}
