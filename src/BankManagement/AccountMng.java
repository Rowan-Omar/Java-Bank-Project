package BankManagement;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;


public class AccountMng extends Bank implements ActionListener {

    JFrame f;
    JTextField tId, tNum, tType, deleteId, editId;
    JButton bAdd, bRemove, bDisplay, bDone, bEdit;
    BufferedReader accountCSVReader;
    BufferedWriter accountCSVWriter;
    ArrayList<BankAccount> arrayFile;

    static BankAccount account = new BankAccount();
    LinkedList<BankAccount> bank = new LinkedList<>();
    public AccountMng(){
        System.out.println(super.id);

        int numRow = -1;
        try {
            accountCSVReader = new BufferedReader(new FileReader("src/BankManagement/accounts.csv"));
            while (accountCSVReader.readLine() != null) { //this for knowing how many rows (account objects) exist in the Excel file
                numRow++;
            }
            System.out.println("There are " + numRow + " entries");
            arrayFile = new ArrayList<>(numRow);
        } catch (Exception ex) {
            System.out.println("There is error in reading for the array size: " + ex);
        }

        //arraysFile = new Object[numRow][numCol];

        String row;
        String[] accountInfo;
        int flag = 0;

        try {
            accountCSVReader = new BufferedReader(new FileReader("src/BankManagement/accounts.csv"));
            while ((row = accountCSVReader.readLine()) != null) {
                if (flag == 0) { //this to skip the header of the Excel file so as not to be added in our array
                    flag = 1;
                    continue;
                }
                accountInfo = row.split(",");

                BankAccount newAccount; // ??????????????????????????? there is a question below
                newAccount = new BankAccount(accountInfo[0], accountInfo[1], null, accountInfo[2]); // HOW to enhance this to be generic  col not by specifying the index of the array statically
                newAccount.setBalance( new Double(accountInfo[3]));
                arrayFile.add(newAccount);
                // System.out.println(arrayFile);
            }
            accountCSVReader.close();
        } catch (Exception ex) {
            System.out.println("There is error in reading in the array: " + ex);
        }
        for(int i=0;i<arrayFile.size();i++){
            if(Objects.equals(arrayFile.get(i).getAcctId(), super.id)){
                System.out.print(arrayFile.get(i).getAcctName());
            }
        }
       // new Transaction();
    }

    public AccountMng(int x) {


        f = new JFrame("Accounts");
        f.setBackground(Color.white);
        f.setLayout(null);

        JLabel lId = new JLabel("Account ID");
        lId.setBounds(50, 150, 100, 30);
        lId.setFont(new Font("serif", Font.BOLD, 12));
        lId.setForeground(Color.black);
        f.add(lId);

        tId = new JTextField();
        tId.setBounds(150, 150, 150, 30);
        f.add(tId);

        JLabel lNumber = new JLabel("Account Number");
        lNumber.setBounds(50, 250, 100, 30);
        lNumber.setFont(new Font("serif", Font.BOLD, 12));
        lNumber.setForeground(Color.black);
        f.add(lNumber);

        tNum = new JTextField();
        tNum.setBounds(150, 250, 150, 30);
        f.add(tNum);

        JLabel lType = new JLabel("Account Type");
        lType.setBounds(50, 350, 100, 30);
        lType.setFont(new Font("serif", Font.BOLD, 12));
        lType.setForeground(Color.black);
        f.add(lType);

        tType = new JTextField();
        tType.setBounds(150, 350, 150, 30);
        f.add(tType);

        bAdd = new JButton("Add");
        bAdd.setBackground(Color.BLACK);
        bAdd.setForeground(Color.WHITE);
        bAdd.setBounds(100, 450, 150, 40);
        bAdd.addActionListener(this);
        f.add(bAdd);

        bRemove = new JButton("Remove");
        bRemove.setBackground(Color.BLACK);
        bRemove.setForeground(Color.WHITE);
        bRemove.setBounds(350, 450, 150, 40);
        bRemove.addActionListener(this);
        f.add(bRemove);

        bEdit = new JButton("Edit");
        bEdit.setBackground(Color.BLACK);
        bEdit.setForeground(Color.WHITE);
        bEdit.setBounds(550, 450, 150, 40);
        bEdit.addActionListener(this);
        f.add(bEdit);

        bDisplay = new JButton("Display in console");
        bDisplay.setBackground(Color.BLACK);
        bDisplay.setForeground(Color.WHITE);
        bDisplay.setBounds(100, 500, 150, 40);
        bDisplay.addActionListener(this);
        f.add(bDisplay);

        bDone = new JButton("Done");  //to update the Excel file with the final changes that happened
        bDone.setBackground(Color.BLACK);
        bDone.setForeground(Color.WHITE);
        bDone.setBounds(350, 500, 150, 40);
        bDone.addActionListener(this);
        f.add(bDone);

        f.setLayout(null);
        f.setSize(900, 600);
        f.setLocation(400, 100);
        f.setVisible(true);

    }

