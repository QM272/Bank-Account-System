/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author MAHEEN A. QURESHI
 */
import java.io.File;

public class Manager extends User {
    private final ObservableList<Customer> customerList;

    public Manager(String password, String username) {
        super(password, username, "MANAGER");
        this.customerList = FXCollections.observableArrayList();
        loadCustomers(); // Load customers when the manager is instantiated
    }

    // Method to load customer information from files
    private void loadCustomers() {
        File folder = new File(".");
        for (File file : folder.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                Customer customer = Customer.loadFromFile(file.getName());
                if (customer != null) {
                    customerList.add(customer);
                }
            }
        }
    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
        customer.saveToFile(); // Save the new customer to a file
    }

    public void deleteCustomer(Customer customer) {
        customerList.remove(customer);
        File file = new File(customer.getUsername() + ".txt");
        if (file.exists()) {
            file.delete(); // Delete the customer file
        }
    }

    public ObservableList<Customer> getCustomerList() {
        return customerList;
    }
}