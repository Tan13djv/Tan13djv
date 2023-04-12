
CREATE TABLE BALANCE (
                         ID SERIAL PRIMARY KEY NOT NULL,
                         CLIENT_ID INTEGER NOT NULL,
                         AMOUNT NUMERIC(18,2) NOT NULL);

CREATE TABLE TRANSACTIONS (
                              ID SERIAL PRIMARY KEY NOT NULL,
                              CLIENT_ID INTEGER NOT NULL,
                              DIRECTION CHAR(1) NOT NULL,
                              AMOUNT NUMERIC NOT NULL,
                              DATE_OP TIMESTAMP NOT NULL);