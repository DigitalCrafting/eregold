CREATE TABLE customers
(
    customer_id VARCHAR(8)  NOT NULL,
    first_name  VARCHAR(20) NOT NULL,
    last_name   VARCHAR(20) NOT NULL,
    email       VARCHAR(50) NOT NULL
);

ALTER TABLE customers
    ADD CONSTRAINT customers_pk PRIMARY KEY (customer_id);

CREATE TABLE customer_accounts
(
    customer_id    VARCHAR(10) NOT NULL,
    account_number VARCHAR(40) NOT NULL
);

ALTER TABLE customer_accounts
    ADD CONSTRAINT cust_acc_pk PRIMARY KEY (customer_id, account_number);
