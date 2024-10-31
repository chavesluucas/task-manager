package com.task.manager.request;

import com.task.manager.entity.UserEntity;
import com.task.manager.status.StatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateTaskRequest {

    private String title;
    private String description;
    private StatusEnum status;
    private Long assignedId;
    private int estimatedHours;
    private Integer leadTime;

}
