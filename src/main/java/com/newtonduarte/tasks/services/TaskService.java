package com.newtonduarte.tasks.services;

import com.newtonduarte.tasks.domain.entities.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<Task> getTasks(UUID taskListId);
    Task getTask(UUID taskListId, UUID id);
    Task createTask(UUID taskListId, Task task);
    Task updateTask(UUID taskListId, UUID id, Task task);
    void deleteTask(UUID taskListId, UUID id);
}
