package com.task.manager.status;

/*
1 - Do not allow deleting a task if the status is different from CREATED
2 - Do not allow moving the task to FINISHED if it is currently in CREATED
3 - Do not allow moving the task to FINISHED if it is currently in BLOCKED
4 - Once a task is marked as FINISHED, it cannot change status or be deleted
5 - If a task has the same description or title, notify that a task with this information already exists
*/
public enum StatusEnum {

    CREATED,
    IN_PROGRESS,
    BLOCKED,
    DONE

}
