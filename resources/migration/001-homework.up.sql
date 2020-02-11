CREATE TABLE users (
id SERIAL PRIMARY KEY,
email VARCHAR,
password VARCHAR);

CREATE TABLE role (
id SERIAL PRIMARY KEY,
name VARCHAR);


CREATE TABLE permissions (
id SERIAL PRIMARY KEY,
permission VARCHAR);

CREATE TABLE user_roles (
id SERIAL PRIMARY KEY,
user_id INT,
role_id INT,
FOREIGN KEY (user_id) REFERENCES users (id),
FOREIGN KEY (role_id) REFERENCES role (id));


CREATE TABLE role_permissions (
id SERIAL PRIMARY KEY,
role_id INT,
permissions_id INT,
FOREIGN KEY (permissions_id) REFERENCES permissions (id),
FOREIGN KEY (role_id) REFERENCES role (id));


