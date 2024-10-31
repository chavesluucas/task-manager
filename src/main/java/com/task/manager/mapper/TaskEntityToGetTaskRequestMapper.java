package com.task.manager.mapper;

import com.task.manager.entity.TaskEntity;
import com.task.manager.response.GetTaskResponse;
import org.springframework.stereotype.Service;

@Service
public class TaskEntityToGetTaskRequestMapper {

    public GetTaskResponse toRquest(TaskEntity task) {
        return GetTaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .assigned(task.getAssigned() != null ? task.getAssigned().getUsername() : "NOT_ASSIGNED")
                .author(task.getAuthor().getUsername())
                .status(task.getStatus())
                .estimatedHours(task.getEstimatedHours())
                .leadTime(task.getLeadTime())
                .createdDate(task.getCreatedDate())
                .updatedDate(task.getUpdatedDate())
                .build();
    }

}
