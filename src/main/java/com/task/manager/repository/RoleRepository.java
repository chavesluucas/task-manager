package com.task.manager.repository;

import com.task.manager.entity.RoleEntity;
import com.task.manager.permissions.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>  {

    RoleEntity findByName(RoleEnum name);

}
