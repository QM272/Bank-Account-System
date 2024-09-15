
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package coe528.project;

//JavaFX Imports
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.*;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

/**
 *
 * @author MAHEEN A. QURESHI
 */
public class BankAccount extends Application {
    private double balance = 100.0;
    private Label levelLabel = new Label("Level: ");
    @Override
      public void start(Stage primaryStage) {
        primaryStage.setTitle("Bank Account");

//FIRST SCREEN: End ser selects their role
        Label mainLabel = new Label("Please Select Your Role.");
        Button managerButton = new Button("Manager");
        Button customerButton = new Button("Customer");

        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(30));
        mainLayout.getChildren().addAll(mainLabel, managerButton, customerButton);

//MANAGER LOGIN SCREEN
        Label loginLabel = new Label("Manager Login");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        Button loginButton = new Button("Login");
        Button backButton = new Button("Back");

        GridPane loginLayout = new GridPane();
        loginLayout.setPadding(new Insets(20));
        loginLayout.setVgap(10);
        loginLayout.setHgap(10);
        loginLayout.addRow(0, loginLabel);
        loginLayout.addRow(1, new Label("Username:"), usernameField);
        loginLayout.addRow(2, new Label("Password:"), passwordField);
        loginLayout.add(loginButton, 1, 3);
        loginLayout.add(backButton, 0, 3);
        
//MANAGER OPTIONS SCREEN
Button viewCustomersButton = new Button("View Customers");
Button logoutButton = new Button("Logout");

VBox managerScreen = new VBox(20);
managerScreen.setPadding(new Insets(30));
managerScreen.getChildren().addAll(viewCustomersButton, logoutButton);



//CUSTOMER LOGIN SCREEN
        Label loginLabel1 = new Label("Customer Login");
        TextField usernameField1 = new TextField();
        usernameField1.setPromptText("Username");
        PasswordField passwordField1 = new PasswordField();
        passwordField1.setPromptText("Password");
        Button loginButton1 = new Button("Login");
        Button backButton1 = new Button("Back");

        GridPane loginLayout1 = new GridPane();
        loginLayout1.setPadding(new Insets(20));
        loginLayout1.setVgap(10);
        loginLayout1.setHgap(10);
        loginLayout1.addRow(0, loginLabel1);
        loginLayout1.addRow(1, new Label("Username:"), usernameField1);
        loginLayout1.addRow(2, new Label("Password:"), passwordField1);
        loginLayout1.add(loginButton1, 1, 3);
        loginLayout1.add(backButton1, 0, 3);

//Stackpane: used to put screens one ontop of another. This is the main one being used in the code.
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(mainLayout);

//MANAGER LOGIN SWITCH
managerButton.setOnAction(e -> {
            stackPane.getChildren().removeAll(mainLayout); // Remove mainLayout
            stackPane.getChildren().add(loginLayout); // Add loginLayout
        });

//CUSTOMER LOGIN SWITCH
customerButton.setOnAction(e -> {
            stackPane.getChildren().removeAll(mainLayout); // Remove mainLayout
            stackPane.getChildren().add(loginLayout1); // Add loginLayout1
        });

//MANAGER LOGIN
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (username.equals("admin") && password.equals("admin")) {
                stackPane.getChildren().removeAll(loginLayout); // Remove loginLayout
                stackPane.getChildren().add(managerScreen); // Add managerScreen
            } else {
                showAlert("Incorrect username or password");
            }
        });



//MANAGER LOGGING OUT
logoutButton.setOnAction(e -> {
    stackPane.getChildren().removeAll(managerScreen);
    stackPane.getChildren().add(mainLayout);
});

VBox customerDetails = new VBox(20);
customerDetails.setPadding(new Insets(30));
customerDetails.getChildren().addAll(levelLabel);

