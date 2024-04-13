package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.enums.Gender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/create")
    public String createUser(Model model){

        model.addAttribute("user", new UserDTO());
        //model.addAttribute("roles", );
        model.addAttribute("genders", Gender.values());

    return "/user/create";
    }

    /*@PostMapping("/create")
    public String userList(@ModelAttribute User user, Model model){



        return "redirect:/user/create";
    }*/

}
