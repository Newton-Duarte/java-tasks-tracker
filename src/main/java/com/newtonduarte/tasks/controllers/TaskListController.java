package com.newtonduarte.tasks.controllers;

import com.newtonduarte.tasks.domain.dto.TaskListDto;
import com.newtonduarte.tasks.domain.entities.TaskList;
import com.newtonduarte.tasks.mappers.TaskListMapper;
import com.newtonduarte.tasks.services.TaskListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/tasks-lists")
public class TaskListController {
    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public ResponseEntity<List<TaskListDto>> getTasksLists() {
        List<TaskList> taskLists = taskListService.getTaskLists();
        List<TaskListDto> taskListsDto = taskLists.stream().map(taskListMapper::toDto).toList();
        return ResponseEntity.ok(taskListsDto);
    }
}
