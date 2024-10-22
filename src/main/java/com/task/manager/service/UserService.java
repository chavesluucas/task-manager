package com.task.manager.service;

import com.task.manager.entity.UserEntity;
import com.task.manager.repository.RoleRepository;
import com.task.manager.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public UserEntity saveUser(UserEntity user) {

        user.setRoles(user.getRoles()
                .stream()
                .map(role -> roleRepository.findByName(role.getName()))
                .toList());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public UserEntity updateUser(UserEntity user) {
        user.setRoles(user.getRoles()
                .stream()
                .map(role -> roleRepository.findByName(role.getName()))
                .toList());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }

    public List<UserEntity> getAll() {
        return this.userRepository.findAll();
    }

}
