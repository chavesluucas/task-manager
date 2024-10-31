package com.task.manager.service;

import com.task.manager.entity.UserEntity;
import com.task.manager.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter(onMethod_ = @Autowired)
public class UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    RoleService roleService;

    public UserEntity saveUser(UserEntity user) {

        user.setRoles(user.getRoles()
                .stream()
                .map(role -> roleService.findByName(role))
                .toList());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public UserEntity updateUser(UserEntity user) {
        user.setRoles(user.getRoles()
                .stream()
                .map(role -> roleService.findByName(role))
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

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

}
