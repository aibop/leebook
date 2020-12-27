create table USER
(
    ID         INT auto_increment,
    NAME       VARCHAR(50),
    ACCOUNT_NO VARCHAR(100),
    TOKEN      VARCHAR(36),
    CREATED_AT DATETIME,
    UPDATED_AT DATETIME,
    BIO        VARCHAR(256),
    constraint USER_PK
        primary key (ID)
);