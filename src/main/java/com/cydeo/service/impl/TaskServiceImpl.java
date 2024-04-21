package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.TaskStatus;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl extends AbstractMapService<TaskDTO, UUID> implements TaskService {
    @Override
    public TaskDTO save(TaskDTO taskDTO) {
        if (taskDTO.getTaskId()==null){
            taskDTO.setTaskId(UUID.randomUUID());
        }
        if (taskDTO.getTaskStatus()==null){
            taskDTO.setTaskStatus(TaskStatus.OPEN);
        }
        if (taskDTO.getAssignedDate()==null){
            taskDTO.setAssignedDate(LocalDate.now());
        }
        return super.save(taskDTO.getTaskId(),taskDTO);
    }

    @Override
    public TaskDTO findById(UUID taskId) {
        return super.findById(taskId);
    }

    @Override
    public List<TaskDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(UUID taskId) {
        super.deleteById(taskId);
    }

    @Override
    public void update(TaskDTO taskDTO) {
        if (taskDTO.getTaskStatus()==null){
            taskDTO.setTaskStatus(super.findById(taskDTO.getTaskId()).getTaskStatus());
        }
        if (taskDTO.getAssignedDate()==null){
            taskDTO.setAssignedDate(super.findById(taskDTO.getTaskId()).getAssignedDate());
        }
        super.update(taskDTO.getTaskId(),taskDTO);
    }

    @Override
    public List<TaskDTO> findPendingTasks() {
        return findAll().stream()
                .filter(task->!task.getTaskStatus().equals(TaskStatus.COMPLETED))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findCompletedTasks() {
        return findAll().stream()
                .filter(task->task.getTaskStatus().equals(TaskStatus.COMPLETED))
                .collect(Collectors.toList());
    }

    @Override
    public void updateEmployeeTask(TaskDTO taskDTO) {

        if (taskDTO.getAssignedDate()==null){
            taskDTO.setAssignedDate(super.findById(taskDTO.getTaskId()).getAssignedDate());
        }

        super.update(taskDTO.getTaskId(),taskDTO);
    }
}
