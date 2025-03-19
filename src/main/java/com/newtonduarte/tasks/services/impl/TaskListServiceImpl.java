package com.newtonduarte.tasks.services.impl;

import com.newtonduarte.tasks.domain.entities.TaskList;
import com.newtonduarte.tasks.repositories.TaskListRepository;
import com.newtonduarte.tasks.services.TaskListService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {
    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> getTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList getTaskList(UUID id) {
        return taskListRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task List not found with id " + id));
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if (null != taskList.getId()) {
            throw new IllegalArgumentException("Task list already has an ID");
        }

        if (null == taskList.getTitle() || taskList.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task list title must be present!");
        }

        LocalDateTime now = LocalDateTime.now();

        return taskListRepository.save(
                new TaskList(
                        null,
                        taskList.getTitle(),
                        taskList.getDescription(),
                        null,
                        now,
                        now
                )
        );
    }

    @Override
    public TaskList updateTaskList(UUID id, TaskList taskList) {
        TaskList existingTaskList = getTaskList(id);

        if (!Objects.equals(taskList.getId(), id)) {
            throw new IllegalArgumentException("Attempting to change task list ID, this is not permitted!");
        }

        if (null == taskList.getTitle() || taskList.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task list title must be present!");
        }

        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdatedAt(LocalDateTime.now());

        return taskListRepository.save(existingTaskList);
    }
}
