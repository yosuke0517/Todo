SELECT
    user_id
    ,name
    ,email
    ,password
    ,created_by
    ,created_at
    ,updated_by
    ,updated_at
    ,deleted_by
    ,deleted_at
    ,version
FROM
    users
WHERE
    deleted_at IS NULL
/*%if user.id != null */
AND user_id = /* user.id */1
/*%end*/
/*%if user.email != null */
AND email = /* user.email */'aaa@email.com'
/*%end*/