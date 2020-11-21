DROP DATABASE IF EXISTS SCearch;
CREATE DATABASE SCearch;
USE SCearch;
CREATE TABLE login (
    user VARCHAR(50) PRIMARY KEY,
    pass VARCHAR(50) NOT NULL
);
CREATE TABLE friendsTable (
	user VARCHAR(50) PRIMARY KEY,
    friends VARCHAR(8000),
    FOREIGN KEY (user) REFERENCES login(user)
);
CREATE TABLE profile (
    user VARCHAR(50) PRIMARY KEY NOT NULL,
    fname VARCHAR(50),
    lname VARCHAR(50),
    major VARCHAR(50),
    email VARCHAR(50),
    classes VARCHAR(8000),
    FOREIGN KEY (user) REFERENCES login(user)
);
CREATE TABLE classes (
	class VARCHAR(50) PRIMARY KEY,
	prevStudents VARCHAR(8000)
);

DROP procedure IF EXISTS findProcedure;
DELIMITER $$
CREATE PROCEDURE findProcedure (IN username varchar(50), IN password varchar(50), OUT exist INT)
BEGIN
SELECT count(*) into exist from login where user = username AND pass = password;
END$$
DELIMITER ;

DROP procedure IF EXISTS findProcedureProfile;
DELIMITER $$
CREATE PROCEDURE findProcedureProfile (IN username varchar(50), OUT firstname varchar(50), OUT lastname varchar(50), OUT majorout varchar(50), OUT emailout varchar(50), OUT classesout varchar(8000), OUT friendsout varchar(8000))
BEGIN
SELECT fname into firstname from profile where user = username;
SELECT lname into lastname from profile where user = username;
SELECT major into majorout from profile where user = username;
SELECT email into emailout from profile where user = username;
SELECT classes into classesout from profile where user = username;
SELECT friends into friendsout from friendsTable where user = username;
END$$
DELIMITER ;