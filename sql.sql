CREATE DATABASE IF NOT EXISTS SistemaLogin;

USE SistemaLogin;

CREATE TABLE IF NOT EXISTS dados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(45) NOT NULL,
    senha VARCHAR(45) NOT NULL,
    email VARCHAR(255) NOT NULL,
    idade INT NOT NULL
);