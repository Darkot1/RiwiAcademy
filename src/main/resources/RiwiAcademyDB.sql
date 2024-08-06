CREATE DATABASE riwiAcademyDB;

USE riwiAcademyDB;

CREATE TABLE student(
id_student INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NOT NULL,
email VARCHAR(100)UNIQUE NOT NULL,
status BOOLEAN DEFAULT TRUE
);

CREATE TABLE course(
id_course INT PRIMARY KEY AUTO_INCREMENT,
name_course VARCHAR(100) UNIQUE NOT NULL,
description TEXT
);

CREATE TABLE registration(
id_registration INT PRIMARY KEY AUTO_INCREMENT,
fk_student_id INT,
fk_course_id INT,
Registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY(fk_student_id) REFERENCES student(id_student) ON DELETE CASCADE,
FOREIGN KEY(fk_course_id) REFERENCES course(id_course) ON DELETE CASCADE,
UNIQUE (fk_student_id, fk_course_id)
);

CREATE TABLE score (
id_score INT PRIMARY KEY AUTO_INCREMENT,
description TEXT,
fk_student_id INT,
fk_course_id INT,
score DECIMAL(3,1) CHECK (score BETWEEN 0 AND 100),
FOREIGN KEY (fk_student_id) REFERENCES student(id_student) ON DELETE CASCADE,
FOREIGN KEY (fk_course_id) REFERENCES course(id_course) ON DELETE CASCADE
);



SELECT * FROM student;

SELECT * FROM course;

SELECT * FROM registration;

SELECT * FROM score;

SELECT * FROM registration WHERE fk_course_id = 1;



