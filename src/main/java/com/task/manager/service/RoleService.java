package com.task.manager.service;

import com.task.manager.entity.RoleEntity;
import com.task.manager.entity.UserEntity;
import com.task.manager.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Setter(onMethod_ = @Autowired)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleService {

    RoleRepository roleRepository;

    public RoleEntity findByName(RoleEntity role) {
        return roleRepository.findByName(role.getName());
    }

}
