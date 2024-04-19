package com.cydeo.dto;

import com.cydeo.enums.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private String name;
    private String code;
    private UserDTO assignedManager;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String projectDetails;
    private ProjectStatus projectStatus;

    private int completedTaskCounts;
    private int unfinishedTaskCounts;

    public ProjectDTO(String name, String code, UserDTO assignedManager, LocalDate startDate, LocalDate endDate, String projectDetails, ProjectStatus projectStatus) {
        this.name = name;
        this.code = code;
        this.assignedManager = assignedManager;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectDetails = projectDetails;
        this.projectStatus = projectStatus;
    }
}
