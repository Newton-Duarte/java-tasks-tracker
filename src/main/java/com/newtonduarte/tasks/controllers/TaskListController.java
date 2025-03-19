package com.newtonduarte.tasks.controllers;

import com.newtonduarte.tasks.domain.dto.TaskListDto;
import com.newtonduarte.tasks.domain.entities.TaskList;
import com.newtonduarte.tasks.mappers.TaskListMapper;
import com.newtonduarte.tasks.services.TaskListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping(path = "/{id}")
    public ResponseEntity<TaskListDto> getTaskList(@PathVariable UUID id) {
        TaskList taskList = taskListService.getTaskList(id);
        return ResponseEntity.ok(taskListMapper.toDto(taskList));
    }

    @PostMapping
    public ResponseEntity<TaskListDto> createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskList createdTaskList = taskListService.createTaskList(taskListMapper.fromDto(taskListDto));
        return new ResponseEntity<>(taskListMapper.toDto(createdTaskList), HttpStatus.CREATED);
    }
}
