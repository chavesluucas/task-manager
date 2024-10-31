package com.task.manager.mapper;

import com.task.manager.entity.TaskEntity;
import com.task.manager.request.UpdateTaskRequest;
import com.task.manager.service.UserService;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter(onMethod_ = @Autowired)
public class UpdateTaskRequestToTaskEntityMapper {

    UserService userService;

    public TaskEntity toEntity(UpdateTaskRequest request, TaskEntity task) {
        task.setTitle(request.getTitle());
        task.setAssigned(userService.findById(request.getAssignedId()).get());
        task.setDescription(request.getDescription());
        task.setEstimatedHours(request.getEstimatedHours());
        task.setLeadTime(request.getLeadTime());
        task.setStatus(request.getStatus());

        return task;
    }

}
