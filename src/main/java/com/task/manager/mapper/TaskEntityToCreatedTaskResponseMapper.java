package com.task.manager.mapper;

import com.task.manager.entity.TaskEntity;
import com.task.manager.response.CreatedTaskResponse;
import org.springframework.stereotype.Service;

@Service
public class TaskEntityToCreatedTaskResponseMapper {

    public CreatedTaskResponse toResponse(TaskEntity task) {
        return CreatedTaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .author(task.getAuthor().getUsername())
                .estimatedHours(task.getEstimatedHours())
                .build();
    }

}
