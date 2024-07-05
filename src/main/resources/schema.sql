DROP TABLE IF EXISTS Countdown_dates;

CREATE TABLE Countdown_dates (
    id INT PRIMARY KEY AUTO_INCREMENT,
    lesson VARCHAR(100) NOT NULL,
    date DATE NOT NULL
);
