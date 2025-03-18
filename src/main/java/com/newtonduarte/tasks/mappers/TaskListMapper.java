package com.newtonduarte.tasks.mappers;

import com.newtonduarte.tasks.domain.dto.TaskListDto;
import com.newtonduarte.tasks.domain.entities.TaskList;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
