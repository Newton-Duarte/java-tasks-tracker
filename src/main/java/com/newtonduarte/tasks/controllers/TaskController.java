package com.newtonduarte.tasks.controllers;

import com.newtonduarte.tasks.domain.dto.TaskDto;
import com.newtonduarte.tasks.domain.entities.Task;
import com.newtonduarte.tasks.mappers.TaskMapper;
import com.newtonduarte.tasks.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/tasks-lists/{task_list_id}/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getTasks(@PathVariable("task_list_id") UUID taskListId) {
        List<Task> tasks = taskService.getTasks(taskListId);
        List<TaskDto> tasksDto = tasks.stream().map(taskMapper::toDto).toList();
        return ResponseEntity.ok(tasksDto);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TaskDto> getTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("id") UUID id
    ) {
        Task task = taskService.getTask(taskListId, id);
        return ResponseEntity.ok(taskMapper.toDto(task));
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(
            @PathVariable("task_list_id") UUID taskListId,
            @RequestBody TaskDto taskDto
    ) {
        Task createdTask = taskService.createTask(taskListId, taskMapper.fromDto(taskDto));
        return new ResponseEntity<>(taskMapper.toDto(createdTask), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<TaskDto> updateTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("id") UUID id,
            @RequestBody TaskDto taskDto
    ) {
        Task updatedTask = taskService.updateTask(taskListId, id, taskMapper.fromDto(taskDto));
        return ResponseEntity.ok(taskMapper.toDto(updatedTask));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("id") UUID id
    ) {
        taskService.deleteTask(taskListId, id);
        return ResponseEntity.noContent().build();
    }
}
