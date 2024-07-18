DROP TABLE IF EXISTS countdown_dates;
DROP TABLE IF EXISTS todo_lists;
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS courses;

/* CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(100) NOT NULL
); */

CREATE TABLE courses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    instructor VARCHAR(50)
);

CREATE TABLE reviews (
    id INT PRIMARY KEY AUTO_INCREMENT,
    course_id INT NOT NULL,
    rating INT NOT NULL,
    review TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (course_id) REFERENCES courses(id)
);

CREATE TABLE countdown_dates (
    id INT PRIMARY KEY AUTO_INCREMENT,
    lesson VARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    userid INT NOT NULL,
    FOREIGN KEY (userid) REFERENCES users(id)
);

CREATE TABLE todo_lists (
    id INT PRIMARY KEY AUTO_INCREMENT,
    todo VARCHAR(100) NOT NULL,
    userid INT NOT NULL,
    FOREIGN KEY (userid) REFERENCES users(id)
);

