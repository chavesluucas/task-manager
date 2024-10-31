package com.task.manager.mapper;

import com.task.manager.entity.TaskEntity;
import com.task.manager.request.UpdateTaskRequest;

public class TaskEntityToUpdateTaskRequestMapper {

    public UpdateTaskRequest toRequest(TaskEntity task) {
        return UpdateTaskRequest.builder()
                .estimatedHours(task.getEstimatedHours())
                .title(task.getTitle())
                .description(task.getDescription())
                .assignedId(task.getAssigned().getId())
                .status(task.getStatus())
                .leadTime(task.getLeadTime())
                .build();
    }

}
