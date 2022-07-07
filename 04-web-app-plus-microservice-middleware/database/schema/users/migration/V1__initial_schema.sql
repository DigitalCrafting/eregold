CREATE TABLE users
(
    user_id       VARCHAR(50)   NOT NULL,
    password_hash CHARACTER(64) NOT NULL
);

ALTER TABLE users
    ADD CONSTRAINT users_pk PRIMARY KEY (user_id);
