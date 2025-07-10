package com.jn.api_gastos.user.service;

import com.jn.api_gastos.user.dto.UserDTO;
import com.jn.api_gastos.user.model.User;
import com.jn.api_gastos.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> listUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(
                        user.getIdUser(),
                        user.getName(),
                        user.getLastname(),
                        user.getUsername()
                ))
                .toList();
    }

    @Override
    public User findUserById(Integer idUser) {
        return userRepository.findById(idUser).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
