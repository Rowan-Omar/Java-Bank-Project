package BankManagement;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class BankCustomer {
    private String custId;
    private String custFirstName;
    private String custLastName;
    private String custCity;
    private String custStreet;
    private int custMobile;


    Scanner input = new Scanner(System.in);
    static ArrayList<BankAccount> arrayFile;
    //Constructor for adding customer
    public BankCustomer () {}

    public BankCustomer ( String Id, String FirstName, String LastName, String City, String Street, int Mobile ) {
        custId=Id;
        custFirstName=FirstName;
        custLastName=LastName;
        custCity=City;
        custStreet=Street;
        custMobile=Mobile;
    }

    public static boolean isValidCust ( String id ) {
        for (BankCustomer customer : arrayFile) {
            if (Objects.equals(id, customer.getCustId()))
                return true;
        }
        return false;
    }

    // ---------- Getters ------------
    public String getCustId () {
        return custId;
    }

    public String getCustFirstName () {
        return custFirstName;
    }

    public String getCustLastName () {
        return custLastName;
    }

    public String getCustCity () {
        return custCity;
    }

    public String getCustStreet () {
        return custStreet;
    }

    public int getCustMobile () {
        return custMobile;
    }


    //-------setters------------

    public void setCustId ( String Id ) {
        custId = Id;
    }

    public void setCustFirstName ( String FirstName ) {
        custFirstName = FirstName;
    }

    public void setCustLastName ( String LastName ) {
        custLastName = LastName;
    }

    public void setCustCity ( String City ) {
        custCity = City;
    }

    public void setCustStreet ( String Street ) {
        custStreet = Street;
    }

    public void setCustMobile ( int Mobile ) {
        custMobile = Mobile;
    }


}


