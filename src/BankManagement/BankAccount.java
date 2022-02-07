package BankManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

import static BankManagement.AccountMng.account;


public class BankAccount extends BankCustomer {

    private String acctId;
    private int custId;
    private String acctName;
    private int acctNo;
    private Date dateOpened;
    private double acctBalance;
    private int alternateAcct; //momken a7oto m3 el acct details
    private String acctCurrency;
    private String acctDetails;
    private String acctType; //whether it is saving account or checking account

    Scanner input = new Scanner(System.in);
    static ArrayList<BankAccount> arrayFile;
    BufferedReader accountCSVReader;

    //Constructor for uploading the accounts from the file to the array
    public BankAccount() {
        //System.out.println(super.id);
        int numRow = -1;
        String row;
        int flag = 0;
        int colCount = 0;
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
            arrayFile = new ArrayList<>(numRow);
        } catch (Exception ex) {
            System.out.println("There is error in reading for the array size: " + ex);
        }

        //arraysFile = new Object[numRow][numCol];

         row = "";
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

                BankAccount newAccount; // ??????????????????????????? there is a question below
                if(accountInfo.length == colCount){
                    newAccount = new BankAccount(accountInfo[0], accountInfo[1], null, accountInfo[2]); // HOW to enhance this to be generic  col not by specifying the index of the array statically
                    newAccount.setBalance(new Double(accountInfo[3]));
                    arrayFile.add(newAccount);
                }else{
                    //fill the empty values with anything
                }
                // System.out.println(arrayFile);
            }
            accountCSVReader.close();
        } catch (Exception ex) {
            System.out.println("There is error in reading in the array: " + ex);
        }
        /*for (int i = 0; i < arrayFile.size(); i++) {
            if (Objects.equals(arrayFile.get(i).getAcctId(), super.id)) {
                System.out.println(arrayFile.get(i).getAcctName());
            }
        }*/
        // new Transaction();
    }

    // public BankAccount(int accountId, String accountName, Date dateOpened, String accountDetails, int accountType, int customerId, int accountNumber) {
    public BankAccount(String accountId, String accountName, Date dateOpened, String accountType) {
        acctId = accountId;
        acctName = accountName;
        this.dateOpened = dateOpened;
        //acctDetails = accountDetails;
        acctType = accountType;
        // custId = customerId;
        // acctNo = accountNumber;
    }

    // ---------- Getters ------------
    public String getAcctId() {
        return acctId;
    }

    public String getAcctName() {
        return acctName;
    }

    public int getAcctNo() {
        return acctNo;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public String getAcctDetails() {
        return acctDetails;
    }

    public String getAcctType() {
        return acctType;
    }

    public int getcustId() {
        return custId;
    }

    public int getAlternateAcct() {
        return alternateAcct;
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

    //-------setters------------
    //will be called from the menu along with the pin number
    public void setAcctId(String accountId) {
        acctId = accountId;
    }

    public void setAcctName(String accountName) {
        acctName = accountName;
    }

    public void setAcctName(int accountNumber) {
        acctNo = accountNumber;
    }

    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    }

    public void setAcctDetails(String accountDetails) {
        acctDetails = accountDetails;
    }

    public void setAcctType(String accountType) {
        acctType = accountType;
    }

    public void setCustId(int customerId) {
        custId = customerId;
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

    public void setAlternateAcct(int accountNo) {
        alternateAcct = accountNo;
    }


    //Methods
    public boolean editAccount(String accountId, int accountNo, String accountDetails, int customerId) {
        if (accountId != acctId && accountNo != acctNo)
            return false; //this account does not exist
        acctDetails = accountDetails;
        custId = customerId;
        return true; //update is done
    }

    /*this can be implemented well in the admin class so to make the object created from this class to refer to null
     instead of referring to an object*/
    public boolean deleteAccount(String accountId, int accountNo) {
        return (accountId == acctId && accountNo == acctNo);
    }

    //Need to be updated
    public static BankAccount findAccount(String accountId) {
        for (int i = 0; i < 5; i++) {
            //code here for searching in a specific file or array or any storage source then return that one with the satisified condition
            //if(accountId == acctId){            }
        }
        return null;
    }

    public String displayAcctDetails(String accountId) {
        if (!Objects.equals(accountId, acctId))
            return "This account does not exist";
        if (custId == 0 || acctName == null || acctNo == 0 || dateOpened == null || acctBalance == 0 || alternateAcct == 0 || acctCurrency == null)
            return "Cannot display the details of this account";
        //could enhance this by searching for the customer id for this account to get its name from the customer class
        return ("Account Name: " + acctName + "\tAccount No.: " + acctNo + "\tDate Opened : " + dateOpened + "\n"
                + "\tCustomer ID: " + custId + "\tBalance: " + acctBalance + " " + getCurrency() + "\tAlternate Account No.: " + alternateAcct);
    }

    //waiting for the transaction class to be implemented
    public void getTransactions() {

    }

    public static boolean isValidAcc(String id) {
        for (BankAccount bankAccount : arrayFile) {
            if (Objects.equals(id, bankAccount.getAcctId()))
                return true;
        }
        return false;
    }

    public static void arrayFileDisplay() {
       // int count;
        if (arrayFile == null || arrayFile.size() == 0) {
            System.out.println("The file is empty");
            return;
        }
       // count = 0;
        for (BankAccount bankAccount : arrayFile) {
            //System.out.println("This is the name of the account no. " + (++count) + " : " + bankAccount.getAcctName());

            System.out.printf("%12s" ,bankAccount.acctId+" | ");
            System.out.printf("%12s" ,bankAccount.custId+" | ");
            System.out.printf("%12s" ,bankAccount.acctNo+" | ");
            System.out.printf("%12s" ,bankAccount.acctBalance+" | ");
            System.out.printf("%12s" ,bankAccount.alternateAcct+" | ");
            System.out.printf("%12s" ,bankAccount.acctType+" | ");

            System.out.println();
        }
    }

}
