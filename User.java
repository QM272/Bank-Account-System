/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author MAHEEN A. QURESHI
 */

/*OVERVIEW:

The user class is the heart of the code, assigning whoever uses the account with a username,
password and the role of either the manager or customer, based on what they select.
IT IS MUTABLE. Being mutable means that its instance variables can be changed.

ABSTRACTION FUNCTION: AF(x) = {x.username -> username, x.password -> password, x.role -> role "MANAGER" or "CUSTOMER"}

REQUIRES: setUsername(String username) cannot have username be null, setPassword(String password)
cannot have password be null, setRole(String role) cannot have role be null.

MODIFIES: setUsername(String username) modifies username, setPassword(String password) modifies
the password, setRole(string role) modifies the role

EFFECTS: getUsername(), getPassword() and getRole() return the username, password and role respectively,
setUsername(String username), setPassword(String password) and setRole(String role) set the username,
password and role respectively. loginAccount(..)tests to see if the correct username and password have been
input into the system, resulting in wether you log in or not.
logoutAccount() returns that the person using the program has been logged out.

REP INVARIANT: Username, Password, Role CANNOT be null




*/

public class User {
    //Instance Variables hold values held by all users regardless of type
    private String password;
    private String role;
    private String username;

    public User(String password, String username, String role) {
        this.password = password;
        this.username = username;
        this.role = role;
    }

    //Initializing Username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //Initializing Password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Initializing Role(Manager or Customer??)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //Login and Logout Details
    public boolean loginAccount(String yourUsername, String yourPassword) {
        return this.username.equals(yourUsername) && this.password.equals(yourPassword);
    }

    public void logoutAccount() {
        System.out.println("YOU HAVE BEEN LOGGED OUT");
    }
    
    //toString() returns the username, password and role of the user
    @Override
    public String toString(){
        return "UN: " + username + ", PASSWORD: " + password + "ROLE: " + role;
    }
    
    //Follows requirements stated above, no user can have a null username, password or role.
    private boolean repOK(){
        return username != null && password != null && role!= null;
    }
}