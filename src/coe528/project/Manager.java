package coe528.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Manager extends User {

    private static final String CUSTOMER_FILES = "customerfiles";
    private static final String MANAGER_FILES = "managerfiles";

    public Manager(String username, String password) {
        super(username, password);
    }

    public void addCustomer(String username, String password) throws IOException {
        File directory = new File(CUSTOMER_FILES);
        if (!directory.exists()) {
            directory.mkdir();
        }

        // It is assumed that no two customers have the same username
        
        File customerFile = new File(CUSTOMER_FILES + File.separator + username + ".txt");
        if (customerFile.createNewFile()) {
            FileWriter writer = new FileWriter(customerFile);
            writer.write("Username: " + username + "\n");
            writer.write("Password: " + password + "\n");
            writer.write("Role: customer\n");
            writer.write("Balance: 100.00\n");
            writer.write("Level: Silver\n");
            writer.close();
        } else {
            throw new IOException("Customer already exists.");
        }
    }
    
    public void addManager(String username, String password) throws IOException {
        File directory = new File(MANAGER_FILES);
        if (!directory.exists()) {
            directory.mkdir();
        }

        // It is assumed that no two managers have the same username
        File managerFile = new File(MANAGER_FILES + File.separator + username + ".txt");
        if (managerFile.createNewFile()) {
            FileWriter writer = new FileWriter(managerFile);
            writer.write("Username: " + username + "\n");
            writer.write("Password: " + password + "\n");
            writer.write("Role: manager\n");
            writer.close();
        } else {
            throw new IOException("Manager already exists.");
        }
    }

    public void deleteCustomer(String username) throws IOException {
        File customerFile = new File(CUSTOMER_FILES + File.separator + username + ".txt");
        if (customerFile.exists()) {
            customerFile.delete();
        } else {
            throw new IOException("Customer not found.");
        }
    }
    
    public boolean login(String username, String password) throws IOException {
    File managerFile = new File(MANAGER_FILES + File.separator + username + ".txt");
    if (!managerFile.exists()) {
        throw new IOException("Username doesn't exist");
    }
    List<String> lines = Files.readAllLines(Paths.get(managerFile.getPath()));
    for (String line : lines) {
        if (line.startsWith("Password: ")) {
            return line.substring(10).equals(password);
        }
    }
    return false;
}
    
    /* public static void main(String[] args) {
    Manager manager = new Manager("admin", "password");

    try {
        manager.addCustomer("customer1", "password1");
        manager.addManager("admin", "admin");

        // Check the customerfiles directory to see if the files were created

        manager.deleteCustomer("customer1");

        // Check the customerfiles directory to see if customer1's file was deleted
    } catch (IOException e) {
        e.printStackTrace();
    }
}*/
    
}
