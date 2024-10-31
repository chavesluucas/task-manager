package com.task.manager.controller;

import com.task.manager.entity.TaskEntity;
import com.task.manager.mapper.TaskEntityToCreatedTaskResponseMapper;
import com.task.manager.mapper.TaskEntityToGetTaskRequestMapper;
import com.task.manager.mapper.TaskEntityToUpdateTaskResponseMapper;
import com.task.manager.request.RegisterTaskRequest;
import com.task.manager.request.UpdateTaskRequest;
import com.task.manager.response.CreatedTaskResponse;
import com.task.manager.response.GetPageResponse;
import com.task.manager.response.GetTaskResponse;
import com.task.manager.response.UpdateTaskResponse;
import com.task.manager.service.TaskManagerService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task-manager")
@Setter(onMethod_ = @Autowired)
public class TaskManagerController {

    TaskManagerService taskManagerService;
    TaskEntityToCreatedTaskResponseMapper taskEntityToCreatedTaskResponseMapper;
    TaskEntityToGetTaskRequestMapper taskEntityToGetTaskRequestMapper;
    TaskEntityToUpdateTaskResponseMapper taskEntityToUpdateTaskResponseMapper;

    @PostMapping
    public ResponseEntity<CreatedTaskResponse> saveTask(@RequestBody RegisterTaskRequest request) {
        CreatedTaskResponse response = taskEntityToCreatedTaskResponseMapper.toResponse(taskManagerService.saveTask(request));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<GetPageResponse> getTask(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Page<TaskEntity> pageTasks = null;

        if (title == null) {
            pageTasks = this.taskManagerService.getAllTasks(PageRequest.of(page, size));
        } else {
            pageTasks = this.taskManagerService.getTaskByTitle(title, PageRequest.of(page, size));
        }

        List<GetTaskResponse> tasks = pageTasks.getContent()
                .stream()
                .map(
                        task -> {
                            return taskEntityToGetTaskRequestMapper.toRquest(task);
                        }).toList();

        GetPageResponse response = buildGetPageResponse(pageTasks, tasks);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<UpdateTaskResponse> updateTask(@PathVariable Long id,@RequestBody UpdateTaskRequest request) {
        TaskEntity taskEntity = taskManagerService.updateTask(id, request);
        UpdateTaskResponse response = taskEntityToUpdateTaskResponseMapper.toResponse(id, taskEntity);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskManagerService.deleteTask(id);
    }

    private static GetPageResponse buildGetPageResponse(Page<TaskEntity> pageTasks, List<GetTaskResponse> tasks) {
        return GetPageResponse.builder()
                .totalPages(pageTasks.getNumber())
                .totalItens(pageTasks.getTotalPages())
                .currentPage(pageTasks.getTotalElements())
                .tasks(tasks)
                .build();
    }

}
