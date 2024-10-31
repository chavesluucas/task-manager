package com.task.manager.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateTaskResponse {

    Long id;
    String title;
    String description;
    String assignedName;
    int leadTime;
    int estimatedHours;
    String status;

}
