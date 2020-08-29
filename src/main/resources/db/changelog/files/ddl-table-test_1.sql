--liquibase formatted sql
--changeset Vijay:V1.0
CREATE TABLE IF NOT EXISTS TEST_1 (
    ID Integer NOT NULL,
    NAME VARCHAR (355)
);
--rollback DROP TABLE test_1