//CUSTOMER LOGGING IN
            loginButton1.setOnAction(e -> {
            String username = usernameField1.getText();
            String password = passwordField1.getText();
            
            
             String file=username+".txt";
               File f = new File(file);
              
               if(!f.exists()){
                   showAlert("File Does Not Exist in Database.");
                   return;
               }
               try{
                   Scanner s = new Scanner(f);
                   boolean passwordChecker = false;
                   
                   while(s.hasNextLine()){
                       String l = s.nextLine();
                       if(l.startsWith("Password:")){
                           String passwordA = l.substring("Password:".length()).trim();
                           passwordChecker = password.equals(passwordA);
                           break;
                       }
                   }
                   s.close();
                   
                   if(passwordChecker){
                       showAlert("LOGGED IN SUCCESSFULLY");
                       //CUSTOMER BALANCE, DEPOSIT, WITHDRAWL AND PURCHASE SCREEN
                       Customer customer = new Customer(password,username,balance);
                        Label balanceLabel = new Label("Balance: $"+ customer.getAccountBalance());
                        Label depositLabel = new Label("Deposit Amount:");
                        Label withdrawLabel = new Label("Withdraw Amount:");
    
                        TextField depositField = new TextField();
                        TextField withdrawField = new TextField();

        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");
        Button onlinePurchaseButton = new Button("Online Purchase");
        
    stackPane.getChildren().removeAll(loginButton1);
    stackPane.getChildren().add(onlinePurchaseButton);
    
         //ONLINE STORE
            onlinePurchaseButton.setOnAction(ev ->{
                
                Label storeLabel = new Label("Maheen's Store <3");
                Label balanceLabel1 = new Label("Balance: $"+customer.getBankAccount().getBalance());
                Button getNailsButton = new Button("Cherry Acrylic Nails Set - $90 ");
                Button backButton9 = new Button("Back");
                
getNailsButton.setOnAction(ed -> {
    double balance1 = customer.getBankAccount().getBalance();
    if (balance1 >= 90 && balance1 <= 10000) {
        customer.getBankAccount().withdraw(90 + 20);
        balanceLabel1.setText("Balance: $" + balance1);
        showAlert("BOUGHT! Cherry Acrylic Nails Set");
    } else if (balance1 >= 90 && balance1 < 20000) {
        customer.getBankAccount().withdraw(90 + 10);
        balanceLabel1.setText("Balance: $" + balance1);
    } else if (balance1 >= 20000) {
        customer.getBankAccount().withdraw(90);
        balanceLabel1.setText("Balance: $" + balance1);
    } else {
        showAlert("INSUFFICIENT FUNDS");
    }
    customer.getBankAccount().setBalance(customer.getBankAccount().getBalance());
});
            
    
                                
            VBox maheensStore = new VBox(20);
            maheensStore.setPadding(new Insets(30));
            maheensStore.getChildren().addAll(storeLabel,getNailsButton,balanceLabel1,backButton9);
            maheensStore.setAlignment(Pos.CENTER);
            
            stackPane.getChildren().clear();
            stackPane.getChildren().add(maheensStore);
            
             backButton9.setOnAction(backEvent ->{
                    stackPane.getChildren().clear();
                    stackPane.getChildren().add(customerDetails);
                });
                });               

                    
                    // DEPOSIT BUTTON
depositButton.setOnAction(ev -> {
    try {
        double depositAmount = Double.parseDouble(depositField.getText());
        if (depositAmount > 0) {
            customer.getBankAccount().deposit(depositAmount);
            balanceLabel.setText("Balance: $" + customer.getBankAccount().getBalance());
            // Update level label based on balance
            String level;
            if (customer.getBankAccount().getBalance() <= 10000) {
                level = "Silver";
            } else if (customer.getBankAccount().getBalance() < 20000 && customer.getBankAccount().getBalance()>10000) {
                level = "Gold";
            } else {
                level = "Platinum";
            
            }
            levelLabel.setText("Level: " + level);
            showAlert("Deposit successful.");
        } else {
            showAlert("Please enter a valid deposit amount.");
        }
    } catch (NumberFormatException ex) {
        showAlert("Please enter a valid deposit amount.");
    }
});

// WITHDRAW BUTTON
withdrawButton.setOnAction(ev -> {
    try {
        double withdrawAmount = Double.parseDouble(withdrawField.getText());
        if (withdrawAmount > 0 && customer.getBankAccount().getBalance() >= withdrawAmount) {
            customer.getBankAccount().withdraw(withdrawAmount);
            balanceLabel.setText("Balance: $" + customer.getBankAccount().getBalance());
            showAlert("Withdrawal successful.");
        } else {
            showAlert("Insufficient funds or invalid withdrawal amount.");
        }
    } catch (NumberFormatException ex) {
        showAlert("Please enter a valid withdrawal amount.");
    }
});


                customerDetails.getChildren().addAll(balanceLabel, depositLabel, depositField, depositButton, withdrawLabel, withdrawField, withdrawButton, onlinePurchaseButton);

                stackPane.getChildren().clear(); // Clear existing content
                stackPane.getChildren().add(customerDetails);
                       }else{
                   showAlert("AUTHENTICATION FAILED.");
                       }
                   } catch (FileNotFoundException ex) {
                       showAlert("ERROR: "+ex.getMessage());
                }
               
                        });





            //MANAGER BACK BUTTON LOGIN SCREEN
            backButton.setOnAction(e -> {
                stackPane.getChildren().removeAll(loginLayout); 
                stackPane.getChildren().add(mainLayout);
            });

            //CUSTOMER BACK BUTTON LOGIN SCREEN
            backButton1.setOnAction(e -> {
                stackPane.getChildren().removeAll(loginLayout1); 
                stackPane.getChildren().add(mainLayout); 
            });

        Scene scene = new Scene(stackPane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
        
//MANAGER FILE HANDLING (Screen that shows up when "View Customers" is pressed
viewCustomersButton.setOnAction(e -> {
    stackPane.getChildren().removeAll(managerScreen);
    
    ListView<String> customerListView = new ListView<>();
    customerListView.setPrefWidth(200);
    
    File direc = new File(".");
    File[] files = direc.listFiles((dir,name) ->name.endsWith(".txt"));
    customerListView.getItems().clear();
    if(files!=null){
        for(File file:files){
            String username=file.getName().replace(".txt","");
            customerListView.getItems().add(username);
        }
    }
        
    Button addCustomersButton = new Button("Add Customers");
    Button backButton3 = new Button("Back");
    Button deleteCustomersButton = new Button("Delete Customers");
    
    VBox viewCustomersLayout = new VBox(20);
    viewCustomersLayout.setPadding(new Insets(30));
    viewCustomersLayout.getChildren().addAll(customerListView,deleteCustomersButton,addCustomersButton, backButton3);
    
   stackPane.getChildren().add(viewCustomersLayout);
    addCustomersButton.setOnAction(es -> {
        stackPane.getChildren().remove(viewCustomersLayout);

        Label makeACustomer = new Label("Insert Customer Information");
        TextField usernameField2 = new TextField();
        usernameField2.setPromptText("Username");
        PasswordField passwordField2 = new PasswordField();
        passwordField2.setPromptText("Password");
        Button createAccount = new Button("Create Account");
        Button backButton2 = new Button("Back");

        GridPane customerMakerLayout = new GridPane();
        customerMakerLayout.setPadding(new Insets(20));
        customerMakerLayout.setVgap(10);
        customerMakerLayout.setHgap(10);
        customerMakerLayout.addRow(0, makeACustomer);
        customerMakerLayout.addRow(1, new Label("Username:"), usernameField2);
        customerMakerLayout.addRow(2, new Label("Password:"), passwordField2);
        customerMakerLayout.add(createAccount, 1, 3);
        customerMakerLayout.add(backButton2, 0, 3);
        
        createAccount.setOnAction(event -> {
            String username = usernameField2.getText();
            String password = passwordField2.getText();
            double balance = 100.0;
            String level = "Silver";
                  try {
                FileWriter writer = new FileWriter(username + ".txt");
                writer.write("Username: " + username + "\n");
                writer.write("Password: " + password + "\n");
                writer.write("Balance: " + balance + "\n");
                writer.close();
                System.out.println("File created: "+ username + ".txt");
                
                customerListView.getItems().add(username);
                showAlert("Customer account created successfully.");
                showAlert("$100 Added to account.");
            } catch (IOException ex) {
                showAlert("Error creating customer account.");
            }
        });    
        


        backButton2.setOnAction(backEvent ->{
        stackPane.getChildren().removeAll(customerMakerLayout);
        stackPane.getChildren().add(viewCustomersLayout);
        });
        stackPane.getChildren().add(customerMakerLayout);
        });
    
    
    deleteCustomersButton.setOnAction(es->{
        String selectCustomer = customerListView.getSelectionModel().getSelectedItem();
        
        if(selectCustomer != null){            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Customer");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this file?");
       
                
            Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            File fileToDelete = new File(selectCustomer + ".txt");
            if (fileToDelete.delete()) {
                customerListView.getItems().remove(selectCustomer);
                showAlert("Customer " + selectCustomer + " deleted successfully.");
            } else {
                showAlert("Error deleting customer " + selectCustomer + ".");
            }
        }
    } else {
        showAlert("Please select a customer to delete.");
    }
        
    });
        backButton3.setOnAction(backEvent -> {
        stackPane.getChildren().removeAll(viewCustomersLayout);
        stackPane.getChildren().add(managerScreen);   
});
       
        

});
      }
      //POPS UP WHENEVER THERE IS AN ERROR (used mainly in this program for if the correct username and password is not put for Manager (i.e. "admin" and "admin"))
      private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("HOLD IT RIGHT THERE");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public static void main(String[] args) {
        launch(args);
    }
    

    double getBalance() {
        return balance;
    }

    void deposit(double amount) {
        balance += amount;
        setBalance(balance);
    }
    
//Withdrawing money, ensures that there is more of a balance than the amount being taken
    void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            setBalance(balance);
        } else {
            System.out.println("NOT ENOUGH FUNDS");
        }
    }
void setBalance(double balance) {
    this.balance = balance;
    String level;
    if (balance <= 10000) {
        level = "Silver";
    } else if (balance>10000 && balance < 20000) {
        level = "Gold";
    } else {
        level = "Platinum";
    }
    levelLabel.setText("Level: " + level);
}

}
    