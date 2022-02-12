package BankManagement;

import Customer.BankCustomer;
import Customer.CustomerDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;


///public class AccountMng extends Bank implements ActionListener {
public class AccountMng implements ActionListener {

    JFrame f;
    JTextField tId, tNum, tType, removeId, editId, tCust;
    JButton bAdd, bRemove, bDisplay, bDone, bEdit;

    static BankAccount account = new BankAccount();
    LinkedList<BankAccount> bank = new LinkedList<>();

    private final String ID = Login.id;
    private BankAccount myAcc;
    private BankCustomer me;

    private int flag = 0, flagRem = 0; //var 'flag' to check whether the admin has uploaded the final updates before closing or not
    // -1 -> changes happened // 0 -> no changes happened // 1 -> changes have been uploaded

    public AccountMng() {
    }

    public AccountMng(int valid) {
        myAcc = BankAccount.getAccount(ID); // to get the whole object of the BankAccount class of the one that just logged in
        me = me(myAcc);

        f = new JFrame("Accounts");

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

        JLabel lCust = new JLabel("Customer Id");
        lCust.setBounds(200, 300, 100, 30);
        lCust.setFont(new Font("serif", Font.BOLD, 12));
        lCust.setForeground(Color.black);
        f.add(lCust);

        tCust = new JTextField();
        tCust.setBounds(300, 300, 150, 30);
        f.add(tCust);

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


        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (flag == -1) {
                    int choice = JOptionPane.showConfirmDialog(null, "Warning: Changes made have not been uploaded to the file yet\nClick the \"Done\" button\nChoose \"Yes\" to ignore the changes made", "Sure to Exit?", 0, 2);
                    if (choice != 0) {
                        f.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                        return;
                    }
                }
                Bank.bankFrame.setVisible(true);
                f.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            }
        });

        f.setLayout(null);
        f.setSize(900, 600);
        f.setLocation(370, 100);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (BankAccount.accountArrayFile != null && ae.getSource() == bAdd) {
            flag = -1; //to inform that changes happened and to be checked before closing this window


            BankAccount newRow;
            if (Objects.equals(tId.getText(), "")) {
                JOptionPane.showMessageDialog(null, "Sorry, the Account ID field must not be empty");
                return;
            }
            if (tNum.getText().length() == 0)
                tNum.setText("---");
            if (tType.getText().length() == 0)
                tType.setText("---");
            newRow = new BankAccount(tId.getText(), tNum.getText(), LocalDate.now(), tType.getText());

            if (!Objects.equals(CustomerDisplay.custID, null)) {
                newRow.setCustID(CustomerDisplay.custID);
                BankAccount.accountArrayFile.add(newRow);
                JOptionPane.showMessageDialog(null, "This account has been added successfully");
                return;
            }

            JOptionPane.showMessageDialog(null, "Cannot add, customer ID is null");

        } else if (ae.getSource() == bDisplay) {
            BankAccount.arrayFileDisplay();

        } else if (ae.getSource() == bRemove) {
            //if (account instanceof BankAccount) { //to be improved so that we have an unknown object and here we check if it is the admin to delete a specific account
            flag = -1;
            flagRem = 1; // this to update the customer file as well

            String message = "Enter the id of the account to be removed";
            removeId = new JTextField();
            int result = JOptionPane.showOptionDialog(null, new Object[]{message, removeId}, //to know if the user has cancelled the removal operation
                    "Remove", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (BankAccount.accountArrayFile != null && result == 0) {
                if (Objects.equals(removeId.getText(), "")) { //need to be updated so that the remove dialog do not disappear, or we can remove this if condition with no work to be done
                    JOptionPane.showMessageDialog(null, "Enter the account's id to be removed");
                    return;
                }
                boolean isExist = false;
                for (BankAccount account : BankAccount.accountArrayFile) {
                    if (Objects.equals(account.getAcctID(), removeId.getText())) {
                        isExist = true;
                    }
                    if (isExist) {
                        BankCustomer removeCust = BankCustomer.getCustomer(account.getCustID());
                        if ((Objects.equals(me.getPost(), "Manager"))) {
                            if (removeCust == null) {
                                JOptionPane.showMessageDialog(null, "Cannot remove, there is no customer for this account");
                                return;
                            }
                            if (!Objects.equals(Objects.requireNonNull(me(account)).getPost(), "Manager")) {
                                //System.out.println(account.getAcctName()+"  with index "+accountArrayFile.indexOf(account));
                                BankAccount.accountArrayFile.remove(account);
                                BankCustomer.getCustArrayFile().remove(removeCust);
                                JOptionPane.showMessageDialog(null, "This account has been removed successfully");
                                return;
                            }
                            JOptionPane.showMessageDialog(null, "This account cannot be removed");
                            return;
                        }
                        if (BankCustomer.isValidCust(account.getCustID())) { //thus the account to be deleted is not an admin
                            BankAccount.accountArrayFile.remove(account);
                            BankCustomer.getCustArrayFile().remove(removeCust);
                            JOptionPane.showMessageDialog(null, "This account has been removed successfully");
                            return;
                        }
                        JOptionPane.showMessageDialog(null, "This account cannot be removed");
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "This account does not exist");
            /*} else if (bank instanceof LinkedList) {
                JOptionPane.showMessageDialog(null, "Sorry, you are not authenticated to remove accounts");
            } else {
                JOptionPane.showMessageDialog(null, "Not instance");
            }*/

        } else if (ae.getSource() == bEdit) {
            flag = -1;
            String message = "Enter the id of the account to be edited";
            editId = new JTextField();
            int result = JOptionPane.showOptionDialog(null, new Object[]{message, editId}, //to know if the user has cancelled the removal operation
                    "Edit", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            System.out.println("The id is: " + editId.getText());

            //NEED to be updated so that we get to the final account without showing dialog repeatedly until condition is found
            if (myAcc != null && result == 0 && !Objects.equals(editId.getText(), "")) {
                for (BankAccount account : BankAccount.accountArrayFile) {
                    if ((Objects.equals(account.getAcctID(), editId.getText()))) {
                        if ((!Objects.equals(me.getPost(), "Manager"))) {
                            if (Objects.equals(Objects.requireNonNull(me(account)).getPost(), "Manager")
                                    || (!BankCustomer.isValidCust(account.getCustID()))) {
                                JOptionPane.showMessageDialog(null, "You are not authorized");
                                return;
                            }
                        }
                        String[] oldAcc = new String[]{account.getAcctType(), (account.getBalance() + "")};
                        new EditFrame(account, oldAcc);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "This account does not exist");
            }
        } else if (ae.getSource() == bDone) {//upload the changes in the real file
            BankAccount.writeToFile();
            if (flagRem == 1) {
                BankCustomer.writeToFile();
            }
            JOptionPane.showMessageDialog(null, "Accounts excel file has been updated successfully");
            flag = 1; //meaning you can safely close
        } else {
            System.out.println("Invalid button");
        }
    }

    private BankCustomer me(BankAccount acc) {
        for (BankCustomer admin : BankCustomer.getAdminArrayFile()) {
            if (Objects.equals(admin.getCustID(), acc.getCustID())) {
                return admin;
            }
        }
        return null;
    }

}

class EditFrame {
    public EditFrame(BankAccount editedAcc, String[] oldData) { //oldData = ['AcctType', 'AcctBalance']
        JFrame fEdit = new JFrame("Edit");

        JPanel fieldsPnl = new JPanel();

        JLabel lAccType = new JLabel("Account Type:");
        lAccType.setBounds(30, 50, 80, 30);

        JTextField textAccType = new JTextField();
        textAccType.setBounds(130, 50, 100, 30);
        textAccType.setText(oldData[0]);

        JLabel lAccBalance = new JLabel("Account Balance: ");
        lAccBalance.setBounds(300, 50, 125, 30);

        JTextField textAccBalance = new JTextField();
        textAccBalance.setBounds(420, 50, 100, 30);
        textAccBalance.setText(oldData[1]);

        fieldsPnl.add(lAccType);
        fieldsPnl.add(textAccType);
        fieldsPnl.add(lAccBalance);
        fieldsPnl.add(textAccBalance);

        fieldsPnl.setLayout(null);

        JPanel btnPnl = new JPanel();

        JButton bDone = new JButton("Done");
        bDone.setBounds(220, 50, 100, 30);
        bDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String type = textAccType.getText();
                String balance = textAccBalance.getText();
                if (type.length() == 0 || balance.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Text fields cannot be empty");
                    return;
                }
                if (!Objects.equals(type, oldData[0]) || !Objects.equals(balance, oldData[1])) {
                    editedAcc.setAcctType(type);
                    editedAcc.setBalance(new Double(balance));
                    JOptionPane.showMessageDialog(null, "This account has been updated successfully");
                }
                fEdit.setVisible(false);
            }
        });

        JButton bBack = new JButton("Back");
        bBack.setBounds(450, 120, 100, 30);
        bBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fEdit.setVisible(false);
            }
        });

        btnPnl.add(bDone);
        btnPnl.add(bBack);

        btnPnl.setLayout(null);

        fEdit.add(fieldsPnl);
        fEdit.add(btnPnl);

        fEdit.setLayout(new GridLayout(2, 1));
        fEdit.setSize(600, 400);
        fEdit.setLocation(500, 200);
        fEdit.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        fEdit.setVisible(true);
    }

   /* public static void main(String[] args) {
        new EditFrame();
    }*/
}

