drop table STUDENT if exists;

CREATE TABLE STUDENT(
	NO CHAR(10) NOT NULL PRIMARY KEY,
	NAME VARCHAR(10) DEFAULT NULL,
	ENT_YEAR INT DEFAULT NULL,
	CLASS_NUM CHAR(5) DEFAULT NULL,
	IS_ATTEND BOOL DEFAULT NULL,
	SCHOOL_CD CHAR(6) DEFAULT NULL
);

INSERT INTO STUDENT (NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD)
VALUES
    (124, 'bbb', 2024, 102, FALSE, 'tes'),
    (125, 'ccc', 2025, 201, TRUE, 'tes'),
    (126, 'ddd', 2025, 202, FALSE, 'tes'),
    (127, '大原太郎', 2025, 202, FALSE, 'tes'),
    (128, 'eeee', 2023, 101, TRUE, 'tes'),
    (129, 'ffff', 2023, 102, TRUE, 'tes'),
    (130, 'gggg', 2024, 201, FALSE, 'tes'),
    (131, 'hhhh', 2024, 202, TRUE, 'tes'),
    (132, 'iiii', 2025, 101, FALSE, 'tes'),
    (133, 'jjjj', 2025, 102, TRUE, 'tes'),
    (134, 'kkkk', 2023, 201, FALSE, 'tes'),
    (135, 'llll', 2023, 202, TRUE, 'tes'),
    (136, 'mmmm', 2024, 101, FALSE, 'tes'),
    (137, 'nnnn', 2024, 102, TRUE, 'tes'),
    (138, 'oooo', 2025, 201, FALSE, 'tes'),
    (139, 'pppp', 2025, 202, TRUE, 'tes'),
    (140, 'qqqq', 2023, 101, FALSE, 'tes'),
    (141, 'rrrr', 2023, 102, TRUE, 'tes'),
    (142, 'ssss', 2024, 201, TRUE, 'tes'),
    (143, 'tttt', 2024, 202, FALSE, 'tes');