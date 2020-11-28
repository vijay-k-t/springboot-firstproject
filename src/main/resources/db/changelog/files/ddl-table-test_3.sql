--liquibase formatted sql
--changeset Vijay:V1.5
CREATE TABLE IF NOT EXISTS PERSON (
    ID Integer NOT NULL,
    firstName VARCHAR (100),
    lastName VARCHAR (100)
);
--rollback DROP TABLE PERSON
