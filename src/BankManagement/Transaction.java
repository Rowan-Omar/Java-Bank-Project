package BankManagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Scanner;

//public class Transaction extends Bank {
    public class Transaction {


    final String ID = Login.id;//=super.id;

    /*    static BankAccount account = AccountMng.account;*/
    public Transaction() {
        System.out.println("Id in transaction is: " + ID);
        //ministatement();
        //Transfer();
    }
 /*  public static void main(String[] args) {

    }*/

    String Cashmoney[] = {"100", "200", "500", "1000", "2000", "3000"};

    void withdrawal() throws Exception {   //withdraw
        JFrame withdraw = new JFrame("Cash withdraw");
        final JComboBox cb = new JComboBox(Cashmoney);
        JButton bwithdraw = new JButton("Withdraw");
        JButton b1otherwith = new JButton("enter other");
        JButton b2with = new JButton("withdraw");
        b2with.setVisible(false);
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
        withdraw.setSize(700, 500);
        withdraw.add(l1with);
        withdraw.add(l2with);
        withdraw.add(l3with);
        withdraw.add(title);
        withdraw.add(bwithdraw);
        withdraw.add(b1otherwith);
        withdraw.add(b2with);
        withdraw.add(cb);
        withdraw.add(other);
        withdraw.setLayout(null);
        withdraw.setVisible(true);
        for (int i = 0; i < BankAccount.arrayFile.size(); i++) {
            if (Objects.equals(BankAccount.arrayFile.get(i).getAcctId(), ID)) {
                System.out.println("the balance is" + BankAccount.arrayFile.get(i).getBalance());
        b1otherwith.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cash = (String) cb.getItemAt(cb.getSelectedIndex());
                cb.setVisible(false);
                other.setVisible(true);
                b1otherwith.setVisible(false);
                bwithdraw.setVisible(false);
                b2with.setVisible(true);
                b2with.setBounds(300, 200, 100, 30);
                b2with.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        long balance = 5000;
                        long amt = Long.parseLong(other.getText());
                        if (balance >= amt) {
                            balance = balance - amt;
                            l3with.setText("balance is " + balance);
                        } else {
                            l3with.setText("error");
                        }
                    }
                });
            }
        });}}
        bwithdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long balance = 5000;
                long amt = Long.parseLong(String.valueOf(cb.getItemAt(cb.getSelectedIndex())));
                if (balance >= amt) {
                    balance = balance - amt;
                    l3with.setText("balance is " + balance);
                } else {
                    l3with.setText("error");
                }
            }
        });
    }


    void deposit() throws Exception {   //deposit
        JFrame deposit = new JFrame("Cash withdraw");
        final JComboBox cb = new JComboBox(Cashmoney);
        JButton bdeposit = new JButton("deposit");
        JButton b1otherdeposit = new JButton("enter other");
        JButton b2deposit = new JButton("deposit");
        b2deposit.setVisible(false);
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
                b2deposit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        long balance = 5000;
                        long amt = Long.parseLong(other.getText());
                        balance = balance + amt;
                        l3deposit.setText("balance is " + balance);

                    }
                });
            }
        });
        bdeposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long balance = 5000;
                long amt = Long.parseLong(String.valueOf(cb.getItemAt(cb.getSelectedIndex())));
                balance = balance + amt;
                l3deposit.setText("balance is " + balance);
            }
        });
    }

    void ministatement() {
        for (int i = 0; i < BankAccount.arrayFile.size(); i++) {
            if (Objects.equals(BankAccount.arrayFile.get(i).getAcctId(), ID)) {
                System.out.println("the num is" + BankAccount.arrayFile.get(i).getAcctName());
                System.out.println("the Type is" + BankAccount.arrayFile.get(i).getAcctType());
                System.out.println("the balance is" + BankAccount.arrayFile.get(i).getBalance());
            }
        }

    }


    void balcheck() throws Exception {

    }

    void Transfer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of the receiver's account: ");
        String accnum = sc.next();
        System.out.println();
        System.out.print("Enter the amount to be transferred: ");
        double balanc = sc.nextDouble();
        System.out.println();
        outer:
        for (int i = 0; i < BankAccount.arrayFile.size(); i++) {
            if (Objects.equals(BankAccount.arrayFile.get(i).getAcctId(), ID)) {
                if (BankAccount.arrayFile.get(i).getBalance() >= balanc) {
                    for (int j = 0; j < BankAccount.arrayFile.size(); j++) {
                        String name = BankAccount.arrayFile.get(j).getAcctName();
                        if (Objects.equals(name, accnum)) {
                            double bal = BankAccount.arrayFile.get(j).getBalance();
                            bal += balanc;
                            BankAccount.arrayFile.get(j).setBalance(bal);
                            System.out.println(BankAccount.arrayFile.get(j).getBalance());
                            BankAccount.arrayFile.get(i).setBalance(BankAccount.arrayFile.get(i).getBalance() - balanc);
                            System.out.println("Your new balance: " + BankAccount.arrayFile.get(i).getBalance());
                            break outer;
                        }
                    }
                }
            }
        }
    }
}
