package com.jn.api_gastos.user.controller;

import com.jn.api_gastos.user.dto.UserDTO;
import com.jn.api_gastos.user.model.User;
import com.jn.api_gastos.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("finanzasps")
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
