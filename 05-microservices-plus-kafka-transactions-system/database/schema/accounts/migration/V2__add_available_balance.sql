ALTER TABLE accounts
    ADD COLUMN available_balance DECIMAL(10, 2) DEFAULT 0.00 NOT NULL;