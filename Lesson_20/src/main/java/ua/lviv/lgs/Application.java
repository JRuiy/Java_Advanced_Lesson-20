package ua.lviv.lgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.domain.UserRole;
import ua.lviv.lgs.security.CustomUserDetailsService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
		PasswordEncoder client = (PasswordEncoder) ctx.getBean("passwordEncoder");
		
		CustomUserDetailsService service = ctx.getBean(CustomUserDetailsService.class);
		
		
		//add user
		
		User userAdmin = new User();
		userAdmin.setUserId(Long.valueOf(0));
		userAdmin.setUserName("admin");
		userAdmin.setPassword(client.encode("Password"));
		userAdmin.setEmail("admin@gmail.com");
		userAdmin.setEnabled(1);
		
		User saved = null;
		saved = service.addUser(userAdmin);
		
		UserRole role = null;
		
		if (saved != null) {
			//add admin role to user "admin"
			role = new UserRole();
			role.setRole("ROLE_ADMIN");
			role.setUserId(saved.getUserId());
			role.setUserRoleId(Long.valueOf(0));
			
			service.addUserRole(role);
			
			//add user role to user "admin"
			role = new UserRole();
			role.setRole("ROLE_USER");
			role.setUserId(saved.getUserId());
			role.setUserRoleId(Long.valueOf(0));
			
			service.addUserRole(role);
		}
		
			//add user
			User user = new User();
			user.setUserId(Long.valueOf(0));
			user.setUserName("user");
			user.setPassword(client.encode("Password"));
			user.setEmail("user@gmail.com");
			user.setEnabled(1);
			
			saved = service.addUser(user);
			
			if (saved != null) {
				role = new UserRole();
				role.setRole("ROLE_USER");
				role.setUserId(saved.getUserId());
				role.setUserRoleId(Long.valueOf(0));
				
				service.addUserRole(role);
			}
			
	}

}
