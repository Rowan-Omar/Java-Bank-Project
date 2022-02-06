package com.company;

import java.util.Scanner;


public class BankCustomer {
    private int custId;
    private String custFirstName;
    private String custLastName;
    private String custCity;
    private String custStreet;
    private int custMobile;


    Scanner input = new Scanner(System.in);

    //Constructor for adding customer
    public BankCustomer () {}

    public BankCustomer ( int Id, String FirstName, String LastName, String City, String Street, int Mobile ) {
       custId=Id;
       custFirstName=FirstName;
       custLastName=LastName;
       custCity=City;
       custStreet=Street;
       custMobile=Mobile;
    }

    // ---------- Getters ------------
    public int getCustId () {
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
    //will be called from the menu along with the pin number
    public void setCustId ( int Id ) {
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