    public void arrayFileDisplay() {
        int count;
        if (arrayFile == null || arrayFile.size() == 0) {
            System.out.println("The file is empty");
            return;
        }
        count = 0;
        for (BankAccount bankAccount : arrayFile)
            System.out.println("This is the name of the account no. " + (++count) + " : " + bankAccount.getAcctName());
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if (arrayFile != null && ae.getSource() == bAdd) {
            BankAccount newRow;
            if (Objects.equals(tId.getText(), "")) {
                JOptionPane.showMessageDialog(null, "Sorry, the Account ID field must not be empty");
                return;
            }
            newRow = new BankAccount(tId.getText(), tNum.getText(), null, tType.getText());
            arrayFile.add(newRow);
            System.out.println("The account has been added successfully");

            /*            try {
                accountCSVWriter = new BufferedWriter(new FileWriter("src/BankManagement/accounts.csv"));
               newRows.add(0, tId.getText());
                newRows.add(1, tNum.getText());
                newRows.add(2, tType.getText());

                /*
                accountCSVWriter.write("Account id");
                accountCSVWriter.append(','); //to add new column
                accountCSVWriter.write("Account Number");
                accountCSVWriter.append(',');
                accountCSVWriter.write("Account Type");
                accountCSVWriter.append('\n'); //to add new row
                accountCSVWriter.write("1");
                accountCSVWriter.append(',');
                accountCSVWriter.write("11111");
                accountCSVWriter.append(',');
                accountCSVWriter.write("Saving");
                accountCSVWriter.append('\n');
                accountCSVWriter.write("2");
                accountCSVWriter.append(',');
                accountCSVWriter.write("22222");
                accountCSVWriter.append(',');
                accountCSVWriter.write("Checking");
                accountCSVWriter.append('\n');
                    accountCSVWriter.write(newRows.get(0));
                    accountCSVWriter.append(',');
                    accountCSVWriter.write(newRows.get(1));
                    accountCSVWriter.append(',');
                    accountCSVWriter.write(newRows.get(2));
                    accountCSVWriter.append('\n');
                } //

                accountCSVWriter.flush();
                accountCSVWriter.close();

            } catch (Exception ex) {
                System.out.println("There is error in writing: " + ex);
            }*/
        } else if (ae.getSource() == bDisplay) {
            arrayFileDisplay();
            System.out.println("Display");
            /* try {
                BufferedReader accountCSVReader = new BufferedReader(new FileReader("src/BankManagement/accounts.csv"));
                while ((row = accountCSVReader.readLine()) != null) {
                    accountInfo = row.split(",");
                    //if (!Objects.equals(accountInfo[0], deleteId.getText())) { //to not view the deleted account //but this MUST be improved and be actual deleted
                    for (int col = 0; col < accountInfo.length; col++) { // da biosher le r9m el column
                        if (col == 0)
                            System.out.print("|");
                        System.out.printf("%17s", accountInfo[col] + " | ");
                    }
                    System.out.println();
                    System.out.println("-".repeat(70));
                    //}
                }
                accountCSVReader.close();
            } catch (Exception ex) {
                System.out.println("There is error in reading: " + ex);
            }*/
        } else if (ae.getSource() == bRemove) {
            if (account instanceof BankAccount) { //to be improved so that we have an unknown object and here we check if it is the admin to delete a specific account
                System.out.println("This one is approved to remove");
                String message = "Enter the id of the account to be deleted";
                deleteId = new JTextField();
                int result = JOptionPane.showOptionDialog(null, new Object[]{message, deleteId}, //to know if the user has cancelled the removal operation
                        "Delete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                System.out.println("The id is: " + deleteId.getText());
                // System.out.println("The account will: " + result);

                if (arrayFile != null && result == 0 && !Objects.equals(deleteId.getText(), "")) {
                    int id = Integer.parseInt(deleteId.getText());
                    // if ((id % 2) == 0) {//deleting the id with even values // can be updated to delete ids if they are instances of customer class
                    for (BankAccount account : arrayFile) {
                        if (Objects.equals(account.getAcctId(), deleteId.getText())) {
                            //System.out.println(account.getAcctName()+"  with index "+arrayFile.indexOf(account));
                            arrayFile.remove(account);
                            System.out.println("The account has been deleted");
                            return;
                        }
                    }
                    // }
                }
            } else if (bank instanceof LinkedList) {
                JOptionPane.showMessageDialog(null, "Sorry, you are not authenticated to remove accounts");
            } else {
                JOptionPane.showMessageDialog(null, "Not instance");
            }
        } else if (ae.getSource() == bEdit) { //same condition will be applied here which are you must be an ADMIN
            System.out.println("This one is approved to edit");
            String message = "Enter the id of the account to be edited";
            editId = new JTextField();
            int result = JOptionPane.showOptionDialog(null, new Object[]{message, editId}, //to know if the user has cancelled the removal operation
                    "Delete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            System.out.println("The id is: " + editId.getText());

            if (arrayFile != null && result == 0 && !Objects.equals(editId.getText(), "")) {
                int id = Integer.parseInt(editId.getText());
                for (BankAccount account : arrayFile) {
                    if ((Objects.equals(account.getAcctId(), editId.getText()))) {
                        //Here, we should make a window to appear to make the admin set the new values.
                        //Also, we need to ask him what specific field to be updated unless we will display all the fields, and he updates all of them!!
                        System.out.println("This account has been updated");
                        return;
                    }
                }
            }
        } else if (ae.getSource() == bDone) {//upload the changes in the real file
            try {
                accountCSVWriter = new BufferedWriter(new FileWriter("src/BankManagement/accounts.csv"));
                accountCSVWriter.write("Account Id");
                accountCSVWriter.append(',');
                accountCSVWriter.write("Account Number");
                accountCSVWriter.append(',');
                accountCSVWriter.write("Account Type");
                for (BankAccount account : arrayFile) {
                    accountCSVWriter.append('\n');
                    accountCSVWriter.append(account.getAcctId());
                    accountCSVWriter.append(',');
                    accountCSVWriter.append(account.getAcctName());
                    accountCSVWriter.append(',');
                    accountCSVWriter.write(account.getAcctType());
                }
                JOptionPane.showMessageDialog(null, "The Excel file has been updated successfully");
                accountCSVWriter.flush();
                accountCSVWriter.close();
            } catch (Exception ex) {
                System.out.println("There is error in writing in file: " + ex);
            }
        } else {
            System.out.println("Invalid button");
        }
    }

    /*public static void main(String[] args){
        new AccountMng();
        System.out.println(super.id);*/
    }


