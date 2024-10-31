package com.task.manager.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterTaskRequest {

    private String title;
    private String description;
    private Long authorId;
    private int estimatedHours;

}
