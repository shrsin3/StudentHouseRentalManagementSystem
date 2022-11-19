package ui;

import model.House;
import model.HouseList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

//Represents the application's main window frame i.e. the application's graphical user interface
public class Gui extends JFrame {

    private static final String JSON_STORE = "./data/houseList.json";
    private HouseList myHouseList;
    private JInternalFrame controlPanel;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private int regNumber;
    private JDesktopPane desktop;

    // Method was taken and modified from AlarmControllerUI() in:
    // https://github.students.cs.ubc.ca/CPSC210/AlarmSystem/blob/main/src/main/ca/ubc/cpsc210/alarm/ui/
    // AlarmControllerUI.java
    //EFFECTS: Creates the main window of the application with the internal window with buttons, title and menu bar
    public Gui() throws FileNotFoundException {

        myHouseList = new HouseList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());

        setContentPane(desktop);
        controlPanel = new JInternalFrame("Housing Application", false, false, false, false);
        controlPanel.setLayout(new GridLayout());

        super.setTitle("Student House Rental Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200,1000);
        controlPanel.pack();
        controlPanel.setVisible(true);
        controlPanel.setLocation(0,50);
        desktop.add(controlPanel);
        controlPanel.setSize(500,500);
        createLabel();
        addButtonPanel();
        addMenu();
        this.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: adds the title label to the application
    public void createLabel() {
        JLabel label = new JLabel("Student House Rental Management System", SwingConstants.CENTER);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Helvetica", Font.BOLD, 24));
        label.setVerticalAlignment(JLabel.TOP);
        label.setBounds(new Rectangle(new Point(350, 10), label.getPreferredSize()));
        this.add(label, BorderLayout.CENTER);
    }

    // Method was taken and modified from addButtonPanel() in:
    // https://github.students.cs.ubc.ca/CPSC210/AlarmSystem/blob/main/src/main/ca/ubc/cpsc210/alarm/ui/
    // AlarmControllerUI.java
    //MODIFIES: controlPanel
    //EFFECTS: adds the buttons to the application
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,2));
        buttonPanel.add(new JButton(new RegisterUser()));
        buttonPanel.add(new JButton(new SaveHouses()));
        buttonPanel.add(new JButton(new LoadHouses()));
        buttonPanel.add(new JButton(new RemoveHouse()));
        buttonPanel.add(new JButton(new UserNumber()));
        buttonPanel.add(new JButton(new DisplayHouses()));

        controlPanel.add(buttonPanel, BorderLayout.WEST);
    }

    // Method was taken and modified from addMenu() in:
    // https://github.students.cs.ubc.ca/CPSC210/AlarmSystem/blob/main/src/main/ca/ubc/cpsc210/alarm/ui/
    // AlarmControllerUI.java
    //MODIFIES: this
    //EFFECTS: adds the menu to the application
    public void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu userMenu = new JMenu("User");
        userMenu.setMnemonic('U');
        addMenuItem(userMenu, new AddUserAction(),
                KeyStroke.getKeyStroke("control U"));
        menuBar.add(userMenu);
        setJMenuBar(menuBar);
    }


    // Method was taken and modified from addMenuItem() in:
    // https://github.students.cs.ubc.ca/CPSC210/AlarmSystem/blob/main/src/main/ca/ubc/cpsc210/alarm/ui/
    // AlarmControllerUI.java
    // EFFECTS: Adds an item with given handler to the given menu
    private void addMenuItem(JMenu theMenu, AbstractAction action, KeyStroke accelerator) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.setMnemonic(menuItem.getText().charAt(0));
        menuItem.setAccelerator(accelerator);
        theMenu.add(menuItem);
    }

    //Represents action to be taken when user wants to add a new user
    private class AddUserAction extends AbstractAction {

        //EFFECTS: creates an action with the given name
        AddUserAction() {
            super("Add House");
        }

        //EFFECTS: performs the following action when user selects the "Add House" option from the menu
        @Override
        public void actionPerformed(ActionEvent evt) {
            new User();
        }
    }

    //Represents action to be taken when user wants to add a new user
    private class RegisterUser extends AbstractAction {


        //EFFECTS: creates an action with the given name
        RegisterUser() {
            super("Add House");
        }

        //EFFECTS: performs the following action when user selects the "Add House" button on the application.
        // If the information of the user is not in the appropriate format or all the fields are not filled then
        // shows an error message
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                new User();


            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter valid Input!", "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Represents the form that pops when the User clicks on Add House button or option from the menu
    public class User {

        JTextField ownerName = new JTextField();
        JTextField ownerGender = new JTextField();
        JTextField address = new JTextField();
        JTextField city = new JTextField();
        JTextField rentAmount = new JTextField();

        //EFFECTS: creates dialog box with fields to enter all the required information to add a house to the
        // application
        public User() {

            Object[] userInput = {"Name", ownerName, "Gender", ownerGender, "Address", address, "City", city,
                    "Rent Amount", rentAmount};
            int result = JOptionPane.showConfirmDialog(Gui.this, userInput, "House Details",
                    JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                parseToHouse();
                popUpImage();
            }
        }

        //MODIFIES:  myHouseList
        //EFFECTS: parses the information added by the user into appropriate formats and adds the list to
        // myHouseList if all the fields have been filled otherwise shows an error message to fill all fields
        public void parseToHouse() {

            String name = ownerName.getText();
            String gender = ownerGender.getText();
            String ownerAddress = address.getText();
            String houseCity = city.getText();
            Double rent = Double.parseDouble((rentAmount.getText()));

            if (name.isEmpty() || gender.isEmpty() || ownerAddress.isEmpty() || houseCity.isEmpty() || rent.isNaN()) {
                JOptionPane.showMessageDialog(Gui.this, "System Error", "Enter Valid Input!",
                        JOptionPane.ERROR_MESSAGE);
            }

            House house = new House(ownerAddress, houseCity, rent, name, gender, false);
            myHouseList.addHouse(house);

        }

        //EFFECTS: pops an image when a house is added to the myHouseList
        public void popUpImage() {
            JFrame pop = new JFrame();
            pop.setLayout(null);
            pop.setUndecorated(true);
            JLabel image = new JLabel(new ImageIcon("./data/house-add.png"));


            pop.setContentPane(image);
            pop.setSize(500, 500);
            pop.setVisible(true);

            Timer timer = new java.util.Timer();
            TimerTask autoHideTimer = new TimerTask() {
                @Override
                public void run() {
                    pop.dispose();
                }
            };
            timer.schedule(autoHideTimer, 5000);

        }
    }


    // Represents the action to be taken when user wants to save the data
    private class SaveHouses extends AbstractAction {

        //EFFECTS: creates an action with the given name
        SaveHouses() {
            super("Save Houses");
        }

        //EFFECTS: saves the myHouseList to the file, shows an error message if the file does not exist
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                jsonWriter.open();
                jsonWriter.write(myHouseList);
                jsonWriter.close();
                System.out.println("House List has been saved " + " to " + JSON_STORE);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(Gui.this, e.getMessage(), "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    // Represents the action to be taken when user wants to load the data
    private class LoadHouses extends AbstractAction {

        //EFFECTS: creates an action with the given name
        LoadHouses() {
            super("Load Houses");
        }

        //EFFECTS: loads data from the file, shows an error message if the file does not exist
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                myHouseList = jsonReader.read();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(Gui.this, e.getMessage(), "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    // Represents action to be taken when user wants to give their registration number
    private class UserNumber extends AbstractAction {

        //EFFECTS: creates an action with the given name
        UserNumber() {
            super("UserNumber");
        }


        //EFFECTS: creates dialog box with fields to enter the user registration number, shows an error
        // message if the input value is not a valid number
        @Override
        public void actionPerformed(ActionEvent evt) {

            try {
                regNumber = Integer.parseInt(JOptionPane.showInputDialog("Please Enter your registration number:"));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(Gui.this, "Enter Valid Number!", "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }


    //Represents action to be taken when user wants to remove a house
    private class RemoveHouse extends AbstractAction {

        //EFFECTS: creates an action with the given name
        RemoveHouse() {
            super("Remove House");
        }

        //MODIFIES: myHouseList
        //EFFECTS: if the house with given registration number is in the list then remove it, otherwise
        // show an error message
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
                JOptionPane.showMessageDialog(Gui.this, "House not registered!",
                        "System Error", JOptionPane.ERROR_MESSAGE);
            }



        }
    }

    //Represents action when user wants to display the data of all houses registered in the system
    private class DisplayHouses extends AbstractAction {

        //EFFECTS: creates an action with the given name
        DisplayHouses() {
            super("Display Houses");
        }

        //MODIFIES: desktop
        //EFFECTS: creates a display table with information of all the houses registered in the system
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                Display df = new Display(myHouseList, Gui.this);
                desktop.add((Display) df);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(Gui.this, "Fill all fields", "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Class was taken and modified from DesktopFocusAction in:
    // https://github.students.cs.ubc.ca/CPSC210/AlarmSystem/blob/main/src/main/ca/ubc/cpsc210/alarm/ui/
    //Represents action to be taken when user clicks desktop to switch focus. (Needed for key handling.)
    private class DesktopFocusAction extends MouseAdapter {

        //EFFECTS: represents action to be taken when the mouse is clicked
        @Override
        public void mouseClicked(MouseEvent e) {
            Gui.this.requestFocusInWindow();
        }
    }

}
