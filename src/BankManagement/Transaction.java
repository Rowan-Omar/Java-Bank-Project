package BankManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

//public class Transaction extends Bank {
public class Transaction {

    final String ID = Login.id;//=super.id;
    private BankAccount myAcc;
    private JFrame fTransaction;

    String Cashmoney[] = {"100", "200", "500", "1000", "2000", "3000"};


    public Transaction() {
        myAcc = me();
        System.out.println("Id in transaction is: " + ID);

        fTransaction = new JFrame("Transaction");

        JButton back = new JButton("Back");
        back.setBounds(300, 300, 100, 30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure to exit?", "Exit", 0);
                if (choice == 0) {
                    fTransaction.setVisible(false);
                    Bank.main(null);
                }
            }
        });

        JButton draw = new JButton("Withdraw");
        draw.setBounds(100, 50, 100, 30);
        draw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdrawal();
                fTransaction.setVisible(false);
            }
        });

        JButton deposit = new JButton("Deposit");
        deposit.setBounds(100, 150, 100, 30);

        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
                fTransaction.setVisible(false);
            }
        });

        JButton ministate = new JButton("Mini-Statement");
        ministate.setBounds(300, 50, 120, 30);
        ministate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ministatement();
                fTransaction.setVisible(false);
            }
        });

        JButton trans = new JButton("Transfer");
        trans.setBounds(300, 150, 100, 30);
        trans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Transfer();
                fTransaction.setVisible(false);
            }
        });

        fTransaction.add(draw);
        fTransaction.add(deposit);
        fTransaction.add(ministate);
        fTransaction.add(trans);
        fTransaction.add(back);

        fTransaction.setSize(500, 400);
        fTransaction.setLayout(null);
        fTransaction.setVisible(true);

    }

    private BankAccount me() {
        for (BankAccount account : BankAccount.getAccArrayFile()) {
            if (Objects.equals(account.getAcctID(), ID)) {
                return account;
            }
        }
        return null;
    }

    void withdrawal() {   //withdraw
        JFrame withdraw = new JFrame("Cash withdraw");
        final JComboBox cb = new JComboBox(Cashmoney);
        JButton bwithdraw = new JButton("Withdraw");
        JButton b1otherwith = new JButton("enter other");
        JButton b2with = new JButton("withdraw");
        b2with.setVisible(false);

        JButton back = new JButton("Back");
        back.setBounds(500, 400, 100, 30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw.setVisible(false);
                fTransaction.setVisible(true);
            }
        });

        JTextField other = new JTextField();
        JLabel title = new JLabel("Cash withdraw");
        JLabel l1with = new JLabel("Your account:");
        JLabel l2with = new JLabel("Ammount");
        JLabel l3with = new JLabel();
        other.setVisible(false);
        title.setBounds(50, 50, 150, 50);
        l1with.setBounds(100, 100, 100, 50);
        l2with.setBounds(100, 130, 100, 50);
        l3with.setBounds(400, 400, 100, 50);
        bwithdraw.setBounds(200, 200, 100, 30);
        b1otherwith.setBounds(400, 200, 100, 30);
        other.setBounds(300, 145, 100, 20);
        cb.setBounds(300, 145, 100, 20);

        withdraw.add(l1with);
        withdraw.add(l2with);
        withdraw.add(l3with);
        withdraw.add(title);
        withdraw.add(bwithdraw);
        withdraw.add(b1otherwith);
        withdraw.add(b2with);
        withdraw.add(cb);
        withdraw.add(other);
        withdraw.add(back);

        withdraw.setSize(700, 500);
        withdraw.setLayout(null);
        withdraw.setVisible(true);

        b1otherwith.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cash = (String) cb.getItemAt(cb.getSelectedIndex());
                cb.setVisible(false);
                other.setVisible(true);
                b1otherwith.setVisible(false);
                bwithdraw.setVisible(false);
                b2with.setVisible(true);
                b2with.setBounds(300, 200, 100, 30);
            }
        });

        b2with.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myAcc = me();
                if (myAcc != null) {
                    double balance = myAcc.getBalance();
                    double amt = Long.parseLong(other.getText());
                    if (balance >= amt) {
                        myAcc.setBalance(balance - amt);
                        JOptionPane.showMessageDialog(null, "The money has been withdrawn successfully");
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        myAcc.getOperations().add(myAcc.getOperations().size(), "You withdrawn " + amt + " at " + dtf.format(now));
                        //l3with.setText("balance is " + balance);
                    } else {
                        // l3with.setText("error");
                        JOptionPane.showMessageDialog(null, "Sorry, you do not have enough money to withdraw this amount");
                    }
                }
            }
        });

        bwithdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("first withdraw");
                myAcc = me();
                if (myAcc != null) {
                    double balance = myAcc.getBalance();
                    double amt = Double.parseDouble((cb.getItemAt(cb.getSelectedIndex())).toString());
                    System.out.println("the amount " + amt);
                    if (balance >= amt) {
                        myAcc.setBalance(balance - amt);
                        //l3with.setText("balance is " + balance);
                        JOptionPane.showMessageDialog(null, "The money has been withdrawn successfully");
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        myAcc.getOperations().add(myAcc.getOperations().size(), "You withdrawn " + amt + " at " + dtf.format(now));
                    } else {
                        //l3with.setText("error");
                        JOptionPane.showMessageDialog(null, "Sorry, you do not have enough money to withdraw this amount");
                    }
                }
            }
        });
    }

    void deposit() {   //deposit
        JFrame deposit = new JFrame("Cash withdraw");
        final JComboBox cb = new JComboBox(Cashmoney);
        JButton bdeposit = new JButton("deposit");
        JButton b1otherdeposit = new JButton("enter other");
        JButton b2deposit = new JButton("deposit");
        b2deposit.setVisible(false);

        JButton showBal = new JButton("Show Balance");
        showBal.setBounds(250, 200, 100, 30);
        showBal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, myAcc.getBalance());
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(300, 300, 100, 30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit.setVisible(false);
                fTransaction.setVisible(true);
            }
        });


        JTextField other = new JTextField();
        JLabel title = new JLabel("Cash deposit");
        JLabel l1deposit = new JLabel("Your account:");
        JLabel l2deposit = new JLabel("Ammount");
        JLabel l3deposit = new JLabel();
        other.setVisible(false);

        title.setBounds(50, 50, 150, 50);
        l1deposit.setBounds(100, 100, 100, 50);
        l2deposit.setBounds(100, 130, 100, 50);
        l3deposit.setBounds(400, 400, 100, 50);
        bdeposit.setBounds(200, 200, 100, 30);
        b1otherdeposit.setBounds(400, 200, 100, 30);
        other.setBounds(300, 145, 100, 20);
        cb.setBounds(300, 145, 100, 20);
        deposit.setSize(700, 500);
        deposit.add(l1deposit);
        deposit.add(l2deposit);
        deposit.add(l3deposit);
        deposit.add(title);
        deposit.add(bdeposit);
        deposit.add(b1otherdeposit);
        deposit.add(b2deposit);
        deposit.add(cb);
        deposit.add(other);
        deposit.add(showBal);
        deposit.add(back);

        deposit.setLayout(null);
        deposit.setVisible(true);

        b1otherdeposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cash = (String) cb.getItemAt(cb.getSelectedIndex());
                cb.setVisible(false);
                other.setVisible(true);
                b1otherdeposit.setVisible(false);
                bdeposit.setVisible(false);
                b2deposit.setVisible(true);
                b2deposit.setBounds(300, 200, 100, 30);

            }
        });
        b2deposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myAcc = me();
                if (myAcc != null) {
                    double balance = myAcc.getBalance();
                    double amt = Long.parseLong(other.getText());
                    if (amt < 0)
                        JOptionPane.showMessageDialog(null, "The money must not be negative");

                    myAcc.setBalance(balance + amt);
                    //l3deposit.setText("balance is " + balance);
                    JOptionPane.showMessageDialog(null, "The money has been deposited successfully");
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    myAcc.getOperations().add(myAcc.getOperations().size(), "You deposited " + amt + " at " + dtf.format(now));
                }
            }
        });
        bdeposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myAcc = me();
                if (myAcc != null) {
                    double balance = myAcc.getBalance();
                    double amt = Double.parseDouble((cb.getItemAt(cb.getSelectedIndex())).toString());
                    if (amt < 0)
                        JOptionPane.showMessageDialog(null, "The money must not be negative");

                    myAcc.setBalance(balance + amt);
                    //l3deposit.setText("balance is " + balance);
                    JOptionPane.showMessageDialog(null, "The money has been deposited successfully");
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    myAcc.getOperations().add(myAcc.getOperations().size(), "You deposited " + amt + " at " + dtf.format(now));
                }
            }
        });
    }

    void ministatement() {
        JFrame ministate = new JFrame("Mini-Statement");

        JButton back = new JButton("Back");
        back.setBounds(300, 300, 100, 30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ministate.setVisible(false);
                fTransaction.setVisible(true);
            }
        });
        JLabel num;
        int k = 100;
        for (int i = 0; i < myAcc.getOperations().size(); i++) {
            num = new JLabel(myAcc.getOperations().get(i));
            num.setBounds(50, k, 300, 50);
            k = k + 30;
            ministate.add(num);
        }

        /*JLabel type = new JLabel(myAcc.operations.get(1));
        type.setBounds(100, 130, 100, 50);

        JLabel balance = new JLabel("Account Balance: ");
        balance.setBounds(100, 200, 100, 50);*/

        /*JTextField tNum = new JTextField(myAcc.getAcctNo());
        tNum.setBounds(200, 100, 100, 50);
        tNum.setEnabled(false);
        tNum.setDisabledTextColor(Color.black);

        JTextField tType = new JTextField(myAcc.getAcctType());
        tType.setBounds(200, 130, 100, 50);
        tType.setEnabled(false);
        tType.setDisabledTextColor(Color.black);

        JTextField tBalance = new JTextField(myAcc.getBalance() + "");
        tBalance.setBounds(200, 200, 100, 50);
        tBalance.setEnabled(false);
        tBalance.setDisabledTextColor(Color.black);*/


        /*ministate.add(type);
        ministate.add(balance);
        ministate.add(tNum);
        ministate.add(tType);
        ministate.add(tBalance);*/
        ministate.add(back);

        ministate.setSize(400, 400);
        ministate.setLayout(null);
        ministate.setVisible(true);
    }

    void balKnowledge() {

    }

    void Transfer() {
        JFrame fTransfer = new JFrame("Transfer");

        JLabel toNum = new JLabel("Account Number: ");
        toNum.setBounds(100, 100, 100, 50);

        JLabel amount = new JLabel("Amount: ");
        amount.setBounds(100, 130, 100, 50);

        JTextField textToNum = new JTextField();
        textToNum.setBounds(200, 100, 100, 50);


        JTextField textAmount = new JTextField();
        textAmount.setBounds(200, 130, 100, 50);

        JButton Trans = new JButton("Transfer");
        Trans.setBounds(200, 300, 100, 30);
        Trans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String toNumber = textToNum.getText();
                if (textAmount.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "You must enter amount ");
                    return;
                }
                double textAmt = Double.parseDouble(textAmount.getText());
                if (myAcc.getBalance() < textAmt) {
                    JOptionPane.showMessageDialog(null, "You do not have enough money  ");
                    return;
                }
                for (BankAccount account : BankAccount.getAccArrayFile()) {
                    if (Objects.equals(account.getAcctNo(), toNumber)) {
                        account.setBalance(account.getBalance() + textAmt);
                        myAcc.setBalance(myAcc.getBalance() - textAmt);
                        JOptionPane.showMessageDialog(null, "money transfered");
                        System.out.println(account.getBalance() + "tonum");
                        System.out.println(myAcc.getBalance() + "fromnum");
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "account does not exist");
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(300, 300, 100, 30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fTransfer.setVisible(false);
                fTransaction.setVisible(true);
            }
        });

        fTransfer.add(back);
        fTransfer.add(Trans);
        fTransfer.add(toNum);
        fTransfer.add(amount);
        fTransfer.add(textToNum);
        fTransfer.add(textAmount);

        fTransfer.setSize(400, 400);
        fTransfer.setLayout(null);
        fTransfer.setVisible(true);

    }
}