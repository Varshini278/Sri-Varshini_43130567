
CREATE TABLE departments (
    department_id INT PRIMARY KEY NOT NULL UNIQUE,
    department_name VARCHAR(50) NOT NULL 
);

CREATE TABLE employees (
    employee_id INT PRIMARY KEY NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(20),
    hire_date DATE,
    department_id INT,
    FOREIGN KEY (department_id) REFERENCES departments(department_id)
);
SELECT * FROM departments;
SELECT * FROM employees;
INSERT INTO departments (department_id, department_name) VALUES
(1, 'Sales'),
(2, 'IT'),
(3, 'Finance');
INSERT INTO employees (employee_id, first_name, last_name, email, phone_number, hire_date, department_id) VALUES
(101, 'Akash', 'Varma', 'akash.varma@email.com', '123-456-7890', '2023-01-15', 1),
(102, 'Vijay', 'Aditya', 'vijay.aditya@email.com', '987-654-3210', '2023-02-20', 2),
(103, 'Sai', 'Saran', 'Sai.Saran@email.com', '111-222-3333', '2023-03-10', 1);
UPDATE employees
SET last_name = 'Sanjay', email = 'akash.sanjay@email.com'
WHERE employee_id = 101;
DELETE FROM employees
WHERE employee_id = 103;
ALTER TABLE employees
ADD salary DECIMAL(10, 2);
ALTER TABLE employees
MODIFY COLUMN phone_number VARCHAR(25);
ALTER TABLE employees
RENAME COLUMN phone_number TO employee_phone;



