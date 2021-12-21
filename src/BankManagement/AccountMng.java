package BankManagement;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;



public class AccountMng implements ActionListener {
    JFrame f;
    JTextField tId, tNum, tType, deleteId = new JTextField();
    JButton bAdd, bRemove, bDisplay,bcheck;

    BankAccount account = new BankAccount();
    LinkedList<BankAccount> bank = new LinkedList<>();

    AccountMng() {
        try{

            BufferedWriter accountCSVWriter = new BufferedWriter(new FileWriter("src/BankManagement/accounts.csv"));
            accountCSVWriter.append("Account id");
            accountCSVWriter.append(','); //to add new column
            accountCSVWriter.append("Account Number");
            accountCSVWriter.append(',');
            accountCSVWriter.append("Account Type");
            accountCSVWriter.append('\n'); //to add new row
            accountCSVWriter.flush();
            accountCSVWriter.close();

             }
        catch(Exception e){
            System.out.println("error");
        }


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
        bRemove.setBounds(400, 450, 150, 40);

        bRemove.addActionListener(this);
        f.add(bRemove);

        bDisplay = new JButton("Display content in console");
        bDisplay.setBackground(Color.BLACK);
        bDisplay.setForeground(Color.WHITE);
        bDisplay.setBounds(250, 500, 150, 40);

        bDisplay.addActionListener(this);
        f.add(bDisplay);

        bcheck=new JButton("Check");
        bcheck.addActionListener(this);
        bcheck.setBackground(Color.BLACK);
        bcheck.setForeground(Color.WHITE);
        bcheck.setBounds(400, 500, 150, 40);

        f.add(bcheck);

        f.setLayout(null);
        f.setSize(900, 600);
        f.setLocation(400, 100);
        f.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == bAdd) {
            BufferedWriter accountCSVWriter = null;
            List<String> newRows = new ArrayList<String>();

            try {
                accountCSVWriter = new BufferedWriter(new FileWriter("src/BankManagement/accounts.csv",true));

                newRows.add(0, tId.getText());
                newRows.add(1, tNum.getText());
                newRows.add(2, tType.getText());


                /*accountCSVWriter.append("1");
                accountCSVWriter.append(',');
                accountCSVWriter.append("11111");
                accountCSVWriter.append(',');
                accountCSVWriter.append("Saving");
                accountCSVWriter.append('\n');
                accountCSVWriter.append("2");
                accountCSVWriter.append(',');
                accountCSVWriter.append("22222");
                accountCSVWriter.append(',');
                accountCSVWriter.append("Checking");
                accountCSVWriter.append('\n');*/
                if (newRows.get(0) != null) {
                    accountCSVWriter.write(newRows.get(0));
                    accountCSVWriter.append(',');
                    accountCSVWriter.append(newRows.get(1));
                    accountCSVWriter.append(',');
                    accountCSVWriter.append(newRows.get(2));
                    accountCSVWriter.append('\n');
                }


                accountCSVWriter.flush();
                accountCSVWriter.close();

            } catch (Exception ex) {
                System.out.println("There is error in writing: " + ex);
            }
        } else if (ae.getSource() == bDisplay) {
            //reading the file
            String row;
            String[] accountInfo = null;
            try {
                BufferedReader accountCSVReader = new BufferedReader(new FileReader("src/BankManagement/accounts.csv"));
                while ((row = accountCSVReader.readLine()) != null) {
                    accountInfo = row.split(",");
                    if (!Objects.equals(accountInfo[0], deleteId.getText())) { //to not view the deleted account //but this MUST be improved and be actual deleted
                        for (int col = 0; col < accountInfo.length; col++) { // da biosher le r9m el column
                            if (col == 0)
                                System.out.print("|");
                            System.out.printf("%17s", accountInfo[col] + " | ");
                        }
                        System.out.println();
                        System.out.println("-".repeat(70));
                    }
                }
                accountCSVReader.close();
            } catch (Exception ex) {
                System.out.println("There is error in reading: " + ex);
            }
        } else if (ae.getSource() == bRemove) {
            if (account instanceof BankAccount) {
                System.out.println("This is one approved to remove");
                String message = "Enter the id of the account to be deleted";
                deleteId = new JTextField();
                int result = JOptionPane.showOptionDialog(null, new Object[]{message, deleteId},
                        "Delete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                System.out.println("The id is: " + deleteId.getText());
                System.out.println("The account will: " + result);

                int id = Integer.parseInt(deleteId.getText());
                if ((id % 2) == 0) {
                    //deleting the id with even values // can be updated to delete ids if they are instance of customer class

                }
                String row;
                String[] accountInfo = null;
                try {
                    BufferedReader accountCSVReader = new BufferedReader(new FileReader("src/BankManagement/accounts.csv"));
                    while ((row = accountCSVReader.readLine()) != null) {
                        accountInfo = row.split(",");
                        // if (Objects.equals(accountInfo[0], deleteId.getText())) {

                        // }
                    }
                    accountCSVReader.close();
                } catch (Exception ex) {
                    System.out.println("There is error in reading2: " + ex);
                }

            } else if (bank instanceof LinkedList) {
                JOptionPane.showMessageDialog(null, "Sorry, you are not authenticated to remove accounts");
            }
            else if(ae.getSource()==bcheck){
                String row;
                String[] accountInfo = null;
                try {
                    BufferedReader accountCSVReader = new BufferedReader(new FileReader("src/BankManagement/accounts.csv"));
                    while((row=accountCSVReader.readLine())!=null){
                        for(int col=0;col<accountInfo.length;col++) {
                            String m = JOptionPane.showInputDialog(null, "enter the id of the account yo wanna display", "checking");
                            if(m==accountInfo[0])
                                System.out.println("id:"+accountInfo[0]+"\n"+"Number:"+accountInfo[1]+"\n"+"Type:"+accountInfo[2]+"\n");

                        }
                    }

                }
                catch(Exception e){
                    System.out.println("there's error in reading");
                }

            }
            else
             {
                JOptionPane.showMessageDialog(null, "Not instance");
            }
        }

    }

    public static void main(String[] args) throws Exception {



        new AccountMng();


    }
}


