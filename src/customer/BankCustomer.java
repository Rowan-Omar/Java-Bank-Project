package Customer;

import BankManagement.BankAccount;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Objects;


public class BankCustomer {
    private String custID;
    private String password = "123";
    private String firstName;
    private String lastName;
    private String acctID;
    private String city;
    private String street;
    private String mobile;
    private String post; //specified to the admins to know their position in the bank
    private ArrayList<String> operations = new ArrayList<String>();


    private BufferedReader customerCSVReader, adminCSVReader;
    private static BufferedWriter customerCSVWriter, adminCSVWriter;


    private static ArrayList<BankCustomer> customerArrayFile;
    private static ArrayList<BankCustomer> adminArrayFile;

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
            else {
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
        custID = adminID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.post = post;
        System.out.println("The post is: " + this.post);
        acctID = accountID;
        this.city = city;
        this.mobile = mobile;
    }


    public BankCustomer(String Id, String FirstName, String LastName, String City, String Street, String Mobile) {
        custID = Id;
        firstName = FirstName;
        lastName = LastName;
        city = City;
        street = Street;
        mobile = Mobile;
    }

    public static void writeToCustFile() {
        try {
            customerCSVWriter = new BufferedWriter(new FileWriter("src/Customer/Customers.csv"));

            customerCSVWriter.write("ID");
            customerCSVWriter.append(','); //to add new column
            customerCSVWriter.write("First Name");
            customerCSVWriter.append(',');
            customerCSVWriter.write("Last Name");
            customerCSVWriter.append(',');
            customerCSVWriter.write("City");
            customerCSVWriter.append(',');
            customerCSVWriter.write("Street");
            customerCSVWriter.append(',');
            customerCSVWriter.write("Mobile");
            if (BankCustomer.getCustArrayFile() == null)
                return;
            for (BankCustomer customer : BankCustomer.getCustArrayFile()) {
                customerCSVWriter.append('\n'); //to add new row
                System.out.println("writing in file " + customer);
                customerCSVWriter.write(customer.getCustID());
                customerCSVWriter.append(',');
                customerCSVWriter.write(customer.getFirstName());
                customerCSVWriter.append(',');
                customerCSVWriter.write(customer.getLastName());
                customerCSVWriter.append(',');
                customerCSVWriter.write(customer.getCity());
                customerCSVWriter.append(',');
                customerCSVWriter.write(customer.getStreet());
                customerCSVWriter.append(',');
                customerCSVWriter.write(customer.getMobile());
            }
            customerCSVWriter.flush();
            customerCSVWriter.close();
        } catch (Exception ex) {
            System.out.println("There is error in writing in customer file: " + ex);
        }
    }

    public static void writeToAdminFile() {
        try {
            adminCSVWriter = new BufferedWriter(new FileWriter("src/BankManagement/Admins.csv"));

            adminCSVWriter.write("ID");
            adminCSVWriter.append(','); //to add new column
            adminCSVWriter.write("First Name");
            adminCSVWriter.append(',');
            adminCSVWriter.write("Last Name");
            adminCSVWriter.append(',');
            adminCSVWriter.write("Post");
            adminCSVWriter.append(',');
            adminCSVWriter.write("Account ID");
            adminCSVWriter.append(',');
            adminCSVWriter.write("City");
            adminCSVWriter.append(',');
            adminCSVWriter.write("Street");
            adminCSVWriter.append(',');
            adminCSVWriter.write("Phone Number");
            if (BankCustomer.getAdminArrayFile() == null)
                return;
            for (BankCustomer admin : BankCustomer.getAdminArrayFile()) {
                adminCSVWriter.append('\n'); //to add new row
                System.out.println("writing in file " + admin);
                adminCSVWriter.write(admin.getCustID());
                adminCSVWriter.append(',');
                adminCSVWriter.write(admin.getFirstName());
                adminCSVWriter.append(',');
                adminCSVWriter.write(admin.getLastName());
                adminCSVWriter.append(',');
                adminCSVWriter.write(admin.getPost());
                adminCSVWriter.append(',');
                adminCSVWriter.write(admin.getAcctID());
                adminCSVWriter.append(',');
                adminCSVWriter.write(admin.getCity());
                adminCSVWriter.append(',');
                adminCSVWriter.write(admin.getStreet());
                adminCSVWriter.append(',');
                adminCSVWriter.write(admin.getMobile());
            }
            adminCSVWriter.flush();
            adminCSVWriter.close();
        } catch (Exception ex) {
            System.out.println("There is error in writing in admin file: " + ex);
        }
    }

    public static boolean isValidCust(String id) {
        /*for (BankCustomer bankcustomer : customerArrayFile) {
            if (Objects.equals(id, bankcustomer.getCustID()))
                return true;
        }*/
        return (getCustomer(id) != null);
    }

    public static boolean isValidPass(String password) {
        System.out.println("pass is " + password);
        for (BankCustomer bankcustomer : customerArrayFile) {
            if (Objects.equals(password, bankcustomer.getPassword()))
                return true;
        }
        return false;
    }

    //Need to be considered this and method "me" in accountMng
    public static BankCustomer getCustomer(String id) {
        for (BankCustomer admin : adminArrayFile) {
            if (Objects.equals(id, admin.getCustID()))
                return admin;
        }
        for (BankCustomer bankcustomer : customerArrayFile) {
            if (Objects.equals(id, bankcustomer.getCustID()))
                return bankcustomer;
        }
        return null;
    }

    // ---------- Getters ------------
    public String getCustID() {
        return custID;
    }

    public String getPassword() {
        return password;
    }

    public String getPost() {
        return post;
    }

    public String getAcctID() {
        return acctID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getMobile() {
        return mobile;
    }

    public ArrayList<String> getOperations() {
        return operations;
    }

    public static ArrayList<BankCustomer> getCustArrayFile() {
        return customerArrayFile;
    }

    public static ArrayList<BankCustomer> getAdminArrayFile() {
        return adminArrayFile;
    }

    //-------setters------------

    public void setCustID(String customerID) {
        custID = customerID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String FirstName) {
        firstName = FirstName;
    }

    public void setLastName(String LastName) {
        lastName = LastName;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setAcctID(String acctID) {
        this.acctID = acctID;
    }

    public void setCity(String City) {
        city = City;
    }

    public void setStreet(String Street) {
        street = Street;
    }

    public void setMobile(String Mobile) {
        mobile = Mobile;
    }


}

