package com.task.manager;

import com.task.manager.entity.RoleEntity;
import com.task.manager.entity.UserEntity;
import com.task.manager.permissions.RoleEnum;
import com.task.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TaskManagerApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		UserEntity user = createUserAndSetRoles();

		userService.saveUser(user);
	}

	private static UserEntity createUserAndSetRoles() {
		UserEntity user = new UserEntity();
		user.setUsername("admin");
		user.setPassword("123456");

		List<RoleEntity> roles = new ArrayList<>();

		RoleEntity roleAdmin = new RoleEntity();
		roleAdmin.setName(RoleEnum.ADMIN);

		RoleEntity roleUsuario = new RoleEntity();
		roleUsuario.setName(RoleEnum.USER);

		roles.add(roleAdmin);
		roles.add(roleUsuario);

		user.setRoles(roles);

		return user;
	}
}
