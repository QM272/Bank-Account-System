/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author MAHEEN A. QURESHI
 */

//LOWEST LEVEL
public class SilverLevel implements AccountLevel{
    private final Customer customer;
    
    public SilverLevel(Customer customer){
        this.customer=customer;
    }
    
    @Override
    public void withdrawFunds(double amount) {
        customer.getBankAccount().withdraw(amount + 20);
    }

    @Override
    public void onlinePurchase(double amount) {
        customer.getBankAccount().withdraw(amount + 20);
    }

    @Override
    //If Level is greater than 20K, moves to Platinum. If level is between, moves to gold
    public void whatLevel() {
        double balance = customer.getBankAccount().getBalance();
        if (balance >= 20000) {
            customer.setLevel(new PlatinumLevel(customer));
        } else if (balance >= 10000) {
            customer.setLevel(new GoldLevel(customer) {
                @Override
                public void whatLevel() {
                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }
            });
        }
    }


}