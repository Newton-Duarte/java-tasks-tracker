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
import java.util.Objects;
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

    @Override
    public Task updateTask(UUID taskListId, UUID id, Task task) {
        Task existingTask = getTask(taskListId, id);

        if (!Objects.equals(id, task.getId())) {
            throw new IllegalArgumentException("Task IDs do not match!");
        }

        if (null == task.getTitle() || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task must have a title!");
        }

        if (null == task.getPriority()) {
            throw new IllegalArgumentException("Task must have a valid priority!");
        }

        if (null == task.getStatus()) {
            throw new IllegalArgumentException("Task must have a valid status!");
        }

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdatedAt(LocalDateTime.now());

        return taskRepository.save(existingTask);
    }
}
