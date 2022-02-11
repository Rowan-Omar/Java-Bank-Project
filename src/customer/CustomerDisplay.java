package CUSTOMER;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Objects;

public class CustomerDisplay implements ActionListener {
    JFrame f;
    JTextField tId;
    JTextField tFirstName;
    JTextField tLastName;
    JTextField tStreet;
    JTextField tCity;
    JTextField tMobile;
    JButton bAdd, bCancel, bDone;

    int flag = 0;
    BufferedWriter customerCSVWriter;

    CustomerDisplay () {
        f = new JFrame("customer");
        f.setBackground(Color.red);
        f.setLayout(null);

        JLabel lId = new JLabel("customer ID");
        lId.setBounds(50, 50, 100, 30);
        lId.setFont(new Font("serif", Font.BOLD, 12));
        lId.setForeground(Color.black);
        f.add(lId);

        tId = new JTextField();
        tId.setBounds(150, 50, 150, 30);
        f.add(tId);

        JLabel lFirstName = new JLabel("FirstName");
        lFirstName.setBounds(50, 100, 100, 30);
        lFirstName.setFont(new Font("serif", Font.BOLD, 12));
        lFirstName.setForeground(Color.black);
        f.add(lFirstName);

        tFirstName = new JTextField();
        tFirstName.setBounds(150, 100, 150, 30);
        f.add(tFirstName);

        JLabel lLastName = new JLabel("LastName");
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
    public void actionPerformed ( ActionEvent ae ) {
        if (BankCustomer.customerArrayFile != null && ae.getSource() == bAdd) {
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

            newRow = new BankCustomer(tId.getText(), tFirstName.getText(), tLastName.getText(), tCity.getText(), null, tStreet.getText(), tMobile.getText());
            BankCustomer.customerArrayFile.add(newRow);
            JOptionPane.showMessageDialog(null, "This customer has been added successfully");
        } else if (ae.getSource() == bDone) {

            try {
                customerCSVWriter = new BufferedWriter(new FileWriter("src/CUSTOMER/customers.csv"));

                customerCSVWriter.write("Customer id");
                customerCSVWriter.append(','); //to add new column
                customerCSVWriter.write("customer FirstName");
                customerCSVWriter.append(',');
                customerCSVWriter.write("customer LastName");
                customerCSVWriter.append(',');
                customerCSVWriter.write("customer City");
                customerCSVWriter.append(',');
                customerCSVWriter.write("customer Street");
                customerCSVWriter.append(',');
                customerCSVWriter.write("customer Mobile");
                for (BankCustomer customer : BankCustomer.customerArrayFile) {
                    customerCSVWriter.append('\n'); //to add new row
                    customerCSVWriter.write(customer.getCustId());
                    customerCSVWriter.append(',');
                    customerCSVWriter.write(customer.getCustFirstName());
                    customerCSVWriter.append(',');
                    customerCSVWriter.write(customer.getCustLastName());
                    customerCSVWriter.append(',');
                    customerCSVWriter.write(customer.getCustCity());
                    customerCSVWriter.append(',');
                    customerCSVWriter.write(customer.getCustStreet());
                    customerCSVWriter.append(',');
                    customerCSVWriter.write(customer.getCustMobile());

                }
                JOptionPane.showMessageDialog(null, "The Excel file has been update successfully");
                customerCSVWriter.flush();
                customerCSVWriter.close();
                flag = -1;
            } catch (Exception ex) {
                System.out.println("There is error in writing: " + ex);
            }
        } else if (ae.getSource() == bCancel) {
            f.setVisible(false);
        }
    }


    public static void main (String[]args ){
            new CustomerDisplay();
        }
}




