--liquibase formatted sql
--changeset Vijay:V1.4
CREATE TABLE IF NOT EXISTS TEST_2 (
    ID Integer NOT NULL,
    NAME VARCHAR (355),
    DESC VARCHAR (100)
);
--rollback DROP TABLE test_2
