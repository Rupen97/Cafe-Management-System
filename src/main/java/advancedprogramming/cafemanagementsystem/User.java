package advancedprogramming.cafemanagementsystem;

import java.sql.Timestamp;

public class User {
    private int userId;
    private String email;
    private String password;
    private String role;
    private Timestamp createdAt;
    private String name;

    // Constructors
    public User() {}

    public User(int userId, String email, String password, String role, String name) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
    }

    // Getters and Setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}