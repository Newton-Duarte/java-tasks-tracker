package com.newtonduarte.tasks.services.impl;

import com.newtonduarte.tasks.domain.entities.Task;
import com.newtonduarte.tasks.domain.entities.TaskList;
import com.newtonduarte.tasks.domain.entities.TaskPriority;
import com.newtonduarte.tasks.domain.entities.TaskStatus;
import com.newtonduarte.tasks.repositories.TaskListRepository;
import com.newtonduarte.tasks.repositories.TaskRepository;
import com.newtonduarte.tasks.services.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> getTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Task getTask(UUID taskListId, UUID id) {
        return taskRepository
                .findByTaskListIdAndId(taskListId, id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with ID " + id));
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {
        if (null != task.getId()) {
            throw new IllegalArgumentException("Task already has an ID");
        }

        if (null == task.getTitle() || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task must have a title!");
        }

        TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);
        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList = taskListRepository
                .findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Task List ID provided!"));

        LocalDateTime now = LocalDateTime.now();
        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                now,
                now
        );

        return taskRepository.save(taskToSave);
    }
}
