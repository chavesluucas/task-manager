package com.task.manager.request;

import com.task.manager.status.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
public class UpdateTaskRequest {

    @NotBlank(message= "{update.task.request.title.mandatory}")
    private String title;
    @Length(max = 150, message = "{update.task.request.description.limited}")
    private String description;
    @NotNull(message= "{update.task.request.estimatedHours.mandatory}")
    private Integer estimatedHours;
    private StatusEnum status;
    private Long assignedId;
    private Integer leadTime;

}
