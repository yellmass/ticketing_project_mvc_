package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.ProjectStatus;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final UserService userService;
    private final ProjectService projectService;


    public ProjectController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/create")
    public String createProject(Model model){

        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("managers", userService.findManagers());
        model.addAttribute("projects", projectService.findAll());
        //model.addAttribute("status", ProjectStatus.values());

        return "project/create";
    }

    @PostMapping("create")
    public String insertProject(@ModelAttribute ProjectDTO project){

        projectService.save(project);

        return "redirect:/project/create";
    }

    @GetMapping("/update")
    public String editProject(@RequestParam String projectCode, Model model){

        model.addAttribute("project", projectService.findById(projectCode));
        model.addAttribute("managers", userService.findManagers());
        model.addAttribute("projects", projectService.findAll());
        //model.addAttribute("status", ProjectStatus.values());


        return "project/update";
    }

    @PostMapping("/update")
    public String updateProject(@ModelAttribute ProjectDTO project){

        projectService.update(project);

        return "redirect:/project/create";
    }

    @GetMapping("delete/{projectCode}")
    public String deleteProject(@PathVariable String projectCode){

        projectService.deleteById(projectCode);

        return "redirect:/project/create";
    }

    @GetMapping("/complete/{projectCode}")
    public String completeProject(@PathVariable String projectCode){

        projectService.complete(projectCode);

        return "redirect:/project/create";
    }

    @GetMapping("manager/project-status")
    public String getProjectStatus(Model model){

        UserDTO manager = userService.findById("john@cydeo.com");
        List<ProjectDTO> projects = projectService.getCountedListOfProjectDTO(manager);

        model.addAttribute("projects", projects);

        return "manager/project-status";
    }


}
