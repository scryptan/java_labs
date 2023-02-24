package com.scryptan.springlab.service;

import com.scryptan.springlab.dto.UserDTO;
import com.scryptan.springlab.entity.Role;
import com.scryptan.springlab.entity.User;
import com.scryptan.springlab.repository.RoleRepository;
import com.scryptan.springlab.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDTO userDto) {
        User user = new User();
        user.setName(userDto.getFirstname() + " "+ userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = ensureRoleExists();

        user.setRoles(List.of(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private UserDTO mapToDTO(User user){
        String[] names = user.getName().split(" ");

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstname(names[0]);
        userDTO.setLastname(names[1]);

        return userDTO;
    }

    private Role ensureRoleExists(){
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = new Role();
            role.setName("ROLE_ADMIN");
            roleRepository.save(role);
        }

        return role;
    }
}
