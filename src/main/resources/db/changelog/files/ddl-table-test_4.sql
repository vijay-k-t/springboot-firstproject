--liquibase formatted sql
--changeset Vijay:V1.6
CREATE TABLE IF NOT EXISTS ROLLBACK_CHECK_TBL (
    ID Integer NOT NULL,
    firstName VARCHAR (100),
    lastName VARCHAR (100)
);
--rollback DROP TABLE ROLLBACK_CHECK_TBL


--changeset Vijay:V1.7
CREATE TABLE IF NOT EXISTS NEW_TABEL_1 (
    ID Integer NOT NULL,
    firstName VARCHAR (100),
    lastName VARCHAR (100)
);
--rollback DROP TABLE NEW_TABEL_1


--changeset Vijay:V1.8
CREATE TABLE IF NOT EXISTS NEW_TABEL_2 (
    ID Integer NOT NULL,
    firstName VARCHAR (100),
    lastName VARCHAR (100)
);
--rollback DROP TABLE NEW_TABEL_2

