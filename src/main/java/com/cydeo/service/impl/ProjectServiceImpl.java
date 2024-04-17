package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.enums.ProjectStatus;
import com.cydeo.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {
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
}
