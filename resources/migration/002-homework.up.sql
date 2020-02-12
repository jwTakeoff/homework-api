INSERT INTO users(email, password) values ('tom@takeoff.com', '123');

INSERT INTO permissions
(permission) values ('read'), ('write'), ('delete');

INSERT INTO role
(name) values ('admin'), ('user');


INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM
    (select id from users where email = 'tom@takeoff.com') u,
    (select id from role where name = 'admin') r
;

INSERT INTO role_permissions (role_id, permissions_id)
SELECT r.id, p.id
FROM
    (select id from role where name='admin') r,
    (select id from permissions where permission='read') p
;
