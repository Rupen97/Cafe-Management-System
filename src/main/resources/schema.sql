CREATE DATABASE IF NOT EXISTS cafe_management;
USE cafe_management;

CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'CHEF', 'CUSTOMER') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT email_check CHECK (email REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$')
);

-- Insert sample data (password is 'admin123' for all users)
INSERT INTO users (email, password, role) VALUES
('admin@cafe.com', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewfT/y3tTUFm1/u2', 'ADMIN'),
('chef@cafe.com', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewfT/y3tTUFm1/u2', 'CHEF'),
('customer@cafe.com', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewfT/y3tTUFm1/u2', 'CUSTOMER');