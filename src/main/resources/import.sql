--CREATE TABLE IF NOT EXISTS "user" (
--    id BIGINT PRIMARY KEY,
--    name VARCHAR(255) NOT NULL,
--    surname VARCHAR(255) NOT NULL,
--    birthdate DATE NOT NULL,
--    status VARCHAR(50) NOT NULL,
--    role VARCHAR(50) NOT NULL
--);

-- CREACIÓN DE MODULOS
INSERT INTO module (id, name, base_path) VALUES (1, 'USERS', '/users');
INSERT INTO module (id, name, base_path) VALUES (2, 'AUTH', '/auth');

-- CREACIÓN DE OPERACIONES
INSERT INTO operation (id, name, path, http_method, permit_all, module_id) VALUES (1, 'READ_ALL_USERS','', 'GET', false, 1);
INSERT INTO operation (id, name, path, http_method, permit_all, module_id) VALUES (2, 'READ_ONE_USER','/[0-9]*', 'GET', false, 1);
INSERT INTO operation (id, name, path, http_method, permit_all, module_id) VALUES (3, 'UPDATE_ONE_USER','/[0-9]*', 'PUT', false, 1);
INSERT INTO operation (id, name, path, http_method, permit_all, module_id) VALUES (4, 'DISABLE_ONE_USER','/[0-9]*/disabled', 'PUT', false, 1);

INSERT INTO operation (id, name, path, http_method, permit_all, module_id) VALUES (5, 'REGISTER_ONE_USER','', 'POST', false, 2);
INSERT INTO operation (id, name, path, http_method, permit_all, module_id) VALUES (6, 'READ_MY_PROFILE','/profile','GET', false, 2);

INSERT INTO operation (id, name, path, http_method, permit_all, module_id) VALUES (7, 'AUTHENTICATE','/authenticate', 'POST', true, 2);
INSERT INTO operation (id, name, path, http_method, permit_all, module_id) VALUES (8, 'VALIDATE-TOKEN','/validate-token', 'GET', true, 2);
INSERT INTO operation (id, name, path, http_method, permit_all, module_id) VALUES (9, 'LOGIN','/login', 'POST', true, 2);

-- CREACIÓN DE ROLES
INSERT INTO role (id, name) VALUES (1, 'ADMIN');
INSERT INTO role (id, name) VALUES (2, 'USER');

-- CREACIÓN DE PERMISOS
INSERT INTO role_operation (id, role_id, operation_id) VALUES (1, 1, 1);
INSERT INTO role_operation (id, role_id, operation_id) VALUES (2, 1, 2);
INSERT INTO role_operation (id, role_id, operation_id) VALUES (3, 1, 3);
INSERT INTO role_operation (id, role_id, operation_id) VALUES (4, 1, 4);
INSERT INTO role_operation (id, role_id, operation_id) VALUES (5, 1, 5);
INSERT INTO role_operation (id, role_id, operation_id) VALUES (6, 1, 6);
INSERT INTO role_operation (id, role_id, operation_id) VALUES (7, 1, 7);
INSERT INTO role_operation (id, role_id, operation_id) VALUES (8, 1, 8);
INSERT INTO role_operation (id, role_id, operation_id) VALUES (9, 1, 9);

INSERT INTO role_operation (id, role_id, operation_id) VALUES (10, 2, 1);
--INSERT INTO role_operation (id, role_id, operation_id) VALUES (11, 2, 2);
INSERT INTO role_operation (id, role_id, operation_id) VALUES (12, 2, 6);
INSERT INTO role_operation (id, role_id, operation_id) VALUES (13, 2, 7);
INSERT INTO role_operation (id, role_id, operation_id) VALUES (14, 2, 8);
INSERT INTO role_operation (id, role_id, operation_id) VALUES (15, 2, 9);



--- Example pass 12345678
INSERT INTO "user"(id, name, username, password, birthdate, status, role_id) VALUES (1, 'john', 'userAdmin', '$2a$10$Ic1Ar5OViLYfyUgahRH2NOAA/By35rFj4/fOIrputKHR8otKw3Rm2', '2002-08-06', 'ACTIVE', 1);
INSERT INTO "user"(id, name, username, password, birthdate, status, role_id) VALUES (2, 'amy', 'userRegular1', '$2a$10$Ic1Ar5OViLYfyUgahRH2NOAA/By35rFj4/fOIrputKHR8otKw3Rm2','1986-11-07', 'ACTIVE', 2);
INSERT INTO "user"(id, name, username, password, birthdate, status, role_id) VALUES (3, 'mathew', 'userRegular2', '$2a$10$Ic1Ar5OViLYfyUgahRH2NOAA/By35rFj4/fOIrputKHR8otKw3Rm2','2012-01-06', 'ACTIVE', 2);

