package BankManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class Bank {

    private static final String BANK_NAME = "IBS Bank";
    static JFrame bankFrame, fLogin, fRegister;
    static final int LOCAL_WIDTH = 100, LOCAL_HEIGHT = 30;

    public Bank() {

        // creating Bank window with 2 buttons (login - register).
        bankFrame = new JFrame(BANK_NAME);

        Image icon = Toolkit.getDefaultToolkit().getImage("E:\\Java Project\\Java-Bank-Project\\src\\bankIcon.png");
        bankFrame.setIconImage(icon);


        //creating the Login frame opened by Login button.
        /*NOTE: this is written in the Login class*/

        //creating the Register frame opened with Register button.
        /*NOTE: this is written in the Register class*/


        //creating the panel of Bank window and adding the buttons.
        JPanel pnl = new JPanel();
        //pnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        // pnl.setLocation(50, 200);
        pnl.setBackground(Color.red);
        pnl.setBorder(new BevelBorder(2, Color.black, Color.black));

        JButton btnLogin = new JButton("Login");
        //btnLogin.setLocation(100,150);
        btnLogin.setBounds(50, 70, LOCAL_WIDTH, LOCAL_HEIGHT);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bankFrame.setVisible(false);
                new Login();
            }
        });
        //btnLogin.setBounds(300,150,30,20);
        //p.add(btnLogin);

        JButton btnRegister = new JButton("Register");
        btnRegister.setBounds(250, 70, LOCAL_WIDTH, LOCAL_HEIGHT);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bankFrame.setVisible(false);
                new Register();
            }
        });
        //p.add(btnRegister);


        /*//creating Menu frame opened with sign in button in Login frame.
        JFrame f3 = new JFrame("Menu");
        f3.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f3.setSize(700, 500);*/

        //bankFrame.add(pnl);
        bankFrame.add(btnRegister);
        bankFrame.add(btnLogin);

        //bankFrame.setLayout(new FlowLayout(FlowLayout.LEFT, 10,30));
        bankFrame.setLayout(null);
        //bankFrame.getContentPane().setLayout(new BoxLayout(bankFrame.getContentPane(), BoxLayout.Y_AXIS));
        bankFrame.setSize(400, 200);
        bankFrame.setLocation(550, 250);
        bankFrame.setResizable(false);
        bankFrame.setVisible(true);
        bankFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Bank();
    }


}
