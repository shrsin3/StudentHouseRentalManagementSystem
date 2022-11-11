package ui;

import model.House;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRegistration extends JDialog {
    private JTextField title;
    private JTextField ownerName;
    private JTextField ownerGender;
    private JTextField address;
    private JTextField city;
    private JTextField rentAmount;
    private JButton registerButton;
    private JPanel userResgisterForm;

    public UserRegistration(JFrame main) {
        super(main);
        setTitle("Register User");
        setContentPane(userResgisterForm);
        setSize(new Dimension(700,700));
        setModal(true);
        setLocationRelativeTo(main);
        setVisible(true);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerHouse();
                main.dispose();
            }
        });
    }

    public House registerHouse() {

        String name = ownerName.getText();
        String gender = ownerGender.getText();
        String ownerAddress = address.getText();
        String houseCity = city.getText();
        Double rent = Double.parseDouble((rentAmount.getText()));

        if (name.isEmpty() || gender.isEmpty() || ownerAddress.isEmpty() || houseCity.isEmpty() || rent.isNaN()) {
            JOptionPane.showMessageDialog(null, "Fill all fields", "System Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        House house = new House(ownerAddress, houseCity, rent, name, gender, false);
        return house;
    }
}
