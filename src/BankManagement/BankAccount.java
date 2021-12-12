package BankManagement;

import java.util.Date;

public class BankAccount {
    private int acctID;
    private int custID;
    private String acctName;
    private int acctNo;
    private Date dateOpened;
    private float acctBalance;
    private int alternateAcct; //momken a7oto m3 el acct details
    private String acctCurrency;
    private String acctDetails;
    private int acctTypeCode;

    //Constructor for adding account
    public BankAccount(int accountID, String accountName, Date dateOpened, String accountDetails, int accountTypeCode, int customerID, int accountNumber) {
        acctID = accountID;
        acctName = accountName;
        this.dateOpened = dateOpened;
        acctDetails = accountDetails;
        acctTypeCode = accountTypeCode;
        custID = customerID;
        acctNo = accountNumber;
    }

    // ---------- Getters ------------
    public int getAcctID() {
        return acctID;
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

    public int getAcctTypeCode() {
        return acctTypeCode;
    }

    public int getCustID() {
        return custID;
    }

    public int getAlternateAcct() {
        return alternateAcct;
    }

    public float getBalance() {
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
                return  acctCurrency;
        }
    }

    //-------setters------------
    public void setAcctID(int accountID) {
        acctID = accountID;
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

    public void setAcctTypeCode(int accountTypeCode) {
        acctTypeCode = accountTypeCode;
    }

    public void setCustID(int customerID) {
        custID = customerID;
    }

    public boolean setBalance(float balance, String currency) {
        acctBalance = balance;
        /*if the added currency is different from the original one
         So it needs to be converted to the original one*/
        if (acctCurrency != currency)
            return false;
        acctCurrency = currency;
        return true;
    }

    public void setAlternateAcct(int accountNo) {
        alternateAcct = accountNo;
    }


    //Methods
    public boolean editAccount(int accountID, int accountNo, String accountDetails, int customerID) {
        if (accountID != acctID && accountNo != acctNo)
            return false; //this account does not exist
        acctDetails = accountDetails;
        custID = customerID;
        return true; //update is done
    }

    /*this can be implemented well in the admin class so to make the object created from this class to refer to null
     instead of referring to an object*/
    public boolean deleteAccount(int accountID, int accountNo) {
        return (accountID == acctID && accountNo == acctNo);
    }

    //Need to be updated
    public static BankAccount findAccount(int accountID) {
        for (int i = 0; i < 5; i++) {
            //code here for searching in a specific file or array or any storage source then return that one with the satisified condition
            //if(accountID == acctID){            }
        }
        return null;
    }

    public String displayAcctDetails(int accountID) {
        if (accountID != acctID)
            return "This account does not exist";
        if (custID == 0 || acctName == null || acctNo == 0 || dateOpened == null || acctBalance == 0 || alternateAcct == 0 || acctCurrency == null)
            return "Cannot display the details of this account";
        //could enhance this by searching for the customer id for this account to get its name from the customer class
        return ("Account Name: " + acctName + "\tAccount No.: " + acctNo + "\tDate Opened : " + dateOpened + "\n"
                + "\tCustomer ID: " + custID + "\tBalance: " + acctBalance + " " + getCurrency() + "\tAlternate Account No.: " + alternateAcct);
    }

    //waiting for the transaction class to be implemented
    public void getTransactions(){

    }

}
