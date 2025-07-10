package com.jn.api_gastos.user.service;

import com.jn.api_gastos.user.dto.UserDTO;
import com.jn.api_gastos.user.model.User;
import java.util.List;

public interface IUserService {
    public List<UserDTO> listUsers();

    public User findUserById(Integer idUser);

    public User saveUser(User user);
}
