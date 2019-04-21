SELECT
    task_id
    ,user_id
    ,task_category
    ,subject
    ,details
    ,created_by
    ,created_at
    ,updated_by
    ,updated_at
    ,deleted_by
    ,deleted_at
    ,version
FROM
    tasks
WHERE
    deleted_at IS NULL
/*%if userId != null */
AND user_id = /* userId */1
/*%end*/
ORDER BY task_id ASC