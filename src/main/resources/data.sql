INSERT INTO users (username, password, role)
VALUES ('user', '$2a$10$a6c3I1GYtgYyENgNZ2O1mONQq/8WXUtaNx1pjzKpCSI4m01ymF9Ia', 'USER');

INSERT INTO Countdown_dates (lesson, date, user_id) VALUES ('ネットプロ', '2024-07-17', 1);
INSERT INTO Countdown_dates (lesson, date, user_id) VALUES ('データベース', '2024-07-21', 1);
INSERT INTO Countdown_dates (lesson, date, user_id) VALUES ('メディアデザイン', '2024-07-12', 1);

INSERT INTO Courses (name, instructor) VALUES ('ネットワークプログラミング', '岩井');
INSERT INTO Courses (name, instructor) VALUES ('データベースプログラミング演習', '増田');
INSERT INTO Courses (name, instructor) VALUES ('メディアデザイン', '井ノ上');
