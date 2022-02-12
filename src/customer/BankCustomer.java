package Customer;

import BankManagement.BankAccount;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;


public class BankCustomer {
    private String custId;
    private String custFirstName;
    private String custLastName;
    private String custCity;
    private String custStreet;
    private String custMobile;

    public String getAcctID() {
        return acctID;
    }

    public void setAcctID(String acctID) {
        this.acctID = acctID;
    }

    private String acctID;

    private String post; //specified to the admins to know their position in the bank

    private BufferedReader customerCSVReader, adminCSVReader;

    public ArrayList<String> getOperations() {
        return operations;
    }

    private ArrayList<String> operations = new ArrayList<String>();
    static ArrayList<BankCustomer> customerArrayFile;
    public static ArrayList<BankCustomer> adminArrayFile;

    //Constructor for adding customer
    public BankCustomer() {
        int numRow = -1;
        String row;
        int flag = 0;
        int colCount = 0;
        try {
            customerCSVReader = new BufferedReader(new FileReader("src/Customer/Customers.csv"));
            while ((row = customerCSVReader.readLine()) != null) {
                if (flag == 0) {
                    colCount = row.split(",").length;
                    flag = 1;
                }
                numRow++;
            }
            if (numRow != -1)
                customerArrayFile = new ArrayList<>(numRow);
            else{
                System.out.println("File is empty");

            }
        } catch (Exception ex) {
            System.out.println("There is error in reading for the array size: " + ex);
        }

        String[] customerInfo;
        flag = 0;

        try {
            customerCSVReader = new BufferedReader(new FileReader("src/Customer/Customers.csv"));
            while ((row = customerCSVReader.readLine()) != null) {
                if (flag == 0) {
                    flag = 1;
                    continue;
                }
                customerInfo = row.split(",");
                for (int i = 0; i < customerInfo.length; i++) {
                    if (customerInfo[i] == null) {
                        customerInfo[i] = "---";
                    }
                }
                BankCustomer newCustomer;

                newCustomer = new BankCustomer(customerInfo[0], customerInfo[1], customerInfo[2], customerInfo[3], customerInfo[4], customerInfo[5]);
                customerArrayFile.add(newCustomer);
            }
            customerCSVReader.close();
        } catch (Exception ex) {
            System.out.println("There is error in reading in the array: " + ex);
        }


        numRow = -1;
        row = "";
        flag = 0;
        colCount = 0;
        try {
            adminCSVReader = new BufferedReader(new FileReader("src/BankManagement/Admins.csv"));
            while ((row = adminCSVReader.readLine()) != null) {
                if (flag == 0) {
                    colCount = row.split(",").length;
                    flag = 1;
                }
                numRow++;
            }

            adminArrayFile = new ArrayList<>(numRow);
        } catch (Exception ex) {
            System.out.println("There is error in reading for the array size: " + ex);
        }

        String[] adminInfo;
        flag = 0;
        try {
            adminCSVReader = new BufferedReader(new FileReader("src/BankManagement/Admins.csv"));
            while ((row = adminCSVReader.readLine()) != null) {
                if (flag == 0) {
                    flag = 1;
                    continue;
                }
                adminInfo = row.split(",");

                BankCustomer newCustomer;
                if (adminInfo.length == colCount) {
                    newCustomer = new BankCustomer(adminInfo[0], adminInfo[1], adminInfo[2], adminInfo[3], adminInfo[4], adminInfo[5], adminInfo[6]);
                    adminArrayFile.add(newCustomer);
                }

            }
            adminCSVReader.close();
        } catch (Exception ex) {
            System.out.println("There is error in reading in the array: " + ex);
        }
    }

    //this constructor for admins
    public BankCustomer(String adminID, String firstName, String lastName, String post, String accountID, String city, String mobile) {
        custId = adminID;
        custFirstName = firstName;
        custLastName = lastName;
        this.post = post;
        acctID = accountID;
        custCity = city;
        custMobile = mobile;
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
        for (BankCustomer bankcustomer : customerArrayFile) {
            if (Objects.equals(id, bankcustomer.getCustId()))
                return true;
        }
        return false;

    }

    // ---------- Getters ------------
    public String getCustId() {
        return custId;
    }

    public String getPost() {
        return post;
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

    public void setPost(String post) {
        this.post = post;
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

