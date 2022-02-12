package BankManagement;


import Customer.BankCustomer;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

//public class BankAccount extends BankCustomer {
public class BankAccount {
    private String acctID;
    private String custID;
    private String acctNo;
    private LocalDate dateOpened;
    private double acctBalance;
    private String alternateAcctNo; //momken a7oto m3 el acct details
    private String acctCurrency;
    private String acctType; //whether it is saving account or checking account
    private ArrayList<String> operations = new ArrayList<String>();

    private static ArrayList<BankAccount> accountArrayFile;

    //Constructor for uploading the accounts from the file to the array
    public BankAccount() {
        //System.out.println(super.id);
        int numRow = -1;
        String row;
        int flag = 0;
        int colCount = 0;
        BufferedReader accountCSVReader;
        try {
            accountCSVReader = new BufferedReader(new FileReader("src/BankManagement/accounts.csv"));
            while ((row = accountCSVReader.readLine()) != null) { //this for knowing how many rows (account objects) exist in the Excel file
                if (flag == 0) { //this to count the header data of the Excel file to know whether the data is missing a value
                    colCount = row.split(",").length;
                    flag = 1;
                }
                numRow++;
            }
            //System.out.println("There are " + numRow + " entries");
            if (numRow != -1)
                accountArrayFile = new ArrayList<>(numRow);
            else {
                System.out.println("File is empty");
            }
        } catch (Exception ex) {
            System.out.println("There is error in reading for the array size: " + ex);
        }

        //arraysFile = new Object[numRow][numCol];

        String[] accountInfo;
        flag = 0;

        try {
            accountCSVReader = new BufferedReader(new FileReader("src/BankManagement/accounts.csv"));
            while ((row = accountCSVReader.readLine()) != null) {
                if (flag == 0) { //this to skip the header of the Excel file so as not to be added in our array
                    flag = 1;
                    continue;
                }
                accountInfo = row.split(",");

                for (int i = 0; i < accountInfo.length; i++) {
                    if (accountInfo[i] == null) {
                        accountInfo[i] = "---";
                    }
                }

                BankAccount newAccount; // ??????????????????????????? there is a question below
                if (accountInfo.length == colCount) {
                    newAccount = new BankAccount(accountInfo[0], accountInfo[1], LocalDate.now(), accountInfo[2]); // HOW to enhance this to be generic  col not by specifying the index of the array statically
                    newAccount.setCustID(accountInfo[3]);
                    newAccount.setBalance(new Double(accountInfo[4]));
                    accountArrayFile.add(newAccount);
                }
                // System.out.println(arrayFile);
            }
            accountCSVReader.close();
        } catch (Exception ex) {
            System.out.println("There is error in reading in the array: " + ex);
        }

    }

    // public BankAccount(int accountId, String accountName, Date dateOpened, String accountDetails, int accountType, int customerId, int accountNumber) {
    public BankAccount(String accountId, String accountNo, LocalDate dateOpened, String accountType) {
        acctID = accountId;
        acctNo = accountNo;
        this.dateOpened = dateOpened;
        acctType = accountType;
    }

    public static void writeToFile() {
        try {
            BufferedWriter accountCSVWriter = new BufferedWriter(new FileWriter("src/BankManagement/accounts.csv"));
            accountCSVWriter.write("ID");
            accountCSVWriter.append(',');
            accountCSVWriter.write("Number");
            accountCSVWriter.append(',');
            accountCSVWriter.write("Type");
            accountCSVWriter.append(',');
            accountCSVWriter.write("Customer ID");
            accountCSVWriter.append(',');
            accountCSVWriter.write("Balance");
            for (BankAccount account : BankAccount.accountArrayFile) {
                accountCSVWriter.append('\n');
                accountCSVWriter.append(account.getAcctID());
                accountCSVWriter.append(',');
                accountCSVWriter.append(account.getAcctNo());
                accountCSVWriter.append(',');
                accountCSVWriter.write(account.getAcctType());
                accountCSVWriter.append(',');
                accountCSVWriter.write(account.getCustID());
                accountCSVWriter.append(',');
                accountCSVWriter.write(account.getBalance() + "");
            }

            accountCSVWriter.flush();
            accountCSVWriter.close();

        } catch (Exception ex) {
            System.out.println("There is error in writing in file: " + ex);
        }
    }


