CREATE DATABASE company_db;
USE company_db;
CREATE TABLE departments (
    dept_id      INT          PRIMARY KEY,
    dept_name    VARCHAR(50)  NOT NULL UNIQUE
);
CREATE TABLE employees (
    emp_id       INT           PRIMARY KEY,
    first_name   VARCHAR(50)   NOT NULL,
    last_name    VARCHAR(50)   NOT NULL,
    dept_id      INT           NOT NULL,
    email        VARCHAR(100)  UNIQUE,
    salary       DECIMAL(10,2) NOT NULL,
    hire_date    DATE          NOT NULL,
    CONSTRAINT fk_dept FOREIGN KEY (dept_id) REFERENCES departments(dept_id)
);
CREATE TABLE projects (
    project_id    INT           PRIMARY KEY,
    project_name  VARCHAR(100)  NOT NULL UNIQUE,
    lead_emp_id   INT           NOT NULL,
    start_date    DATE          NOT NULL,
    end_date      DATE,
    CONSTRAINT fk_emp FOREIGN KEY (lead_emp_id) REFERENCES employees(emp_id)
);
INSERT INTO departments VALUES (1, 'Human Resources');
INSERT INTO departments VALUES (2, 'Finance');
INSERT INTO departments VALUES (3, 'IT');
INSERT INTO employees VALUES (101, 'John', 'Doe', 1, 'john.doe@example.com', 50000, '2020-03-15');
INSERT INTO employees VALUES (102, 'Jane', 'Smith', 2, 'jane.smith@example.com', 60000, '2019-07-01');
INSERT INTO employees VALUES (103, 'Alice', 'Johnson', 3, 'alice.johnson@example.com', 70000, '2021-01-20');
INSERT INTO employees VALUES (104, 'Bob', 'Brown', 3, 'bob.brown@example.com', 75000, '2018-10-10');
INSERT INTO projects VALUES (1001, 'Website Redesign', 103, '2023-01-01', '2023-06-30');
INSERT INTO projects VALUES (1002, 'Finance Audit', 102, '2023-03-15', '2023-09-15');
INSERT INTO projects VALUES (1003, 'Recruitment Drive', 101, '2023-05-01', NULL);
SELECT 
    e.emp_id       AS "Employee ID",
    e.first_name   AS "First Name",
    e.last_name    AS "Last Name",
    d.dept_name    AS "Department",
    e.salary       AS "Salary ($)"
FROM employees e
JOIN departments d ON e.dept_id = d.dept_id
WHERE e.first_name LIKE 'J%';
CREATE OR REPLACE VIEW it_project_leads_view AS
SELECT 
    e.emp_id       AS "Employee ID",
    e.first_name   AS "First Name",
    e.last_name    AS "Last Name",
    d.dept_name    AS "Department",
    e.salary       AS "Salary ($)",
    p.project_name AS "Project Name",
    p.start_date   AS "Start Date",
    p.end_date     AS "End Date"
FROM employees e
JOIN departments d ON e.dept_id = d.dept_id
JOIN projects p ON e.emp_id = p.lead_emp_id
WHERE d.dept_name = 'IT' AND e.salary > 65000;

SELECT * FROM it_project_leads_view;
