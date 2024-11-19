package com.unicesimar.demo.kanban.dto;

import com.unicesimar.demo.kanban.enums.Priority;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDTO {
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private Priority priority;
}