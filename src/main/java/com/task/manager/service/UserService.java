package com.task.manager.service;

import com.task.manager.entity.User;
import com.task.manager.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    public User updateUser(User user) {
        if (user != null) {
            return this.userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }

    public List<User> getAll() {
        return this.userRepository.findAll();
    }

}
