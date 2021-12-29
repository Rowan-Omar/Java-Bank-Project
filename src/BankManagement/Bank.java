package BankManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.*;
import javax.swing.*;
public class Bank {
    static String id;
    private final String BankName;
    public Bank(){
        BankName="IBS BankManagement.Bank";
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Bank Ba =new Bank();
        Ba.id=sc.next();
        new AccountMng();
        new Transaction();

       /* // creating BankManagement.Bank window with 2 buttons (login - register).
        JFrame BankName = new JFrame("BankManagement.Bank Application");
        BankName.setSize(700,500);
        BankName.setVisible(true);
        BankName.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //creating the Login frame opened by Login button.
        JFrame f1 = new JFrame("Login Window");
        f1.setSize(700,500);
        f1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //creating the Register frame opened with Register button.
        JFrame f2 = new JFrame("Register Window");
        f2.setSize(700,500);
        f2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //creating the panel of BankManagement.Bank window and adding the buttons.
        JPanel p = new JPanel();
        BankName.add(p);
        JLabel l1=  new JLabel("Welcome to IBS BankManagement.Bank");
        l1.setVisible(true);
        p.add(l1);
        JButton btnlogin = new JButton("Login");
        btnlogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //BankName.setVisible(false);
                f1.setVisible(true);
            }
        });
        // btnlogin.setBounds(300,150,30,20);
        p.add(btnlogin);
        JButton btnregist = new JButton("Register");
        btnregist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //BankName.setVisible(false);
                f2.setVisible(true);
            }
        });
        //btnregist.setBounds(400,150,30,20);
        p.add(btnregist);


        //creating Menue frame opened with sign in button in Login frame.
        JFrame f3 = new JFrame("Menue");
        f3.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f3.setSize(700,500);

        //creating the panel of Login window with 2 textfields and (sign in) button.
        JPanel plog = new JPanel();
        f1.add(plog);

        JLabel l2 = new JLabel("E-mail");
        JTextField mail = new JTextField();
        JLabel l3 = new JLabel("Password");
        JPasswordField psw = new JPasswordField();
        JButton signin = new JButton("Sign in");
        signin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f3.setVisible(true);
            }
        });
        plog.add(l2);
        plog.add(mail);
        plog.add(l3);
        plog.add(psw);
        plog.add(signin);

        //creating the panel of Register window.
        JPanel preg = new JPanel();
        f2.add(preg);*/
    }



}
