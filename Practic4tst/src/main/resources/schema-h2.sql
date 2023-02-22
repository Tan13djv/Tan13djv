CREATE TABLE ACTIVE_CHAT (
                             ID SERIAL PRIMARY KEY NOT NULL,
                             CHAT_ID INTEGER NOT NULL
);

CREATE TABLE INCOMES (
                         ID SERIAL PRIMARY KEY NOT NULL,
                         CHAT_ID INTEGER NOT NULL,
                         INCOME INTEGER
);

CREATE TABLE SPEND (
                       ID SERIAL PRIMARY KEY NOT NULL,
                       CHAT_ID INTEGER NOT NULL,
                       SPEND INTEGER
)