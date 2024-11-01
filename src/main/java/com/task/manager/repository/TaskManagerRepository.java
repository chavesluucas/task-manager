package com.task.manager.repository;

import com.task.manager.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskManagerRepository extends JpaRepository<TaskEntity, Long> {

    TaskEntity findByTitleOrDescription(String title, String description);

    Page<TaskEntity> findByTitleContaining(String title, Pageable pageable);

    Page<TaskEntity> findAll(Pageable pageable);

}
