package BankManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Register {
    public Register() {
        JFrame fRegister = new JFrame("Register Window");
        fRegister.setSize(700, 500);
        //fRegister.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel l1 = new JLabel("Welcome to IBS Bank");
        l1.setBounds(10, 100, 300, 40);
        l1.setAlignmentX(Component.CENTER_ALIGNMENT);
        //bankFrame.add(l1);

        //creating the panel of Register window.
        JPanel preg = new JPanel();

        fRegister.add(preg);

        fRegister.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                Bank.bankFrame.setVisible(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        fRegister.setLayout(null);
        fRegister.setSize(400, 200);
        fRegister.setLocation(600, 200);
        fRegister.setVisible(true);
        fRegister.setResizable(false);

    }
    /*public static void main(String[] args) {
        new Register();
    }*/
}
