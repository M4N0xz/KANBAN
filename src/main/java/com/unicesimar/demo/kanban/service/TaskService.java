package com.unicesimar.demo.kanban.service;

import com.unicesimar.demo.kanban.dto.TaskDTO;
import com.unicesimar.demo.kanban.enums.TaskStatus;
import com.unicesimar.demo.kanban.exception.CustomException;
import com.unicesimar.demo.kanban.model.Task;
import com.unicesimar.demo.kanban.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task createTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());
        task.setPriority(taskDTO.getPriority());
        task.setStatus(TaskStatus.TODO);
        return taskRepository.save(task);
    }

    public Map<TaskStatus, List<Task>> getAllTasksByStatus() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .collect(Collectors.groupingBy(Task::getStatus));
    }

    public Task moveTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new CustomException("Task not found"));

        switch (task.getStatus()) {
            case TODO -> task.setStatus(TaskStatus.IN_PROGRESS);
            case IN_PROGRESS -> task.setStatus(TaskStatus.DONE);
            case DONE -> throw new CustomException("Task is already completed");
        }

        return taskRepository.save(task);
    }

    public Task updateTask(Long id, TaskDTO taskDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new CustomException("Task not found"));

        if (taskDTO.getTitle() != null) {
            task.setTitle(taskDTO.getTitle());
        }
        if (taskDTO.getDescription() != null) {
            task.setDescription(taskDTO.getDescription());
        }
        if (taskDTO.getPriority() != null) {
            task.setPriority(taskDTO.getPriority());
        }
        if (taskDTO.getDueDate() != null) {
            task.setDueDate(taskDTO.getDueDate());
        }

        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new CustomException("Task not found");
        }
        taskRepository.deleteById(id);
    }

    public List<Task> getTasksByPriority(TaskStatus status) {
        return taskRepository.findByStatusOrderByPriorityDesc(status);
    }

    public List<Task> getOverdueTasks() {
        return taskRepository.findByDueDateBeforeAndStatusNot(
                LocalDateTime.now(), TaskStatus.DONE);
    }
}
