package com.newtonduarte.tasks.mappers;

import com.newtonduarte.tasks.domain.dto.TaskDto;
import com.newtonduarte.tasks.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);
}
