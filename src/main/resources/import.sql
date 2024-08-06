--CREATE TABLE IF NOT EXISTS employee (
--    id BIGINT PRIMARY KEY,
--    name VARCHAR(255) NOT NULL,
--    surname VARCHAR(255) NOT NULL,
--    birthdate DATE NOT NULL,
--    status VARCHAR(50) NOT NULL,
--    role VARCHAR(50) NOT NULL
--);

INSERT INTO employee(id, name, surname, birthdate, status, role) VALUES ('1549456', 'john', 'doe', '2002-08-06', 'ACTIVE', 'USER');
INSERT INTO employee(id, name, surname, birthdate, status, role) VALUES ('1549499', 'amy', 'jhonson', '1986-11-07', 'ACTIVE', 'USER');
INSERT INTO employee(id, name, surname, birthdate, status, role) VALUES ('1549489', 'mathew', 'morgan', '2012-01-06', 'ACTIVE', 'USER');

-- Customers example pass: 1234578
INSERT INTO customer(id, name, username, password, role) VALUES ('1', 'mathew', 'userAdmin', '$2a$10$Ic1Ar5OViLYfyUgahRH2NOAA/By35rFj4/fOIrputKHR8otKw3Rm2', 'ADMIN');
INSERT INTO customer(id, name, username, password, role) VALUES ('2', 'dexter', 'userRegular', '$2a$10$Ic1Ar5OViLYfyUgahRH2NOAA/By35rFj4/fOIrputKHR8otKw3Rm2', 'USER');