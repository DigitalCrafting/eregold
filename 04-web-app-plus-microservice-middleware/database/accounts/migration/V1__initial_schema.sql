CREATE TABLE accounts
(
    /* TODO customer_id     VARCHAR(10)                 NOT NULL,*/
    account_number  VARCHAR(40)                 NOT NULL,
    account_name    VARCHAR(40)                 NOT NULL,
    current_balance DECIMAL(10, 2) DEFAULT 0.00 NOT NULL,
    currency        VARCHAR(3)                  NOT NULL,
    type            VARCHAR(20)                 NOT NULL
);

ALTER TABLE accounts
    ADD CONSTRAINT accounts_pk PRIMARY KEY (account_number);
