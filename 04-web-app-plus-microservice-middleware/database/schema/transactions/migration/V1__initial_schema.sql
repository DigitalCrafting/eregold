CREATE TABLE transactions
(
    id          NUMERIC(64)    NOT NULL,
    account_number VARCHAR(40)    NOT NULL,
    foreign_account_number VARCHAR(40),
    amount      DECIMAL(10, 2) NOT NULL,
    currency    VARCHAR(3)     NOT NULL,
    date        timestamp      NOT NULL,
    description VARCHAR(100)   NOT NULL,
    type        VARCHAR(20)    NOT NULL
);

ALTER TABLE transactions
    ADD CONSTRAINT transactions_pk PRIMARY KEY (id, account_number);

CREATE SEQUENCE transaction_id_seq START 1;
