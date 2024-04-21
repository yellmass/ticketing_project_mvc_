package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Gender;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final RoleService roleService;
    private final UserService userService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createUser(Model model) {

        model.addAttribute("user", new UserDTO());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("genders", Gender.values());

        return "/user/create";
    }

    @PostMapping("/create")
    public String insertUser(@Valid @ModelAttribute UserDTO user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()){

            model.addAttribute("users", userService.findAll());
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("genders", Gender.values());

            return "user/create";
        }
        userService.save(user);

        return "redirect:/user/create";
    }

    @GetMapping("/update/{username}")
    public String editUser(@PathVariable String username, Model model){

        UserDTO user = userService.findById(username);

        model.addAttribute("user", user);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("genders", Gender.values());

        return "user/update";
    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute UserDTO user){

        userService.update(user);

        return "redirect:/user/create";
    }

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable String username){

        userService.deleteById(username);

        return "redirect:/user/create";
    }

}
