CREATE TABLE users
(
    id    SERIAL PRIMARY KEY,
    email  VARCHAR(255),
    password_hash VARCHAR(255),
    salt  VARCHAR(255)
);