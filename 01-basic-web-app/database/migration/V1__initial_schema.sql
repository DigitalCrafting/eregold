CREATE TABLE customers
(
    customer_id   VARCHAR(10) NOT NULL,
    password_hash CHARACTER(64) NOT NULL,
    first_name    VARCHAR(20) NOT NULL,
    last_name     VARCHAR(20) NOT NULL,
    email         VARCHAR(20) NOT NULL
);

ALTER TABLE customers
    ADD CONSTRAINT customers_pk PRIMARY KEY (customer_id);

CREATE TABLE accounts
(
    account_number  VARCHAR(40) NOT NULL,
    current_balance DECIMAL(10, 2) DEFAULT 0.00 NOT NULL,
    currency VARCHAR(3) NOT NULL,
    type            VARCHAR(20) NOT NULL
);

ALTER TABLE accounts
    ADD CONSTRAINT accounts_pk PRIMARY KEY (account_number);

CREATE TABLE customer_accounts
(
    customer_id    VARCHAR(10) NOT NULL,
    account_number VARCHAR(40) NOT NULL
);

ALTER TABLE customer_accounts
    ADD CONSTRAINT cust_acc_pk PRIMARY KEY (customer_id, account_number);

ALTER TABLE customer_accounts
    ADD CONSTRAINT cust_acc_cust_fk FOREIGN KEY (customer_id) REFERENCES customers (customer_id);

ALTER TABLE customer_accounts
    ADD CONSTRAINT cust_acc_acc_fk FOREIGN KEY (account_number) REFERENCES accounts (account_number);

CREATE TABLE transactions (
  id NUMERIC (64) NOT NULL,
  src_account VARCHAR(40) NOT NULL,
  dst_account VARCHAR(40) NOT NULL,
  amount DECIMAL(10,2) NOT NULL,
  currency VARCHAR(3) NOT NULL,
  date timestamp NOT NULL,
  description VARCHAR(100) NOT NULL
);
