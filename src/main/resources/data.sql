INSERT INTO Users (username, password, role)
VALUES ('user', '$2a$10$a6c3I1GYtgYyENgNZ2O1mONQq/8WXUtaNx1pjzKpCSI4m01ymF9Ia', 'USER');
INSERT INTO Users (username, password, role)
VALUES ('kouki', '$2a$10$4LY0z/URvnhc.9l4MnkS3./rO1K8Dh/QL7XcJ9MX0d4quXdYJKpCa', 'USER');
INSERT INTO Users (username, password, role)
VALUES ('yuki', '$2a$10$P93DouLOAJEAi9yb/iMnrOIZZ5v2NN/mz6Y5nU.skag5LF/LK41kO', 'USER');

INSERT INTO Countdown_dates (lesson, date,userid) VALUES ('ネットワークプログラミング', '2024-07-27',2);
INSERT INTO Countdown_dates (lesson, date,userid) VALUES ('情報技術基礎および演習', '2024-07-25',2);
INSERT INTO Countdown_dates (lesson, date,userid) VALUES ('情報セキュリティの基礎と暗号技術', '2024-08-11',3);
INSERT INTO Countdown_dates (lesson, date,userid) VALUES ('データベースプログラミング演習', '2024-08-15',3);

INSERT INTO Todo_lists(todo,userid) VALUES ('基本情報技術者試験',2);
INSERT INTO Todo_lists(todo,userid) VALUES ('情報セキュリティマネジメント試験',3);
INSERT INTO Todo_lists(todo,userid) VALUES ('応用情報技術者試験',3);
INSERT INTO Todo_lists(todo,userid) VALUES ('外務員一種試験',2);
INSERT INTO Todo_lists(todo,userid) VALUES ('Java Silver',2);

INSERT INTO Courses(name,instructor) VALUES ('ネットワークプログラミングとクラウド開発','岩井、高橋');
INSERT INTO Reviews(course_id, rating, review) VALUES (1, 5, 'とてもわかりやすかった。');
INSERT INTO Courses(name,instructor) VALUES ('情報セキュリティの基礎と暗号技術','寺田、千葉、廣瀬');
INSERT INTO Reviews(course_id, rating, review) VALUES (2, 5, 'とてもわかりやすかった。');
INSERT INTO Courses(name,instructor) VALUES ('情報技術基礎および演習','竜田');
INSERT INTO Reviews(course_id, rating, review) VALUES (3, 5, 'とてもわかりやすかった。');
INSERT INTO Courses(name,instructor) VALUES ('データベースプログラミング演習','増田');
INSERT INTO Reviews(course_id, rating, review) VALUES (4, 5, 'とてもわかりやすかった。');
