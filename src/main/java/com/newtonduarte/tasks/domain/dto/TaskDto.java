package com.newtonduarte.tasks.domain.dto;

import com.newtonduarte.tasks.domain.entities.TaskPriority;
import com.newtonduarte.tasks.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}
