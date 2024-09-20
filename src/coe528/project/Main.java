/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 *
 * @author Bilal Irfan | 501176502
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import java.io.IOException;
import javafx.geometry.HPos;
import javafx.scene.text.Text;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {

    private Stage primaryStage;
    private Customer customer;
    private static final String CUSTOMER_FILES = "customerfiles";


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showWelcomeScreen();
    }

    private void showWelcomeScreen() {
        Label greeting = new Label("Welcome to the Bank");
        greeting.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        Button continueButton = new Button("Continue");
        continueButton.setOnAction(e -> showUserSelectionScreen());

        VBox layout = new VBox(10, greeting, continueButton);
        layout.setAlignment(Pos.CENTER);

        StackPane root = new StackPane(layout);
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    private void showUserSelectionScreen() {
        Label prompt = new Label("Select user");
        prompt.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        Button managerButton = new Button("Manager");
        managerButton.setOnAction(e -> showLoginScreen("Manager"));
        Button customerButton = new Button("Customer");
        customerButton.setOnAction(e -> showCustomerLoginScreen("Customer"));

        VBox layout = new VBox(10, prompt, managerButton, customerButton);
        layout.setAlignment(Pos.CENTER);
        
        StackPane root = new StackPane(layout);
        primaryStage.setScene(new Scene(root, 400, 300));
    }

    private void showLoginScreen(String userType) {
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showUserSelectionScreen());
        Label title = new Label("Login as " + userType);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        Label usernameLabel = new Label("Username");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.add(backButton, 0, 0);
        layout.add(title, 1, 0, 2, 1);
        layout.add(usernameLabel, 1, 1);
        layout.add(usernameField, 1, 2);
        layout.add(passwordLabel, 1, 3);
        layout.add(passwordField, 1, 4);

        StackPane root = new StackPane(layout);
        primaryStage.setScene(new Scene(root, 400, 300));
        
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);

    Button signInButton = new Button("Sign In");
    signInButton.setOnAction(e -> {
        try {
            Manager manager = new Manager(usernameField.getText(), passwordField.getText());
            if (manager.login(usernameField.getText(), passwordField.getText())) {
                showManagerScreen();
            } else {
                errorLabel.setText("Incorrect password entered");
                passwordField.clear();
            }
        } catch (IOException ex) {
            errorLabel.setText("Username entered doesn't exist");
            usernameField.clear();
            passwordField.clear();
        }
    });

    layout.add(errorLabel, 1, 5);
    layout.add(signInButton, 1, 6);

    }
    
    private void showManagerScreen() {
        Text title = new Text("Welcome Back!");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        Label prompt = new Label("What would you like to do?");
        prompt.setFont(Font.font("Arial", FontWeight.NORMAL, 20));

        Button addCustomerButton = new Button("Add Customer");
        Button deleteCustomerButton = new Button("Delete Customer");
        HBox buttonBox = new HBox(10, addCustomerButton, deleteCustomerButton);
        buttonBox.setAlignment(Pos.CENTER);

        Button signOutButton = new Button("Sign Out");
        signOutButton.setOnAction(e -> showUserSelectionScreen());

        HBox titleBox = new HBox(20, title, new Region(), signOutButton); // Added spacing
        titleBox.setHgrow(titleBox.getChildren().get(1), Priority.ALWAYS);
        titleBox.setPadding(new Insets(20, 20, 20, 20));

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(20);
        layout.setVgap(20);
        layout.add(titleBox, 0, 0, 2, 1);
        layout.add(prompt, 0, 1, 2, 1);
        layout.add(buttonBox, 0, 2, 2, 1);
        GridPane.setHalignment(prompt, HPos.CENTER);
        GridPane.setHalignment(buttonBox, HPos.CENTER);

        addCustomerButton.setOnAction(e -> showAddCustomerScreen());
        deleteCustomerButton.setOnAction(e -> showDeleteCustomerScreen());

        StackPane root = new StackPane(layout);
        primaryStage.setScene(new Scene(root, 400, 300));
}
    
    private void showAddCustomerScreen() {
        Text title = new Text("Enter customer information below");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        Label usernameLabel = new Label("Username");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);

        Button addButton = new Button("Add Customer");
        addButton.setOnAction(e -> {
            try {
                Manager manager = new Manager("admin", "password");
                manager.addCustomer(usernameField.getText(), passwordField.getText());
                showManagerScreen();
            } catch (IOException ex) {
                errorLabel.setText("Error adding customer");
            }
        });

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.add(title, 0, 0, 2, 1);
        layout.add(usernameLabel, 0, 1);
        layout.add(usernameField, 1, 1);
        layout.add(passwordLabel, 0, 2);
        layout.add(passwordField, 1, 2);
        layout.add(errorLabel, 0, 3, 2, 1);
        layout.add(addButton, 0, 4, 2, 1);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setHalignment(errorLabel, HPos.CENTER);
        GridPane.setHalignment(addButton, HPos.CENTER);

        StackPane root = new StackPane(layout);
        primaryStage.setScene(new Scene(root, 400, 300));
        
        Label successLabel = new Label();
    successLabel.setTextFill(Color.GREEN);

    addButton.setOnAction(e -> {
        try {
            Manager manager = new Manager("admin", "password");
            manager.addCustomer(usernameField.getText(), passwordField.getText());
            usernameField.clear();
            passwordField.clear();
            errorLabel.setText("");
            successLabel.setText("Customer added successfully");
        } catch (IOException ex) {
            errorLabel.setText("Error adding customer");
            successLabel.setText("");
        }
    });

    Button backButton = new Button("Back");
    backButton.setOnAction(e -> showManagerScreen());

    layout.add(successLabel, 0, 5, 2, 1);
    layout.add(backButton, 0, 6, 2, 1);
    GridPane.setHalignment(successLabel, HPos.CENTER);
    GridPane.setHalignment(backButton, HPos.CENTER);
    
    layout.add(successLabel, 0, 5, 2, 1);
    GridPane.setHalignment(successLabel, HPos.CENTER);
    }

    private void showDeleteCustomerScreen() {
        Text title = new Text("Enter customer information below");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        Label usernameLabel = new Label("Username");
        TextField usernameField = new TextField();
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);

        Button deleteButton = new Button("Delete Customer");
        /*deleteButton.setOnAction(e -> {
            Manager manager = new Manager("admin", "password");
            manager.deleteCustomer(usernameField.getText());
            showManagerScreen();
        });*/

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.add(title, 0, 0, 2, 1);
        layout.add(usernameLabel, 0, 1);
        layout.add(usernameField, 1, 1);
        layout.add(errorLabel, 0, 2, 2, 1);
        layout.add(deleteButton, 0, 3, 2, 1);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setHalignment(errorLabel, HPos.CENTER);
        GridPane.setHalignment(deleteButton, HPos.CENTER);

        StackPane root = new StackPane(layout);
        primaryStage.setScene(new Scene(root, 400, 300));
        
        Label successLabel = new Label();
    successLabel.setTextFill(Color.GREEN);

    deleteButton.setOnAction(e -> {
        Manager manager = new Manager("admin", "password");
       try {
        manager.deleteCustomer(usernameField.getText());
        usernameField.clear();
        successLabel.setText("Customer deleted successfully");
        errorLabel.setText(""); // Clear the error message
        }
       catch (IOException ex) {
        errorLabel.setText(ex.getMessage());
        usernameField.clear();
        successLabel.setText("");
    }
    });
    
    Button backButton = new Button("Back");
    backButton.setOnAction(e -> showManagerScreen());

    layout.add(successLabel, 0, 5, 2, 1);
    layout.add(backButton, 0, 6, 2, 1);
    GridPane.setHalignment(successLabel, HPos.CENTER);
    GridPane.setHalignment(backButton, HPos.CENTER);

    layout.add(successLabel, 0, 4, 2, 1);
    GridPane.setHalignment(successLabel, HPos.CENTER);
    }
    
    private void showCustomerLoginScreen(String userType) {
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showUserSelectionScreen());
        Label title = new Label("Login as " + userType);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        Label usernameLabel = new Label("Username");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.add(backButton, 0, 0);
        layout.add(title, 1, 0, 2, 1);
        layout.add(usernameLabel, 1, 1);
        layout.add(usernameField, 1, 2);
        layout.add(passwordLabel, 1, 3);
        layout.add(passwordField, 1, 4);
        
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        GridPane.setHalignment(errorLabel, HPos.CENTER);

        Button loginButton = new Button("Sign In");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            try {
                if (!Customer.authenticate(username, password)) {
                    errorLabel.setText("Incorrect password entered");
                    passwordField.clear();
                } else {
                    try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_FILES + File.separator + username + ".txt"))) {
                    reader.readLine(); // Skip username line
                    reader.readLine(); // Skip password line
                    reader.readLine(); // Skip role line
                    double balance = Double.parseDouble(reader.readLine().split(": ")[1]); // Get balance from fourth line

                    // Create a new BankAccount and Level object for the customer
                    BankAccount bankAccount = new BankAccount(balance);
                    Level customerLevel = Level.updateLevel(balance);

                    // Create a new Customer object and store it in the customer field
                    customer = new Customer(username, password, bankAccount, customerLevel);

                    // Show the customer screen
                    showCustomerScreen();
                    }
                }
            } catch (IOException ex) {
                if (ex.getMessage().equals("Username doesn't exist.")) {
                    errorLabel.setText(ex.getMessage());
                    usernameField.clear();
                    passwordField.clear();
                } else {
                    errorLabel.setText("Error reading file");
                }
            }
        });
        
        layout.add(loginButton, 1, 6);
        layout.add(errorLabel, 1, 5);


        StackPane root = new StackPane(layout);
        primaryStage.setScene(new Scene(root, 400, 300));
}
    
    private void showCustomerScreen() {
        
        Text title = new Text("Welcome Back!");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        Label prompt = new Label("Enter the amount below and select one of the three options.");
        prompt.setFont(Font.font("Arial", FontWeight.NORMAL, 13));
        Label amountLabel = new Label("Amount");
        TextField amountField = new TextField();
        Button depositButton = new Button("Deposit Amount");
        Button withdrawButton = new Button("Withdraw Amount");
        Button purchaseButton = new Button("Purchase Online");
        Button balanceButton = new Button("View Balance");
        HBox buttonBox = new HBox(10, depositButton, withdrawButton, purchaseButton);
        buttonBox.setAlignment(Pos.CENTER);
        
        Button signOutButton = new Button("Sign Out");
        signOutButton.setOnAction(e -> showUserSelectionScreen());

        HBox titleBox = new HBox(20, title, new Region(), signOutButton);
        titleBox.setHgrow(titleBox.getChildren().get(1), Priority.ALWAYS);
        titleBox.setPadding(new Insets(20, 20, 20, 20));
        
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        Label successLabel = new Label();
        successLabel.setTextFill(Color.GREEN);
        
        balanceButton.setOnAction(e -> {
            String balanceString = String.format("%.2f", customer.getBalance());
            successLabel.setText("Current Balance: " + balanceString);
            errorLabel.setText("");
        });

        depositButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                customer.depositMoney(amount);
                amountField.clear();
                successLabel.setText("Amount deposited successfully.");
                errorLabel.setText(""); // Clear error message
            } catch (NumberFormatException ex) {
                errorLabel.setText("Please enter a valid amount");
                successLabel.setText("");
                amountField.clear();
            }
        });

        withdrawButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                customer.withdrawMoney(amount);
                amountField.clear();
                successLabel.setText("Amount withdrawn successfully.");
                errorLabel.setText(""); // Clear error message
            } catch (NumberFormatException ex) {
                errorLabel.setText("Please enter a valid amount");
                successLabel.setText("");
                amountField.clear();
            } catch (IOException ex) {
                errorLabel.setText(ex.getMessage());
                successLabel.setText("");
                amountField.clear();
            }
        });

        purchaseButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                customer.onlinePurchase(amount);
                amountField.clear();
                successLabel.setText("Online purchase complete.");
                errorLabel.setText(""); // Clear error message
            } catch (NumberFormatException ex) {
                errorLabel.setText("Please enter a valid amount");
                successLabel.setText("");
                amountField.clear();
            } catch (IOException ex) {
                errorLabel.setText(ex.getMessage());
                successLabel.setText("");
                amountField.clear();
            } catch (IllegalArgumentException ex) {
                errorLabel.setText(ex.getMessage());
                successLabel.setText("");
                amountField.clear();
            }
        });

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(20);
        layout.setVgap(20);
        layout.add(titleBox, 0, 0, 2, 1);
        layout.add(prompt, 0, 1, 2, 1);
        layout.add(amountLabel, 0, 2);
        layout.add(amountField, 1, 2);
        layout.add(errorLabel, 0, 3, 2, 1);
        layout.add(buttonBox, 0, 4, 2, 1);
        layout.add(balanceButton, 0, 6);
        layout.add(successLabel, 1, 6);
        GridPane.setMargin(balanceButton, new Insets(0, 0, 20, 0));
        GridPane.setMargin(successLabel, new Insets(0, 0, 20, 0));
        GridPane.setHalignment(prompt, HPos.CENTER);
        GridPane.setHalignment(errorLabel, HPos.CENTER);
        GridPane.setHalignment(prompt, HPos.CENTER);

        StackPane root = new StackPane(layout);
        primaryStage.setScene(new Scene(root, 400, 300));
}

    public static void main(String[] args) {
        launch(args);
    }
}