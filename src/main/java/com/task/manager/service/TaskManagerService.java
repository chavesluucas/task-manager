package com.task.manager.service;

import com.task.manager.entity.TaskEntity;
import com.task.manager.mapper.RegisterTaskRequestToTaskEntityMapper;
import com.task.manager.mapper.UpdateTaskRequestToTaskEntityMapper;
import com.task.manager.repository.TaskManagerRepository;
import com.task.manager.request.RegisterTaskRequest;
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


    public TaskEntity saveTask(RegisterTaskRequest request) {
        return taskManagerRepository.save(registerTaskRequestToTaskEntityMapper.toEntity(request, StatusEnum.CREATED));
    }

    public Page<TaskEntity> getTaskByTitle(String title, Pageable pageable) {
        return this.taskManagerRepository.findByTitleContaining(title,pageable);
    }

    public Page<TaskEntity> getAllTasks(Pageable pageable) {
        return this.taskManagerRepository.findAll(pageable);
    }

    public TaskEntity updateTask(Long id, UpdateTaskRequest request) {
        TaskEntity updatedTask = updateTaskRequestToTaskEntityMapper
                .toEntity(request, this.taskManagerRepository.findById(id).get());

        return taskManagerRepository.save(updatedTask);
    }

    public void deleteTask(Long id) {
        this.taskManagerRepository.deleteById(id);
    }

}
