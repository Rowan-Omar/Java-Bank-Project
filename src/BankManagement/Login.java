package BankManagement;

import customer.src.com.company.BankCustomer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Login {
    static String id;

    public Login() {
        JFrame fLogin = new JFrame("Login Window");
        new BankAccount();
        new BankCustomer();
//creating the panel of Login window with 2 textfields and (sign in) button.
        JPanel plog = new JPanel();

        //JLabel lEmail = new JLabel("E-mail");
        JLabel lEmail = new JLabel("ID");
        lEmail.setBounds(50, 50, 60, 20);

        JTextField mail = new JTextField();
        mail.setBounds(120, 50, 100, 20);

        JLabel lPass = new JLabel("Password");
        lPass.setBounds(50, 100, 60, 20);

        JPasswordField psw = new JPasswordField();
        psw.setBounds(120, 100, 100, 20);

        JButton signIn = new JButton("Sign in");
        signIn.setBounds(300, 75, 80, 20);
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //f3.setVisible(true);
                id = mail.getText();
                if (BankAccount.isValidAcc(id)) { // Thus, it is admin
                    if (BankCustomer.isValidCust(id)) { //thus, you are a customer
                        new Transaction();
                    } else {  //you are admin  // Need to be updated when the admin CSV file is created
                        new AccountMng(1);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "This is invalid account");
                }
            }
        });
        fLogin.add(lEmail);
        fLogin.add(mail);
        fLogin.add(lPass);
        fLogin.add(psw);
        fLogin.add(signIn);

        fLogin.add(plog);
        //fLogin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        fLogin.setSize(700, 500);
        fLogin.setVisible(true);
        fLogin.setLocation(400, 200);
    }

}
