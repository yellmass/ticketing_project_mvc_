package com.cydeo.dto;

import com.cydeo.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private UUID taskId;
    private ProjectDTO project;
    private UserDTO assignedEmployee;
    private String taskSubject;
    private String taskDetail;
    private TaskStatus taskStatus;
    private LocalDate assignedDate;
}
