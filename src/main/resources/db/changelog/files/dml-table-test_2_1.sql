--liquibase formatted sql
--changeset Vijay:V1.5
INSERT INTO TEST_2 VALUES (1, 'My Name 1', 'Desc 1');
INSERT INTO TEST_2 VALUES (2, 'My Name 2', 'Desc 2');
INSERT INTO TEST_2 VALUES (3, 'My Name 3', 'Desc 3');
INSERT INTO TEST_2 VALUES (4, 'My Name 4', 'Desc 4');
INSERT INTO TEST_2 VALUES (5, 'My Name 5', 'Desc 5');
INSERT INTO TEST_2 VALUES (6, 'My Name 6', 'Desc 6');
INSERT INTO TEST_2 VALUES (7, 'My Name 7', 'Desc 7');
INSERT INTO TEST_2 VALUES (8, 'My Name 8', 'Desc 8');
INSERT INTO TEST_2 VALUES (9, 'My Name 9', 'Desc 9');

--rollback DELETE FROM TEST_2 WHERE ID IN (1,2,3,4,5,6,7,8,9)

--changeset Vijay:TEST_2_V2
INSERT INTO TEST_2 VALUES (21, 'My Name 21', 'Desc 21');
INSERT INTO TEST_2 VALUES (22, 'My Name 22', 'Desc 22');

