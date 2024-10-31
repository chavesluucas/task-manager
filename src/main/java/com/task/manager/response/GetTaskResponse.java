package com.task.manager.response;

import com.task.manager.status.StatusEnum;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetTaskResponse {

    Long id;
    String title;
    String description;
    StatusEnum status;
    String assigned;
    String author;
    int estimatedHours;
    Integer leadTime;
    LocalDate createdDate;
    LocalDate updatedDate;

}
