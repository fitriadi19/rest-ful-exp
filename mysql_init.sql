-- Create database
CREATE DATABASE restfulexp
    CHARACTER SET utf8
    COLLATE utf8_unicode_ci;

CREATE TABLE customers (
   id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   name VARCHAR(255),
   last_name VARCHAR(255)
);
