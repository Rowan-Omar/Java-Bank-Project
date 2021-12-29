package BankManagement;

import java.util.Date;
import java.util.Objects;
import java.util.Scanner;


public class BankAccount {
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

    //Constructor for adding account
    public BankAccount() {
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
        acctBalance = balance;
        /*if the added currency is different from the orhiginal one
         So it needs to be converted to the original one*/
        /*if (acctCurrency != currency)
            return false;*/
        //acctCurrency = currency;
        return true;
    }

    public void calcBalanceWithdraw(float amount) { // when the user withdraw some amount // we could then return the new balance
        acctBalance -= amount;
    }

    public void calcBalanceDeposite(float amount) {
        acctBalance += amount;
    }

    public void getWithdrawInput() { //getting the amount to be withdrawn from the user
        System.out.println("Enter the amount to withdraw from your account: ");
        float amount = input.nextFloat();
        if ((acctBalance - amount) >= 0) {
            calcBalanceWithdraw(amount);
            System.out.println("New account balance: " + getBalance());
        } else {
            System.out.println("Sorry, you do not have this amount in your account balance");
        }
    }

    public void getDepositeInput() { //getting the amount to be deposited from the user
        System.out.println("Enter the amount to withdraw from your account: ");
        float amount = input.nextFloat();
        if (amount > 0) {
            calcBalanceDeposite(amount);
            System.out.println("New account balance: " + getBalance());
        } else {
            System.out.println("Sorry, the deposited amount cannot be negative or zero");
        }
    }

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

}
