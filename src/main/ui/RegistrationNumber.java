package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationNumber extends JDialog {

    JFrame frame = new JFrame("Enter registration number:");
    JTextField regNumber = new JTextField();
    JButton submit = new JButton("Submit");

    public RegistrationNumber(JFrame main) {

        super(main);
        frame.setSize(500,500);
        frame.setLayout(new FlowLayout());
        regNumber.setPreferredSize(new Dimension(250,40));
        frame.add(submit);
        frame.add(regNumber);
        frame.pack();
        frame.setLocationRelativeTo(main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

//        submit.addActionListener(this);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getRegistrationNumber();
                frame.dispose();
            }
        });

    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == submit) {
//            getRegistrationNumber();
//            frame.dispose();
//        }
//    }
    public int getRegistrationNumber() {

        String text = regNumber.getText();

        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter Number", "System Error",
                        JOptionPane.ERROR_MESSAGE);
        }

        int number = Integer.parseInt(text);

        return number;


    }
}
