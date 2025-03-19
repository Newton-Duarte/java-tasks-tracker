package com.newtonduarte.tasks.services;

import com.newtonduarte.tasks.domain.entities.TaskList;

import java.util.List;

public interface TaskListService {
    List<TaskList> getTaskLists();
    TaskList createTaskList(TaskList taskList);
}
