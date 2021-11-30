package BankManagement;

import java.util.Date;

public class BankAccounts {
    private int acctID;
    private String acctName;
    private Date dateOpened;
    private String acctDetails;
    private int acctTypeCode;
    private int custID;

    public BankAccounts(int accountID, String accountName, Date dateOpened, String accountDetails, int accountTypeCode, int customerID) {
        acctID = accountID;
        acctName = accountName;
        dateOpened = dateOpened;
        acctDetails = accountDetails;
        acctTypeCode = accountTypeCode;
        custID = customerID;
    }

    public int getAcctID() {
        return acctID;
    }

    public String getAcctName() {
        return acctName;
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

    public void getAcctID(int accountID) {
        acctID = accountID;
    }

    public void getAcctName(String accountName) {
        acctName = accountName;
    }

    public void getDateOpened(Date dateOpened) {
        dateOpened = dateOpened;
    }

    public void getAcctDetails(String accountDetails) {
        acctDetails = accountDetails;
    }

    public void getAcctTypeCode(int accountTypeCode) {
        acctTypeCode = accountTypeCode;
    }

    public void getCustID(int customerID) {
        custID = customerID;
    }


}
