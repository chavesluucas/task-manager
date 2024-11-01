package com.task.manager.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CreatedTaskRequest {

    @NotBlank(message= "{register.task.request.title.mandatory}")
    private String title;

    @Length(max = 150, message= "{register.task.request.description.limited}")
    private String description;

    @NotNull(message= "{register.task.request.author.mandatory}")
    private Long authorId;

    @NotNull(message= "{register.task.request.estimatedHours.mandatory}")
    private Integer estimatedHours;

}
