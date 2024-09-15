/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author MAHEEN A. QURESHI
 */

//HIGHEST LEVEL!
public class PlatinumLevel implements AccountLevel {
    private final Customer customer;

    public PlatinumLevel(Customer customer) {
        this.customer = customer;
    }

    //Customer can withdrawFunds
    @Override
    public void withdrawFunds(double amount) {
        customer.getBankAccount().withdraw(amount);
    }

    @Override
    public void onlinePurchase(double amount) {
        customer.getBankAccount().withdraw(amount);
    }

    //If Customer's balance is greater than 20K, will move back to Gold, If Customer's balance is less than 10K, will move back to Silver
    @Override
    public void whatLevel() {
        double balance = customer.getBankAccount().getBalance();
        if (balance < 10000) {
            customer.setLevel(new SilverLevel(customer) {
                @Override
                public void whatLevel() {
                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }
            });
        } else if (balance < 20000) {
            customer.setLevel(new GoldLevel(customer) {
                @Override
                public void whatLevel() {
                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }
            });
        }
    }

    public void withdrawFund(double amount) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}