package BankManagement;


import Customer.BankCustomer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;

public class Login {
    static String id;

    public Login() {
        JFrame fLogin = new JFrame("Login Window");

        //creating the panel of Login window with 2 textfields and (sign in) button.
        //JPanel plog = new JPanel();

        //JLabel lEmail = new JLabel("E-mail");
        JLabel lID = new JLabel("ID");
        lID.setBounds(50, 50, 60, 20);

        JTextField textID = new JTextField();
        textID.setBounds(120, 50, 100, 20);

        JLabel lPass = new JLabel("Password");
        lPass.setBounds(50, 100, 60, 20);

        JPasswordField psw = new JPasswordField();
        psw.setBounds(120, 100, 100, 20);

        JButton signIn = new JButton("Sign in");
        signIn.setBounds(300, 75, Bank.LOCAL_WIDTH, Bank.LOCAL_HEIGHT);
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //f3.setVisible(true);

                //these two to load the data from the files to the arrays
                new BankAccount();
                new BankCustomer();

                id = textID.getText();
                char[] pass = psw.getPassword();
                if (id.length() == 0 || pass.length == 0) {// fe 7aga bttktb t7t el button sign in lma ados 3lih !!!!
                    JOptionPane.showMessageDialog(null, "Both fields must be entered");
                    return;
                }
                System.out.println("Pass is: " + pass.length);
                if (BankAccount.isValidAcc(id)) { // Thus, it is an existing account
                    if (BankCustomer.isValidCust(id)) { //thus, you are a customer
                        if (BankCustomer.isValidPass(String.valueOf(pass))) {
                            new Transaction();
                            textID.setText("");
                            psw.setText("");
                            fLogin.setVisible(false);
                            System.out.println("You are a customer");
                        }else{
                            System.out.println("Wrong password");
                            JOptionPane.showMessageDialog(null, "This is an invalid account");
                        }
                    } else {  //you are admin  // Need to be updated when the admin CSV file is created . because it can be not an admin also
                        new AccountMng(1);
                        textID.setText("");
                        psw.setText("");
                        fLogin.setVisible(false);
                        System.out.println("You are an admin");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "This is an invalid account");
                }
            }
        });

        fLogin.add(lID);
        fLogin.add(textID);
        fLogin.add(lPass);
        fLogin.add(psw);
        fLogin.add(signIn);

        //fLogin.add(plog);

        fLogin.addWindowListener(new WindowListener() {
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

        fLogin.setLayout(null);
        fLogin.setSize(500, 250);
        fLogin.setLocation(550, 250);
        fLogin.setVisible(true);
    }

}