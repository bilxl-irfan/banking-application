package coe528.project;

/**
 *
 * @author Bilal Irfan | 501176502
 * 
 */
abstract class User {
    
    protected String username;
    protected String password;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
