package BankManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Bank {

    private final String bankName;
    static JFrame bankFrame, fLogin, fRegister;

    public Bank() {
        bankName = "IBS BankManagement.Bank";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //System.out.print("Enter your id: ");
        //Ba.id = sc.next();

        //new Transaction();
        new Login();
        // creating BankManagement.Bank window with 2 buttons (login - register).
        bankFrame = new JFrame("BankManagement.Bank Application");

        //creating the Login frame opened by Login button.
        /*NOTE: this is written in the Login class*/

        //creating the Register frame opened with Register button.
        fRegister = new JFrame("Register Window");
        fRegister.setSize(700, 500);
        //fRegister.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel l1 = new JLabel("Welcome to IBS BankManagement.Bank");
        l1.setBounds(10, 100, 300, 40);
        l1.setAlignmentX(Component.CENTER_ALIGNMENT);
        bankFrame.add(l1);


        //creating the panel of BankManagement.Bank window and adding the buttons.
        JPanel p = new JPanel();
        p.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.setLocation(50, 200);

        JButton btnLogin = new JButton("Login");
        //btnLogin.setLocation(100,150);
        btnLogin.setBounds(50, 100, 60, 30);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bankFrame.setVisible(false);
                new Login();
            }
        });
        // btnLogin.setBounds(300,150,30,20);
        p.add(btnLogin);

        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bankFrame.setVisible(false);
                fRegister.setVisible(true);
            }
        });
        //btnRegister.setBounds(400,150,30,20);
        p.add(btnRegister);


        //creating Menue frame opened with sign in button in Login frame.
        JFrame f3 = new JFrame("Menue");
        f3.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f3.setSize(700, 500);


        //creating the panel of Register window.
        JPanel preg = new JPanel();
        fRegister.add(preg);

        bankFrame.add(p);
        bankFrame.getContentPane().setLayout(new BoxLayout(bankFrame.getContentPane(), BoxLayout.Y_AXIS));
        bankFrame.setSize(700, 500);
        bankFrame.setLocation(600, 200);
        bankFrame.setVisible(true);
        bankFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


}
