package com.unicesimar.demo.kanban.repository;

import com.unicesimar.demo.kanban.enums.Priority;
import com.unicesimar.demo.kanban.enums.TaskStatus;
import com.unicesimar.demo.kanban.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByStatusOrderByPriorityDesc(TaskStatus status);
    List<Task> findByPriority(Priority priority);
    List<Task> findByDueDateBeforeAndStatusNot(LocalDateTime date, TaskStatus status);
}