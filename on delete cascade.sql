CREATE DATABASE shopdb;
USE shopdb;
CREATE TABLE products_delete (
    product_id   INT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    price        DECIMAL(10,2) NOT NULL
);
CREATE TABLE orders_delete (
    order_id     INT PRIMARY KEY,
    customer     VARCHAR(100) NOT NULL,
    product_id   INT,
    quantity     INT NOT NULL,
        FOREIGN KEY (product_id)
        REFERENCES products_delete(product_id)
        ON DELETE CASCADE
);
INSERT INTO products_delete (product_id, product_name, price) VALUES (1, 'Laptop', 75000);
INSERT INTO products_delete (product_id, product_name, price) VALUES (2, 'Smartphone', 35000);
INSERT INTO orders_delete (order_id, customer, product_id, quantity) VALUES (101, 'Alice', 1, 1);
INSERT INTO orders_delete (order_id, customer, product_id, quantity) VALUES (102, 'Bob', 2, 2);
SELECT * FROM products_delete;
SELECT * FROM orders_delete;
DELETE FROM products_delete WHERE product_id = 1;
SELECT * FROM orders_delete;
