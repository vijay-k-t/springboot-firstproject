--liquibase formatted sql
--changeset Vijay:TEST_VIEW_V28 runOnChange:true labels:release-8 context:dev,uat 
--comment Test comment
--preconditions onFail:HALT onError:HALT
--precondition-sql-check expectedResult:1 SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='TEST_1'
CREATE OR REPLACE VIEW TEST_VIEW AS SELECT * FROM TEST_1 WHERE ID < 8;