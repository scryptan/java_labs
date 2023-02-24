package com.scryptan.springlab.controller;

import com.scryptan.springlab.dto.UserDTO;
import com.scryptan.springlab.entity.User;
import com.scryptan.springlab.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SecurityController {
    private UserService userService;

    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);

        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDTO userDTO,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(userDTO.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty())
            result.rejectValue("email",
                    String.valueOf(400),
                    "Этот email уже зарегистрирован.");

        if(result.hasErrors())
        {
            model.addAttribute("user", userDTO);
            return "/register";
        }

        userService.saveUser(userDTO);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<UserDTO> users = userService.findAllUsers();
        model.addAttribute("users", users);

        return "/users";
    }
}
