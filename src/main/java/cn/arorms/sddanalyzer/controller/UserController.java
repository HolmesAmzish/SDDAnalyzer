package cn.arorms.sddanalyzer.controller;

import cn.arorms.sddanalyzer.entity.UserEntity;
import cn.arorms.sddanalyzer.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUserByName")
    public UserEntity getUserByName(String username) {
        return userService.getUserByName(username);
    }
}
