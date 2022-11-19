package ui;

import model.House;
import model.HouseList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

//Represents the window with a table of all the information of houses registered in the system
public class Display extends JInternalFrame {

    JPanel panel = new JPanel();
    JFrame frame = new JFrame();
    ArrayList<String> houses;
    String ownerNumber;
    String ownerName;
    String ownerGender;
    String ownerAddress;
    String ownerCity;
    String ownerAmount;
    String ownerStatus;

    //EFFECTS: creates a window with a table of information af all the houses in the given list and includes it in
    // the parent window
    public Display(HouseList houseList, Component parent) {

        super("All Houses", false, true, false, false);
        setSize(600, 500);
        panel.setSize(700, 700);
        setLayout(new FlowLayout());

        Object[] colNames = {"Reg. Number","Name","Gender","Address","City","Amount",
                "Status"};


        Object[][] rows = convertHouseList(houseList);

        DefaultTableModel table = new DefaultTableModel(rows, colNames);
        JTable table1 = new JTable(table);
        table1.setPreferredScrollableViewportSize(new Dimension(500,700));
        table1.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table1);

        add(scrollPane);
        validate();
        setPosition(parent);
        setVisible(true);

        setDefaultCloseOperation(getDefaultCloseOperation());
    }

    //EFFECTS: parses the given houseList into Object[][] so it can be used to create a table
    public Object[][] convertHouseList(HouseList houseList) {

        Object[][] finalList = new Object[houseList.getHouseList().size()][];
        int number = 0;

        for (House house : houseList.getHouseList()) {

            ownerNumber =  Integer.toString(house.getRegistrationNumber());
            ownerName = house.getOwnerName();
            ownerGender = house.getOwnerGender();
            ownerCity = house.getCity();
            ownerAddress = house.getAddress();
            ownerAmount = Double.toString(house.getRentAmount());

            if (house.getIsRented()) {
                ownerStatus = "Rented";
            } else {
                ownerStatus = "Available";
            }
            Object[] o = {ownerNumber,ownerName,ownerGender,ownerAddress,ownerCity,ownerAmount,ownerStatus};
            finalList[number] = o;
            number += 1;

        }
        return finalList;
    }

    // Method was taken and modified from setPosition() in:
    // https://github.students.cs.ubc.ca/CPSC210/AlarmSystem/blob/main/src/main/ca/ubc/cpsc210/alarm/
    // ui/ScreenPrinter.java
    //EFFECTS: sets the position of the display window relative to the parent window
    private void setPosition(Component parent) {
        setLocation(parent.getWidth() - getWidth() - 20,
                parent.getHeight() - getHeight() - 20);
    }

}
