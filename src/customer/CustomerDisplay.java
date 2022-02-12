package Customer;

import BankManagement.AccountMng;
import BankManagement.BankAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class CustomerDisplay implements ActionListener {
    public static JFrame f;
    JTextField tId, tFirstName, tLastName, tStreet, tCity, tMobile;
    JButton bAdd, bCancel, bDone;

    public static String custID, password, accountID;
    int flag = 0;

    public CustomerDisplay(String accID) {
        f = new JFrame("customer");
        f.setBackground(Color.red);
        f.setLayout(null);

        accountID = accID;

        JLabel lId = new JLabel("customer ID");
        lId.setBounds(50, 50, 100, 30);
        lId.setFont(new Font("serif", Font.BOLD, 12));
        lId.setForeground(Color.black);
        f.add(lId);

        tId = new JTextField();
        tId.setBounds(150, 50, 150, 30);
        f.add(tId);

        JLabel lFirstName = new JLabel("First Name");
        lFirstName.setBounds(50, 100, 100, 30);
        lFirstName.setFont(new Font("serif", Font.BOLD, 12));
        lFirstName.setForeground(Color.black);
        f.add(lFirstName);

        tFirstName = new JTextField();
        tFirstName.setBounds(150, 100, 150, 30);
        f.add(tFirstName);

        JLabel lLastName = new JLabel("Last Name");
        lLastName.setBounds(50, 150, 100, 30);
        lLastName.setFont(new Font("serif", Font.BOLD, 12));
        lLastName.setForeground(Color.black);
        f.add(lLastName);

        tLastName = new JTextField();
        tLastName.setBounds(150, 150, 150, 30);
        f.add(tLastName);

        JLabel lCity = new JLabel("City");
        lCity.setBounds(50, 200, 100, 30);
        lCity.setFont(new Font("serif", Font.BOLD, 12));
        lCity.setForeground(Color.black);
        f.add(lCity);

        tCity = new JTextField();
        tCity.setBounds(150, 200, 150, 30);
        f.add(tCity);

        JLabel lStreet = new JLabel("Street");
        lStreet.setBounds(50, 250, 100, 30);
        lStreet.setFont(new Font("serif", Font.BOLD, 12));
        lStreet.setForeground(Color.black);
        f.add(lStreet);

        tStreet = new JTextField();
        tStreet.setBounds(150, 250, 150, 30);
        f.add(tStreet);

        JLabel lMobile = new JLabel("Mobile");
        lMobile.setBounds(50, 300, 100, 30);
        lMobile.setFont(new Font("serif", Font.BOLD, 12));
        lMobile.setForeground(Color.black);
        f.add(lMobile);

        tMobile = new JTextField();
        tMobile.setBounds(150, 300, 150, 30);
        f.add(tMobile);

        bAdd = new JButton("Add");
        bAdd.setBackground(Color.BLACK);
        bAdd.setForeground(Color.WHITE);
        bAdd.setBounds(400, 350, 150, 40);
        bAdd.addActionListener(this);
        f.add(bAdd);

        bDone = new JButton("Done");
        bDone.setBackground(Color.BLACK);
        bDone.setForeground(Color.WHITE);
        bDone.setBounds(600, 200, 150, 40);

        bDone.addActionListener(this);
        f.add(bDone);

        bCancel = new JButton("Cancel");
        bCancel.setBackground(Color.BLACK);
        bCancel.setForeground(Color.WHITE);
        bCancel.setBounds(600, 250, 150, 40);

        bCancel.addActionListener(this);
        f.add(bCancel);

        f.setLayout(null);
        f.setSize(900, 600);
        f.setLocation(400, 100);
        f.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (BankCustomer.getCustArrayFile() != null && ae.getSource() == bAdd) {
            flag = -1; //to inform that changes happened and to be checked before closing this window

            BankCustomer newRow;

            if (Objects.equals(tId.getText(), "")) {
                JOptionPane.showMessageDialog(null, "Sorry, the Customer ID field must not be empty");
                return;
            }
            if (tFirstName.getText().length() == 0)
                tFirstName.setText("-----");
            if (tLastName.getText().length() == 0)
                tLastName.setText("-----");
            if (tCity.getText().length() == 0)
                tCity.setText("-----");
            if (tStreet.getText().length() == 0)
                tStreet.setText("-----");
            if (tMobile.getText().length() == 0)
                tMobile.setText("---");

            newRow = new BankCustomer(tId.getText(), tFirstName.getText(), tLastName.getText(), accountID, tCity.getText(), tStreet.getText(), tMobile.getText());
            BankCustomer.getCustArrayFile().add(newRow);
            custID = tId.getText();
            BankAccount acc = BankAccount.getAccount(accountID);

            if (acc != null) //updating the customer id in the BankAccount class
                acc.setCustID(tId.getText());
            JOptionPane.showMessageDialog(null, "This customer has been added successfully");
        } else if (ae.getSource() == bDone) {
            BankCustomer.writeToCustFile();
            JOptionPane.showMessageDialog(null, "Customer excel file has been update successfully");
            flag = 1;
        } else if (ae.getSource() == bCancel) {

            f.setVisible(false);
        }
    }

    public static void main(String[] args) {
        /*new BankCustomer();
        new CustomerDisplay();*/
    }
}




