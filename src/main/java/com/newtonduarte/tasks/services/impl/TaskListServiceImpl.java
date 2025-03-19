package com.newtonduarte.tasks.services.impl;

import com.newtonduarte.tasks.domain.entities.TaskList;
import com.newtonduarte.tasks.repositories.TaskListRepository;
import com.newtonduarte.tasks.services.TaskListService;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
