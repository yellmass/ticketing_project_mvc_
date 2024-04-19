package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.ProjectStatus;
import com.cydeo.enums.TaskStatus;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {
    private final TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ProjectDTO save(ProjectDTO projectDTO) {
        if (projectDTO.getProjectStatus()==null){
            projectDTO.setProjectStatus(ProjectStatus.OPEN);
        }
        return super.save(projectDTO.getCode(), projectDTO);
    }

    @Override
    public ProjectDTO findById(String s) {
        return super.findById(s);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(String s) {
        super.deleteById(s);
    }

    @Override
    public void update(ProjectDTO projectDTO) {
        if (projectDTO.getProjectStatus()==null){
            ProjectStatus projectStatus = super.findById(projectDTO.getCode()).getProjectStatus();
            projectDTO.setProjectStatus(projectStatus);
        }

        super.update(projectDTO.getCode(), projectDTO);
    }

    @Override
    public void complete(String projectCode) {
        ProjectDTO project = super.findById(projectCode);
        project.setProjectStatus(ProjectStatus.COMPLETED);

    }

    @Override
    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {
        List<ProjectDTO> projectList = super.findAll().stream()
                .filter(project -> project.getAssignedManager().getUserName().equals(manager.getUserName()))
                .map(project -> {
                    List<TaskDTO> taskList = taskService.findAll().stream()
                            .filter(task->task.getProject().getCode().equals(project.getCode()))
                            .collect(Collectors.toList());

                    int unfinishedCount = (int) taskList.stream().filter(task->task.getTaskStatus() != TaskStatus.COMPLETED).count();
                    int completedCount = (int) taskList.stream().filter(task->task.getTaskStatus() == TaskStatus.COMPLETED).count();

                    project.setUnfinishedTaskCounts(unfinishedCount);
                    project.setCompletedTaskCounts(completedCount);

                    return project;
                })
                .collect(Collectors.toList());


        return projectList;
    }
}
