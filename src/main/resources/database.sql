CREATE DATABASE cafe_management;
USE cafe_management;

CREATE TABLE users (
                       user_id INT PRIMARY KEY AUTO_INCREMENT,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role ENUM('ADMIN', 'CHEF', 'CUSTOMER') NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       name VARCHAR(100) NOT NULL
);

-- Insert sample data (password is 'admin123' encrypted with BCrypt)
INSERT INTO users (email, password, role, name) VALUES
                                                    ('admin@cafe.com', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewfT/y3tTUFm1/u6', 'ADMIN', 'Admin User'),
                                                    ('chef@cafe.com', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewfT/y3tTUFm1/u6', 'CHEF', 'Chef User'),
                                                    ('customer@cafe.com', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewfT/y3tTUFm1/u6', 'CUSTOMER', 'Customer User');