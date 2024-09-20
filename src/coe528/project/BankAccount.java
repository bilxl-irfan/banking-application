package coe528.project;

/**
 *
 * @author Bilal Irfan | 501176502
 * 
 */
public class BankAccount {
    
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }
    
    public void deposit(double amount) {
        this.balance += amount;
    }
    
    public void withdraw(double amount) {
        this.balance -= amount;
    }
    
    public double getBalance() {
        return this.balance;
    }
    
}
