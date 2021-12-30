package customer.src.com.company;

import BankManagement.BankAccount;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class BankCustomer {
    private int custId;
    private String custID;
    private String custFirstName;
    private String custLastName;
    private String custCity;
    private String custStreet;
    private int custMobile;

    BufferedReader customerCSVReader;
    static ArrayList<BankCustomer> arrCustomerFile;

    Scanner input = new Scanner(System.in);

    //Constructor for adding customer
    public BankCustomer() {
        int numRow = -1;
        try {
            customerCSVReader = new BufferedReader(new FileReader("src/customer/custoomer.csv"));
            while (customerCSVReader.readLine() != null) { //this for knowing how many rows (account objects) exist in the Excel file
                numRow++;
            }
            arrCustomerFile = new ArrayList<>(numRow);
        } catch (Exception ex) {
            System.out.println("There is error in reading for the array size: " + ex);
        }

        String row;
        String[] customerInfo;
        int flag = 0;
        try {
            customerCSVReader = new BufferedReader(new FileReader("src/customer/custoomer.csv"));
            while ((row = customerCSVReader.readLine()) != null) {
                if (flag == 0) {
                    flag = 1;
                    continue;
                }
                customerInfo = row.split(",");

                BankCustomer newCustomer;
                newCustomer = new BankCustomer(customerInfo[0], customerInfo[1], customerInfo[2], customerInfo[3]);
                arrCustomerFile.add(newCustomer);
                // System.out.println(arrayFile);
            }
            customerCSVReader.close();
        } catch (Exception ex) {
            System.out.println("There is error in reading in the array: " + ex);
        }

    }

    public BankCustomer(String Id, String firstName, String lastName, String city) {
        custID = Id;
        custFirstName = firstName;
        custLastName = lastName;
        custCity = city;
    }

    public static boolean isValidCust(String id) {
        for (BankCustomer bankCustomer : arrCustomerFile) {
            if (Objects.equals(id, bankCustomer.getCustID()))
                return true;
        }
        return false;
    }

    public BankCustomer(int Id, String FirstName, String LastName, String City, String Street, int Mobile) {
        custId = Id;
        custFirstName = FirstName;
        custLastName = LastName;
        custCity = City;
        custStreet = Street;
        custMobile = Mobile;
    }

    // ---------- Getters ------------
    public int getCustId() {
        return custId;
    }

    public String getCustID() {
        return custID;
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

    public int getCustMobile() {
        return custMobile;
    }


    //-------setters------------
    //will be called from the menu along with the pin number
    public void setCustId(int Id) {
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

    public void setCustMobile(int Mobile) {
        custMobile = Mobile;
    }


}












