package com.task.manager.service;

import com.task.manager.entity.TaskEntity;
import com.task.manager.exception.DeleteNotAllowedException;
import com.task.manager.exception.NotAllowedToChangeStatusException;
import com.task.manager.exception.TaskAlreadyExistsExcepetion;
import com.task.manager.mapper.RegisterTaskRequestToTaskEntityMapper;
import com.task.manager.mapper.UpdateTaskRequestToTaskEntityMapper;
import com.task.manager.repository.TaskManagerRepository;
import com.task.manager.request.CreatedTaskRequest;
import com.task.manager.request.UpdateTaskRequest;
import com.task.manager.status.StatusEnum;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter(onMethod_ = @Autowired)
public class TaskManagerService {

    TaskManagerRepository taskManagerRepository;
    RegisterTaskRequestToTaskEntityMapper registerTaskRequestToTaskEntityMapper;
    UpdateTaskRequestToTaskEntityMapper updateTaskRequestToTaskEntityMapper;


    public TaskEntity saveTask(CreatedTaskRequest request) {
        TaskEntity taskEqualsByTitleOrDescription = taskManagerRepository.findByTitleOrDescription(request.getTitle(), request.getDescription());

        if (taskEqualsByTitleOrDescription != null) {
            throw new TaskAlreadyExistsExcepetion("Task with the same title or description already exists");
        }

        return taskManagerRepository.save(registerTaskRequestToTaskEntityMapper.toEntity(request, StatusEnum.CREATED));
    }

    public Page<TaskEntity> getTaskByTitle(String title, Pageable pageable) {
        return this.taskManagerRepository.findByTitleContaining(title,pageable);
    }

    public Page<TaskEntity> getAllTasks(Pageable pageable) {
        return this.taskManagerRepository.findAll(pageable);
    }

    public TaskEntity updateTask(Long id, UpdateTaskRequest request) {
        TaskEntity task = this.taskManagerRepository.findById(id).get();

        validateIfTaskCanBeMoved(request, task);

        TaskEntity updatedTask = updateTaskRequestToTaskEntityMapper
                .toEntity(request, task);

        return taskManagerRepository.save(updatedTask);
    }

    public void deleteTask(Long id) {
        TaskEntity task = this.taskManagerRepository.findById(id).get();

        if (!StatusEnum.CREATED.equals(task.getStatus())) {
            throw new DeleteNotAllowedException();
        }

        this.taskManagerRepository.deleteById(id);
    }

    private static void validateIfTaskCanBeMoved(UpdateTaskRequest request, TaskEntity task) {
        if (task.getStatus().equals(StatusEnum.CREATED) && request.getStatus().equals(StatusEnum.DONE)) {
            throw new NotAllowedToChangeStatusException("Do not allow moving the task to FINISHED if it is currently in CREATED");
        }

        if (task.getStatus().equals(StatusEnum.BLOCKED) && request.getStatus().equals(StatusEnum.DONE)) {
            throw new NotAllowedToChangeStatusException("Do not allow moving the task to FINISHED if it is currently in BLOCKED");
        }

        if (task.getStatus().equals(StatusEnum.DONE)) {
            throw new NotAllowedToChangeStatusException("Moving a task with a status of DONE is not allowed.");
        }
    }

}
