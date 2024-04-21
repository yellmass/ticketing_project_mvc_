package com.cydeo.service;

import com.cydeo.dto.TaskDTO;

import java.util.List;
import java.util.UUID;

public interface TaskService extends CrudService<TaskDTO, UUID>{
    List<TaskDTO> findPendingTasks();
    List<TaskDTO> findCompletedTasks();

    void updateEmployeeTask(TaskDTO taskDTO);
}
