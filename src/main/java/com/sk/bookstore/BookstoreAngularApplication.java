package com.sk.bookstore;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sk.bookstore.config.SecurityUtility;
import com.sk.bookstore.domain.User;
import com.sk.bookstore.domain.security.Role;
import com.sk.bookstore.domain.security.UserRole;
import com.sk.bookstore.service.UserService;

@SpringBootApplication
public class BookstoreAngularApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(BookstoreAngularApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setFirstName("Surendra");
		user1.setLastName("Kuppuraj");
		user1.setUsername("s");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("k"));
		user1.setEmail("surendra.kumar.kuppuraj@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1 = new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1, userRoles);
		
		userRoles.clear();
		
//		User user1 = new User();
//		user1.setFirstName("Surendra");
//		user1.setLastName("Kuppuraj");
//		user1.setUsername("sk");
//		user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
//		user1.setEmail("surendra.kuppuraj@gmail.com");
//		Set<UserRole> userRoles = new HashSet<>();
//		Role role1 = new Role();
//		role1.setRoleId(1);
//		role1.setName("ROLE_USER");
//		userRoles.add(new UserRole(user1, role1));
//		
//		userService.createUser(user1, userRoles);
//		
//		userRoles.clear();
//		
//		User user2 = new User();
//		user2.setFirstName("Admin");
//		user2.setLastName("Admin");
//		user2.setUsername("admin");
//		user2.setPassword(SecurityUtility.passwordEncoder().encode("p"));
//		user2.setEmail("surendra.kuppuraj@gmail.com");
//		Role role2 = new Role();
//		role2.setRoleId(0);
//		role2.setName("ROLE_ADMIN");
//		userRoles.add(new UserRole(user2, role2));
//		
//		userService.createUser(user2, userRoles);
	}
}
