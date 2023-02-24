package com.scryptan.springlab.service;

import com.scryptan.springlab.dto.UserDTO;
import com.scryptan.springlab.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDto);
    User findUserByEmail(String email);
    List<UserDTO> findAllUsers();
}
