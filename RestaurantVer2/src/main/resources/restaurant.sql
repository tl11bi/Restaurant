CREATE TABLE REST_MENU_ITEM(
                               MENU_ITEM_ID NUMBER(6) NOT NULL,
                               MENU_ITEM_NAME VARCHAR2(50),
                               MENU_ITEM_PRICE NUMBER(6),
                               CONSTRAINT MENU_ITEM_ID_PK PRIMARY KEY (MENU_ITEM_ID)
);


CREATE TABLE REST_TABLE(
                           TABLE_ID NUMBER(6) NOT NULL,
                           TABLE_NAME VARCHAR2(20),
                           TABLE_CAPACITY NUMBER(6),
                           TABLE_OCCUPIED NUMBER(1),
                           CONSTRAINT TABLE_ID_PK PRIMARY KEY (TABLE_ID)
);


CREATE TABLE REST_RESERVATION(
                                 RESERVATION_ID NUMBER(6) NOT NULL,
                                 TABLE_ID NUMBER(6) NOT NULL,
                                 RESERVATION_FIRST_NAME VARCHAR2(20),
                                 RESERVATION_LAST_NAME VARCHAR2(20),
                                 RESERVATION_NUMBER_OF_PEOPLE NUMBER(6),
                                 RESERVATION_TIME DATE,
                                 CONSTRAINT RESERVATION_ID_PK
                                     PRIMARY KEY (RESERVATION_ID),
                                 CONSTRAINT RESERVATION_TABLE_ID_FK
                                     FOREIGN KEY (TABLE_ID)
                                         REFERENCES REST_TABLE (TABLE_ID)
);


CREATE TABLE REST_EMPLOYEE (
                               EMP_ID NUMBER(6) NOT NULL,
                               FIRST_NAME VARCHAR2(20),
                               LAST_NAME VARCHAR2(20),
                               EMP_TYPE VARCHAR2(20),
                               CONSTRAINT EMP_ID_PK
                                   PRIMARY KEY (EMP_ID)
);

CREATE TABLE REST_ORDER(
                           ORDER_ID NUMBER(6) NOT NULL,
                           TABLE_ID NUMBER(6) NOT NULL,
                           EMP_ID NUMBER(6) NOT NULL,
                           CONSTRAINT ORDER_ID_PK
                               PRIMARY KEY (ORDER_ID),
                           CONSTRAINT ORDER_TABLE_ID_FK
                               FOREIGN KEY (TABLE_ID)
                                   REFERENCES REST_TABLE (TABLE_ID),
                           CONSTRAINT ORDER_EMP_ID_FK
                               FOREIGN KEY (EMP_ID)
                                   REFERENCES REST_EMPLOYEE (EMP_ID)
);

CREATE TABLE REST_ORDER_MENU_ITEM(
                                     ORDER_ID NUMBER(6) NOT NULL,
                                     MENU_ITEM_ID NUMBER(6) NOT NULL,
                                     CONSTRAINT ORDER_MENU_ITEM_ID_FK
                                         FOREIGN KEY (MENU_ITEM_ID)
                                             REFERENCES REST_MENU_ITEM (MENU_ITEM_ID),
                                     CONSTRAINT ORDER_ID_FK
                                         FOREIGN KEY (ORDER_ID)
                                             REFERENCES REST_ORDER (ORDER_ID)

);

CREATE SEQUENCE emp_seq
    MINVALUE 1
    START WITH 100
    INCREMENT BY 1;


CREATE SEQUENCE menu_seq
    MINVALUE 1
    START WITH 200
    INCREMENT BY 1;

CREATE SEQUENCE order_seq
    MINVALUE 1
    START WITH 1000
    INCREMENT BY 1;

CREATE SEQUENCE reservation_seq
    MINVALUE 1
    START WITH 2000
    INCREMENT BY 1;

CREATE SEQUENCE rest_table_seq
    MINVALUE 1
    START WITH 300
    INCREMENT BY 1;
