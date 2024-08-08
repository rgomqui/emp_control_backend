--CREATE TABLE IF NOT EXISTS employee (
--    id BIGINT PRIMARY KEY,
--    name VARCHAR(255) NOT NULL,
--    surname VARCHAR(255) NOT NULL,
--    birthdate DATE NOT NULL,
--    status VARCHAR(50) NOT NULL,
--    role VARCHAR(50) NOT NULL
--);
--- Example pass 12345678
INSERT INTO employee(id, name, username, password, birthdate, status, role) VALUES ('1', 'john', 'userAdmin', '$2a$10$Ic1Ar5OViLYfyUgahRH2NOAA/By35rFj4/fOIrputKHR8otKw3Rm2', '2002-08-06', 'ACTIVE', 'ADMIN');
INSERT INTO employee(id, name, username, password, birthdate, status, role) VALUES ('2', 'amy', 'userRegular1', '$2a$10$Ic1Ar5OViLYfyUgahRH2NOAA/By35rFj4/fOIrputKHR8otKw3Rm2','1986-11-07', 'ACTIVE', 'USER');
INSERT INTO employee(id, name, username, password, birthdate, status, role) VALUES ('3', 'mathew', 'userRegular2', '$2a$10$Ic1Ar5OViLYfyUgahRH2NOAA/By35rFj4/fOIrputKHR8otKw3Rm2','2012-01-06', 'ACTIVE', 'USER');

