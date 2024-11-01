package com.task.manager.entity;

import com.task.manager.status.StatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name  = "Task")
@Data
@Getter
@Setter
@Builder
public class TaskEntity implements Serializable {

    private static final long serialVersionUUID = 1L;

    public TaskEntity() {
    }

    public TaskEntity(Long id, String title, String description, StatusEnum status, UserEntity assigned, UserEntity author, int estimatedHours, Integer leadTime, LocalDate createdDate, LocalDate updatedDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.assigned = assigned;
        this.author = author;
        this.estimatedHours = estimatedHours;
        this.leadTime = leadTime;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Column
    private UserEntity assigned;

    @Column(nullable = false)
    private UserEntity author;

    @Column(nullable = false)
    private int estimatedHours;

    @Column
    private Integer leadTime;

    @Column
    @CreationTimestamp
    private LocalDate createdDate;

    @Column
    @UpdateTimestamp
    private LocalDate updatedDate;

}
