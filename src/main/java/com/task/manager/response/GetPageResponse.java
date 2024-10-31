package com.task.manager.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetPageResponse {

    Long currentPage;
    int totalItens;
    int totalPages;
    List<GetTaskResponse> tasks;

}
