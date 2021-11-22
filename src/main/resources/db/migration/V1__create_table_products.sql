CREATE TABLE products
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255),
    price REAL,
    date  date
);