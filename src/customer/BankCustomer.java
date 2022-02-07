package Customer;

import BankManagement.BankAccount;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class BankCustomer {
    private String custId;
    private String custFirstName;
    private String custLastName;
    private String custCity;
    private String custStreet;
    private String custMobile;

    BufferedReader accountCSVReader;


    static ArrayList<BankCustomer> arrayFile;

    //Constructor for adding customer
    public BankCustomer() {
        int numRow = -1;
        String row;
        int flag = 0;
        int colCount = 0;
        try {
            accountCSVReader = new BufferedReader(new FileReader("src/Customer/customer.csv"));
            while ((row = accountCSVReader.readLine()) != null) {
                if (flag == 0) {
                    colCount = row.split(",").length;
                    flag = 1;
                }
                numRow++;
            }

            arrayFile = new ArrayList<>(numRow);
        } catch (Exception ex) {
            System.out.println("There is error in reading for the array size: " + ex);
        }

        String[] accountInfo;
        flag = 0;

        try {
            accountCSVReader = new BufferedReader(new FileReader("src/Customer/customer.csv"));
            while ((row = accountCSVReader.readLine()) != null) {
                if (flag == 0) {
                    flag = 1;
                    continue;
                }
                accountInfo = row.split(",");

                BankCustomer newAccount;
                if (accountInfo.length == colCount) {
                    newAccount = new BankCustomer(accountInfo[0], accountInfo[1], accountInfo[2], accountInfo[3], accountInfo[4], accountInfo[5]);
                    arrayFile.add(newAccount);
                } else {
                    //fill the empty values with anything
                }

            }
            accountCSVReader.close();
        } catch (Exception ex) {
            System.out.println("There is error in reading in the array: " + ex);
        }
    }

    public BankCustomer(String Id, String FirstName, String LastName, String City, String Street, String Mobile) {
        custId = Id;
        custFirstName = FirstName;
        custLastName = LastName;
        custCity = City;
        custStreet = Street;
        custMobile = Mobile;
    }

    public static boolean isValidCust(String id) {
        for (BankCustomer customer : arrayFile) {
            if (Objects.equals(id, customer.getCustId()))
                return true;
        }
        return false;
    }

    // ---------- Getters ------------
    public String getCustId() {
        return custId;
    }

    public String getCustFirstName() {
        return custFirstName;
    }

    public String getCustLastName() {
        return custLastName;
    }

    public String getCustCity() {
        return custCity;
    }

    public String getCustStreet() {
        return custStreet;
    }

    public String getCustMobile() {
        return custMobile;
    }


    //-------setters------------

    public void setCustId(String Id) {
        custId = Id;
    }

    public void setCustFirstName(String FirstName) {
        custFirstName = FirstName;
    }

    public void setCustLastName(String LastName) {
        custLastName = LastName;
    }

    public void setCustCity(String City) {
        custCity = City;
    }

    public void setCustStreet(String Street) {
        custStreet = Street;
    }

    public void setCustMobile(String Mobile) {
        custMobile = Mobile;
    }


}

