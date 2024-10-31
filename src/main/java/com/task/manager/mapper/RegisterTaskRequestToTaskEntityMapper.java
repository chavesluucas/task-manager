package com.task.manager.mapper;

import com.task.manager.entity.TaskEntity;
import com.task.manager.request.RegisterTaskRequest;
import com.task.manager.service.UserService;
import com.task.manager.status.StatusEnum;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Setter(onMethod_ = @Autowired)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterTaskRequestToTaskEntityMapper {

    UserService userService;

    public TaskEntity toEntity(RegisterTaskRequest request, StatusEnum status) {
        return TaskEntity.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(status)
                .estimatedHours(request.getEstimatedHours())
                .author(userService.findById(request.getAuthorId()).get())
                .build();
    }

}
