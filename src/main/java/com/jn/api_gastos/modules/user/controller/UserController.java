package com.jn.api_gastos.modules.user.controller;

import com.jn.api_gastos.modules.user.dto.UserDTO;
import com.jn.api_gastos.modules.user.model.User;
import com.jn.api_gastos.modules.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.base.path}")
@CrossOrigin(value="http://localhost:5173")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/user")
    public List<UserDTO> getUsers() {
        return  userService.listUsers().stream().toList();
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user){
        return userService.saveUser(user);
    }
}
