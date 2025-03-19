package com.newtonduarte.tasks.services;

import com.newtonduarte.tasks.domain.entities.TaskList;

import java.util.List;
import java.util.UUID;

public interface TaskListService {
    List<TaskList> getTaskLists();
    TaskList getTaskList(UUID id);
    TaskList createTaskList(TaskList taskList);
    TaskList updateTaskList(UUID id, TaskList taskList);
    void deleteTaskList(UUID id);
}
