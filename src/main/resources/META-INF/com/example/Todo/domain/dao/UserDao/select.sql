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
/*%if criteria.id != null */
AND user_id = /* criteria.id */1
/*%end*/
/*%if criteria.email != null */
AND email = /* criteria.email */'aaa@email.com'
/*%end*/