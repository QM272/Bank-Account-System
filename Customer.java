/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author MAHEEN A. QURESHI
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Customer extends User{
    private final BankAccount bankAccount;
    private AccountLevel level;
    private double balance;
    
    public Customer(String password, String username, double balance) {
        super(password, username, "CUSTOMER");
                this.bankAccount = new BankAccount();
                this.bankAccount.setBalance(balance);
        this.level = new SilverLevel(this) {};
        this.balance=balance;
    }

    //All of the Customer's actions that they may do
    public void withdrawFunds(double amount) {
        level.withdrawFunds(amount);
    }

    public double getBalance() {
        return bankAccount.getBalance();
    }

    public void onlinePurchase(double amount) {
        level.onlinePurchase(amount);
    }

    public void setLevel(AccountLevel level) {
        this.level = level;
    }

    public AccountLevel getLevel() {
        return level;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }
    
    public double getAccountBalance(){
        return balance;
    }
    
    // Will take the customer and write their information to a file. I used a try catch block to ensure it is saved/loaded 
        public void saveToFile() {
        try (FileWriter writer = new FileWriter(getUsername() + ".txt")) {
            writer.write("Username: " + getUsername() + "\n");
            writer.write("Password: " + getPassword() + "\n");
            writer.write("Balance: " + getAccountBalance() + "\n");
        } catch (IOException e) {
            System.out.println("ERROR" + e.getMessage());
        }
    }

        //Will take the customer and get their information from the saved files. A try catch block is used to get the username, password and show the balance.
    public static Customer loadFromFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            String username = scanner.nextLine().split(": ")[1];
            String password = scanner.nextLine().split(": ")[1];
            double balance = Double.parseDouble(scanner.nextLine().split(": ")[1]);

            Customer customer = new Customer(username, password,balance);
            customer.getBankAccount().setBalance(balance);
            return customer;
        } catch (IOException e) {
            System.out.println("Error loading customer information from file: " + e.getMessage());
            return null;
        }
    }
}
