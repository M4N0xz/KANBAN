package com.unicesimar.demo.kanban.model;

import com.unicesimar.demo.kanban.enums.Priority;
import com.unicesimar.demo.kanban.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @PrePersist
    public void prePersist() {
        creationDate = LocalDateTime.now();
        if (status == null) {
            status = TaskStatus.TODO;
        }
    }
}
