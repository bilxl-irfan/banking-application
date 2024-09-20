package coe528.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Overview: Customers are mutable objects that represent a bank customer with a username, password, bank account, and level. The customer's bank account balance can change through deposit, withdrawal, and online purchase operations, and the customer's level can change based on the balance.
 * The abstraction function is:
 * AF(c) = a customer with username c.username, password c.password, bank account balance c.bankAccount.getBalance(), and level c.level.getLevel().
 * The rep invariant is:
 * RI(c) = c.username != null and c.password != null and c.bankAccount != null and c.level != null
 */

public class Customer extends User {
    
    private BankAccount bankAccount;
    private Level level;
    private static final String CUSTOMER_FILES = "customerfiles";
    
    
    public Customer (String username, String password, BankAccount bankAccount, Level level) {
        
        /**
         * Requires: username and password are non-null strings, bankAccount and level are non-null objects. 
         * Modifies: this 
         * Effects: Makes a new Customer with the specified username, password, bankAccount, and level.
         */
        
        super(username, password);
        this.bankAccount = bankAccount;
        this.level = level;
    }
    
    
    public void depositMoney(double amount) {
        
        /**
         * Requires: amount > 0 
         * Modifies: this.bankAccount, this.level 
         * Effects: Deposits the specified amount into the customer's bank account, updates the customer's level, and updates the customer's .txt file.
         */
        
        this.bankAccount.deposit(amount);
        this.level = Level.updateLevel(this.bankAccount.getBalance());
        updateFile();
    }
    
    
    public void withdrawMoney(double amount) throws IOException {
        
        /**
         * Requires: amount >= 0 and amount <= this.bankAccount.balance
         * Modifies: this.bankAccount, this.level 
         * Effects: Withdraws the specified amount from the customer's bank account, updates the customer's level, and updates the customer's file. 
         *          Throws IOException if the customer's balance is less than the amount.
         */
        
        if (this.bankAccount.getBalance() >= amount) {
            this.bankAccount.withdraw(amount);
                    this.level = Level.updateLevel(this.bankAccount.getBalance());
        } else {
            throw new IOException("Insufficient balance");
        }
        updateFile();
    }
    
    
    public double getBalance() {
        
        /**
         * Requires: Nothing.
         * Modifies: Nothing. 
         * Effects: Returns the balance of the customer's bank account.
         */
        
        return this.bankAccount.getBalance();
    }
    
    
    public void onlinePurchase(double amount) throws IOException {
        
        /**
        * Requires: amount >= 50 and amount + this.level.getFee() <= this.bankAccount.balance
        * Modifies: this.bankAccount, this.level
        * Effects: Makes an online purchase of the specified amount plus the fee based on the level, updates the customer's level, and updates the customer's file.
        *          Throws IllegalArgumentException if the amount is less than 50.
        *          Throws IOException if the customer's balance is less than the total purchase amount.
        */
        
        if (amount < 50) {
            throw new IllegalArgumentException("The minimum online purchase amount is 50 dollars.");
        }
        double totalAmount = amount + this.level.getFee();
        if (this.bankAccount.getBalance() >= totalAmount) {
            this.bankAccount.withdraw(totalAmount);
            this.level = Level.updateLevel(this.bankAccount.getBalance());
        } else {
            throw new IOException("Insufficient balance for purchase");
        }
        updateFile();
    }
    
    private void updateFile() {
        
        /**
        * Requires: Nothing.
        * Modifies: this
        * Effects: Updates the customer's file with the current username, password, role, balance, and level.
        */
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("customerfiles/" + username + ".txt"))) {
            writer.write("Username: " + username);
            writer.newLine();
            writer.write("Password: " + password);
            writer.newLine();
            writer.write("Role: customer");
            writer.newLine();
            String balanceString = String.format("%.2f", getBalance());
            writer.write("Balance: " + balanceString);
            writer.newLine();
            writer.write("Level: " + this.level.getLevel());
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
    }
    
    public static boolean authenticate(String username, String password) throws IOException {
        
        /**
        * Requires: username and password are non-null strings.
        * Modifies: Nothing.
        * Effects: Returns true if the specified username and password match the customer's username and password, and false otherwise.
        *          Throws IOException if the username doesn't exist.
        */
        
        File customerFile = new File(CUSTOMER_FILES + File.separator + username + ".txt");
        if (!customerFile.exists()) {
            throw new IOException("Username doesn't exist.");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_FILES + File.separator + username + ".txt"))) {
            reader.readLine(); // Skip username line
            String correctPassword = reader.readLine().split(": ")[1]; // Get password from second line
            return password.equals(correctPassword);
        }
    }
    
    @Override
    public String toString() {
        return "Customer{username='" + username + '\'' + ", password='" + password + '\'' + ", bankAccountBalance=" + bankAccount.getBalance() + ", level=" + level.getLevel() + '}';
    }
    
    public boolean repOk() {
        return username != null && password != null && bankAccount != null && level != null;
    }
    
    }
