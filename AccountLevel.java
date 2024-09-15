/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package coe528.project;

/**
 *
 * @author MAHEEN A. QURESHI
 */

//The following interface holds the withdrawFunds, onlinePurchase and whatLevel methods, which are referenced based on levels and are linked to ONLY Customers
public interface AccountLevel {
    void withdrawFunds(double amount);
    void onlinePurchase(double amount);
    void whatLevel();
}