    // ---------- Getters ------------
    public String getAcctID() {
        return acctID;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public LocalDate getDateOpened() {
        return dateOpened;
    }

    public String getAcctType() {
        return acctType;
    }

    public String getCustID() {
        return custID;
    }

    public String getAlternateAcct() {
        return alternateAcctNo;
    }

    public double getBalance() {
        return acctBalance;
    }

    public String getCurrency() {
        switch (acctCurrency) {
            case "EGP":
                return "pounds";
            case "USD":
                return "dollars";
            case "EUR":
                return "euro";
            default:
                return acctCurrency;
        }
    }

    public static BankAccount getAccount(String ID) {
        for (BankAccount account : BankAccount.accountArrayFile) {
            if (Objects.equals(account.getAcctID(), ID)) {
                return account;
            }
        }
        return null;
    }

    public ArrayList<String> getOperations() {
        return operations;
    }

    public static ArrayList<BankAccount> getAccArrayFile() {
        return accountArrayFile;
    }

    //-------setters------------
    //will be called from the menu along with the pin number
    public void setAcctID(String accountId) {
        acctID = accountId;
    }

    public void setAcctNo(String accountNo) {
        acctNo = accountNo;
    }

    public void setDateOpened(LocalDate dateOpened) {
        this.dateOpened = dateOpened;
    }


    public void setAcctType(String accountType) {
        acctType = accountType;
    }

    public void setCustID(String customerId) {
        custID = customerId;
    }

    public boolean setBalance(double balance) {
        if (balance < 0) {
            System.out.println("Balance cannot be less than zero");
            return false;
        }
        acctBalance = balance;
        return true;
    }

    public boolean setBalance(double balance, String currency) {
        acctBalance = balance;
        /*if the added currency is different from the original one
         So it needs to be converted to the original one*/
        if (acctCurrency != currency)
            return false;
        acctCurrency = currency;
        return true;
    }

    /*public void calcBalanceWithdraw(double amount) { // when the user withdraw some amount // we could then return the new balance
        acctBalance -= amount;
    }

    public void calcBalanceDeposite(double amount) {
        acctBalance += amount;
    }

    public void getWithdrawInput() { //getting the amount to be withdrawn from the user
        System.out.println("Enter the amount to withdraw from your account: ");
        double amount = input.nextDouble();
        if ((acctBalance - amount) >= 0) {
            calcBalanceWithdraw(amount);
            System.out.println("New account balance: " + getBalance());
        } else {
            System.out.println("Sorry, you do not have this amount in your account balance");
        }
    }

    public void getDepositeInput() { //getting the amount to be deposited from the user
        System.out.println("Enter the amount to withdraw from your account: ");
        double amount = input.nextDouble();
        if (amount > 0) {
            calcBalanceDeposite(amount);
            System.out.println("New account balance: " + getBalance());
        } else {
            System.out.println("Sorry, the deposited amount cannot be negative or zero");
        }
    }*/

    public void setAlternateAcct(String accountNo) {
        alternateAcctNo = accountNo;
    }


    //Methods
    public boolean editAccount(String accountId, String accountNo, String customerId) {
        if (accountId != acctID && accountNo != acctNo)
            return false; //this account does not exist

        custID = customerId;
        return true; //update is done
    }

    /*this can be implemented well in the admin class so to make the object created from this class to refer to null
     instead of referring to an object*/
    public boolean deleteAccount(String accountId, String accountNo) {
        return (accountId == acctID && accountNo == acctNo);
    }

    //Need to be updated
    public static BankAccount findAccount(String accountId) {
        for (int i = 0; i < 5; i++) {
            //code here for searching in a specific file or array or any storage source then return that one with the satisified condition
            //if(accountId == acctID){            }
        }
        return null;
    }

    public String displayAcctDetails(String accountId) {
        if (!Objects.equals(accountId, acctID))
            return "This account does not exist";
        if (custID == null || acctNo == null || dateOpened == null || acctBalance == 0 || alternateAcctNo == null || acctCurrency == null)
            return "Cannot display the details of this account";
        //could enhance this by searching for the customer id for this account to get its name from the customer class
        return ("Account No.: " + acctNo + "\tDate Opened : " + dateOpened + "\n"
                + "\tCustomer ID: " + custID + "\tBalance: " + acctBalance + " " + getCurrency() + "\tAlternate Account No.: " + alternateAcctNo);
    }

    //waiting for the transaction class to be implemented
    public void getTransactions() {

    }

    public static boolean isValidAcc(String id) {
        for (BankAccount bankAccount : accountArrayFile) {
            if (Objects.equals(id, bankAccount.getAcctID()))
                return true;
        }
        return false;
    }

    public static void arrayFileDisplay() {
        // int count;
        if (accountArrayFile == null || accountArrayFile.size() == 0) {
            System.out.println("The file is empty");
            return;
        }
        // count = 0;
        for (BankAccount bankAccount : accountArrayFile) {
            //System.out.println("This is the name of the account no. " + (++count) + " : " + bankAccount.getAcctName());

            System.out.printf("%12s", bankAccount.acctID + " | ");
            System.out.printf("%12s", bankAccount.custID + " | ");
            System.out.printf("%12s", bankAccount.acctNo + " | ");
            System.out.printf("%12s", bankAccount.acctBalance + " | ");
            System.out.printf("%12s", bankAccount.alternateAcctNo + " | ");
            System.out.printf("%12s", bankAccount.acctType + " | ");

            System.out.println();
        }
    }

}