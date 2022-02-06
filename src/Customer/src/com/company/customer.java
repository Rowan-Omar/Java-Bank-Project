package com.company;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

    public class customer implements ActionListener {
        JFrame f;
        JTextField tId;
        JTextField tFirstName;
        JTextField tLastName;
        JTextField tStreet;
        JTextField tCity;
        JTextField tMobile,DeleteId = new JTextField();
        JButton bAdd, bCancel,bDisplay;

        BankCustomer customer = new BankCustomer();

        customer() {
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
            bAdd.setBounds(400, 200, 150, 40);
            bAdd.addActionListener(this);
            f.add(bAdd);

            bCancel = new JButton("cancel");
            bCancel.setBackground(Color.BLACK);
            bCancel.setForeground(Color.WHITE);
            bCancel.setBounds(600, 200, 150, 40);

            bCancel.addActionListener(this);
            f.add(bCancel);

            f.setLayout(null);
            f.setSize(900, 600);
            f.setLocation(400, 100);
            f.setVisible(true);

        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == bAdd) {
                BufferedWriter custoomerCSVWriter = null;
                List<String> newRows = new ArrayList<String>();

                try {
                    custoomerCSVWriter = new BufferedWriter(new FileWriter("C:\\Users\\WIN 10\\Desktop\\untitled2\\custoomer.csv"));

                    newRows.add(0, tId.getText());
                    newRows.add(1, tFirstName.getText());
                    newRows.add(2, tLastName.getText());
                    newRows.add(3,tCity.getText());
                    newRows.add(4, tStreet.getText());
                    newRows.add(5,tMobile.getText());



                    custoomerCSVWriter.write("Account id");
                    custoomerCSVWriter.append(','); //to add new column
                    custoomerCSVWriter.write("customer FirstName");
                    custoomerCSVWriter.append(',');
                    custoomerCSVWriter.write("customer LastName");
                    custoomerCSVWriter.append(',');
                    custoomerCSVWriter.write("customer City");
                    custoomerCSVWriter.append(',');
                    custoomerCSVWriter.write("customer Street");
                    custoomerCSVWriter.append(',');
                    custoomerCSVWriter.write("customer Mobile");
                    custoomerCSVWriter.append('\n'); //to add new row
                    custoomerCSVWriter.write("  ");
                    custoomerCSVWriter.append(',');
                    custoomerCSVWriter.write("  ");
                    custoomerCSVWriter.append(',');
                    custoomerCSVWriter.write("  ");
                    custoomerCSVWriter.append(',');
                    custoomerCSVWriter.write(" ");
                    custoomerCSVWriter.append(',');
                    custoomerCSVWriter.write(" ");
                    custoomerCSVWriter.append(',');
                    custoomerCSVWriter.write(" ");
                    custoomerCSVWriter.append('\n');
                    custoomerCSVWriter.write(" ");
                    custoomerCSVWriter.append(',');
                    custoomerCSVWriter.write(" ");
                    custoomerCSVWriter.append(',');
                    custoomerCSVWriter.write(" ");
                    custoomerCSVWriter.append(',');
                    custoomerCSVWriter.write(" ");
                    custoomerCSVWriter.append(',');
                    custoomerCSVWriter.write(" ");
                    custoomerCSVWriter.append(',');
                    custoomerCSVWriter.write(" ");
                    custoomerCSVWriter.append('\n');

                    if (newRows.get(0) != null) {
                        custoomerCSVWriter.write(newRows.get(0));
                        custoomerCSVWriter.append(',');
                        custoomerCSVWriter.write(newRows.get(1));
                        custoomerCSVWriter.append(',');
                        custoomerCSVWriter.write(newRows.get(2));
                        custoomerCSVWriter.append(',');
                        custoomerCSVWriter.write(newRows.get(3));
                        custoomerCSVWriter.append(',');
                        custoomerCSVWriter.write(newRows.get(4));
                        custoomerCSVWriter.append(',');
                        custoomerCSVWriter.write(newRows.get(5));
                        custoomerCSVWriter.append('\n');
                    }


                    custoomerCSVWriter.flush();
                    custoomerCSVWriter.close();

                } catch (Exception ex) {
                    System.out.println("There is error in writing: " + ex);
                }
            } else if (ae.getSource() == bDisplay) {
                //reading the file
                String row;
                String[] customerInfo = null;
                try {
                    BufferedReader  custoomerCSVReader = new BufferedReader(new FileReader("C:\\Users\\WIN 10\\Desktop\\untitled2\\custoomer.csv"));
                    while ((row =  custoomerCSVReader.readLine()) != null) {
                        customerInfo = row.split(",");
                        if (!Objects.equals(customerInfo[0], DeleteId.getText())) { //to not view the deleted account //but this MUST be improved and be actual deleted
                            for (int col = 0; col < customerInfo.length; col++) { // da biosher le r9m el column
                                if (col == 0)
                                    System.out.print("|");
                                System.out.printf("%17s", customerInfo[col] + " | ");
                            }
                            System.out.println();
                            System.out.println("-".repeat(70));
                        }
                    }
                    custoomerCSVReader.close();
                } catch (Exception ex) {
                    System.out.println("There is error in reading: " + ex);
                }
            } else if (ae.getSource() == bCancel) {
                if (customer instanceof BankCustomer) {
                    System.out.println("This is one approved to remove");
                    String message = "Enter the id of the customer to be deleted";
                    DeleteId = new JTextField();
                    int result = JOptionPane.showOptionDialog(null, new Object[]{message, DeleteId},
                            "Delete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                    System.out.println("The id is: " + DeleteId.getText());
                    System.out.println("The customer will: " + result);

                    int id = Integer.parseInt(DeleteId.getText());
                    if ((id % 2) == 0) {
                        //deleting the id with even values // can be updated to delete ids if they are instance of customer class

                    }
                    String row;
                    String[] customerInfo = null;
                    try {
                        BufferedReader  custoomerCSVReader = new BufferedReader(new FileReader("C:\\Users\\WIN 10\\Desktop\\untitled2\\custoomer.csv"));
                        while ((row =  custoomerCSVReader.readLine()) != null) {
                            customerInfo = row.split(",");
                            // if (Objects.equals(customerInfo[0], deleteId.getText())) {

                            // }
                        }
                        custoomerCSVReader.close();
                    } catch (Exception ex) {
                        System.out.println("There is error in reading2: " + ex);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Not instance");
                }
            }

        }

        public static void main (String[] args) throws Exception {


              new customer();


        }

    }
