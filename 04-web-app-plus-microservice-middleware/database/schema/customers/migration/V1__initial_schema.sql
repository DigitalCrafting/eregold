CREATE TABLE customers
(
    customer_id VARCHAR(8)  NOT NULL,
    first_name  VARCHAR(20) NOT NULL,
    last_name   VARCHAR(20) NOT NULL,
    email       VARCHAR(50) NOT NULL
);

ALTER TABLE customers
    ADD CONSTRAINT customers_pk PRIMARY KEY (customer_id);