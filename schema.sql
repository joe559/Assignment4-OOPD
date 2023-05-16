CREATE TABLE Address (
    address_id SERIAL PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    zip_code INTEGER NOT NULL
);
CREATE TABLE Customer (
   customer_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (address_id) REFERENCES address (id)
);

CREATE TABLE Order (
    number SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    item VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);