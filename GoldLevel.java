/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author MAHEEN A. QURESHI
 */

//SECOND HIGHEST CLASS
public class GoldLevel implements AccountLevel {
    private final Customer customer;

    public GoldLevel(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void withdrawFunds(double amount) {
        customer.getBankAccount().withdraw(amount + 10);
    }

    @Override
    public void onlinePurchase(double amount) {
        customer.getBankAccount().withdraw(amount + 10);
    }

    //IF BALANCE is greater than 20K, moves to platinum, if balance is less than 10K, moves level to Silver
    @Override
    public void whatLevel() {
        double balance = customer.getBankAccount().getBalance();
        if (balance >= 20000) {
            customer.setLevel(new PlatinumLevel(customer));
        } else if (balance < 10000) {
            customer.setLevel(new SilverLevel(customer) {
                @Override
                public void whatLevel() {
                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }
            });
        }
    }


}