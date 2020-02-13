-- name: get-user-permissions
SELECT
    permissions.permission
FROM
    permissions,
    users,
    role,
    user_roles,
    role_permissions
WHERE
    users.email = :email
    and user_roles.user_id = users.id
    and role.id = user_roles.role_id
    and role_permissions.role_id = role.id
    and permissions.id = role_permissions.permissions_id
;

-- name: get-user-password
-- single?: true
SELECT
    users.password
FROM
    users
WHERE
    users.email = :email
;
