package com.github.iqpizza6349.flower_tood.controller;

import com.github.iqpizza6349.flower_tood.dto.UserDTO;
import com.github.iqpizza6349.flower_tood.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public String signUp(UserDTO userDTO) {
        userService.save(userDTO);
        return "redirect:/login";
    }

}
