package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.TaskStatus;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final ProjectService projectService;
    private final UserService userService;
    private final TaskService taskService;

    public TaskController(ProjectService projectService, UserService userService, TaskService taskService) {
        this.projectService = projectService;
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/create")
    public String createTask(Model model){

        model.addAttribute("task", new TaskDTO());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("tasks", taskService.findAll());

        return "task/create";
    }

    @PostMapping("/create")
    public String insertTask(@ModelAttribute TaskDTO task){

        taskService.save(task);

        return "redirect:/task/create";
    }

    @GetMapping("/update/{taskId}")
    public String editTask(@PathVariable UUID taskId, Model model){

        model.addAttribute("task", taskService.findById(taskId));
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("tasks", taskService.findAll());

        return "task/update";
    }

    /*@PostMapping("/update/{taskId}")
    public String updateTask(@ModelAttribute TaskDTO task, @PathVariable UUID taskId){

        task.setTaskId(taskId);
        taskService.update(task);

        return "redirect:/task/create";
    }*/

    @PostMapping("/update/{taskId}")
    public String updateTask(@ModelAttribute TaskDTO task){

        taskService.update(task);

        return "redirect:/task/create";
    }

    @GetMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable UUID taskId ){

        taskService.deleteById(taskId);

        return "redirect:/task/create";
    }

    @GetMapping("/employee/pending-tasks")
    public String getPendingTasks(Model model){

        model.addAttribute("tasks",taskService.findPendingTasks());

        return "task/pending-tasks";
    }

    @GetMapping("/employee/archive")
    public String getArchive(Model model){

        model.addAttribute("tasks", taskService.findCompletedTasks());

        return "/task/archive";
    }

    @GetMapping("/employee/edit/{taskId}")
    public String editEmployeeTask(@PathVariable UUID taskId, Model model){

        model.addAttribute("task", taskService.findById(taskId));
        model.addAttribute("statuses", TaskStatus.values());
        model.addAttribute("tasks", taskService.findPendingTasks());

        return "task/status-update";
    }

    @PostMapping("employee/update/{taskId}")
    public String updateEmployeeTask(@ModelAttribute TaskDTO task){

        taskService.updateEmployeeTask(task);

        return "redirect:/task/employee/pending-tasks";
    }

}
