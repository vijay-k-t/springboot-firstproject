--liquibase formatted sql
--changeset Vijay:V1.3
INSERT INTO TEST_1 VALUES (21, 'My Name 1');
INSERT INTO TEST_1 VALUES (22, 'My Name 2');
INSERT INTO TEST_1 VALUES (23, 'My Name 3');
INSERT INTO TEST_1 VALUES (24, 'My Name 4');
INSERT INTO TEST_1 VALUES (25, 'My Name 5');
INSERT INTO TEST_1 VALUES (26, 'My Name 6');
INSERT INTO TEST_1 VALUES (27, 'My Name 7');
INSERT INTO TEST_1 VALUES (28, 'My Name 8');
INSERT INTO TEST_1 VALUES (29, 'My Name 9');

--rollback DELETE FROM TEST_1 WHERE ID IN (11,12,13,14,15,16,17,18,19)