CREATE DATABASE IF NOT EXISTS shopdb;
USE shopdb;
DROP TABLE IF EXISTS orders_update;
DROP TABLE IF EXISTS products_update;
CREATE TABLE products_update (
    product_id   INT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    price        DECIMAL(10,2) NOT NULL
);

CREATE TABLE orders_update (
    order_id     INT PRIMARY KEY,
    customer     VARCHAR(100) NOT NULL,
    product_id   INT,
    quantity     INT NOT NULL,
    CONSTRAINT fk_product_update
        FOREIGN KEY (product_id)
        REFERENCES products_update(product_id)
        ON UPDATE CASCADE
);
INSERT INTO products_update VALUES (1, 'Laptop', 75000);
INSERT INTO products_update VALUES (2, 'Smartphone', 35000);

INSERT INTO orders_update VALUES (201, 'Charlie', 1, 2);
INSERT INTO orders_update VALUES (202, 'Diana', 2, 3);

SELECT * FROM products_update;
SELECT * FROM orders_update;

UPDATE products_update  SET product_id = 20  WHERE product_id = 2;
SELECT * FROM products_update;
SELECT * FROM orders_update;
