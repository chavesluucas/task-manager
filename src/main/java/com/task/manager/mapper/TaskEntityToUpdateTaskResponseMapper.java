package com.task.manager.mapper;

import com.task.manager.entity.TaskEntity;
import com.task.manager.response.UpdateTaskResponse;
import org.springframework.stereotype.Service;

@Service
public class TaskEntityToUpdateTaskResponseMapper {

    public UpdateTaskResponse toResponse(Long id, TaskEntity task) {
        return UpdateTaskResponse.builder()
                .id(id)
                .title(task.getTitle())
                .description(task.getDescription())
                .assignedName(task.getAssigned().getUsername())
                .leadTime(task.getLeadTime())
                .estimatedHours(task.getEstimatedHours())
                .status(task.getStatus().toString())
                .build();
    }

}
