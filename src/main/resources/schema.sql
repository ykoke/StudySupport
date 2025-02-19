DROP TABLE IF EXISTS Countdown_dates;
DROP TABLE IF EXISTS Todo_lists;
DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS Courses;
DROP TABLE IF EXISTS Users;

CREATE TABLE Users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(100) NOT NULL
);

CREATE TABLE Courses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    instructor VARCHAR(50)
);

CREATE TABLE Reviews (
    id INT PRIMARY KEY AUTO_INCREMENT,
    course_id INT NOT NULL,
    rating INT NOT NULL,
    review TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (course_id) REFERENCES Courses(id)
);

CREATE TABLE Countdown_dates (
    id INT PRIMARY KEY AUTO_INCREMENT,
    lesson VARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    userid INT NOT NULL,
    FOREIGN KEY (userid) REFERENCES Users(id)
);

CREATE TABLE Todo_lists (
    id INT PRIMARY KEY AUTO_INCREMENT,
    todo VARCHAR(100) NOT NULL,
    userid INT NOT NULL,
    FOREIGN KEY (userid) REFERENCES Users(id)
);

