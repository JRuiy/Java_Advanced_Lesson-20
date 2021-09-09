package ua.lviv.lgs.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.domain.UserRole;
import ua.lviv.lgs.repository.UserRepository;
import ua.lviv.lgs.repository.UserRoleRepository;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;
	
	@Autowired
	public CustomUserDetailsService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException("No user present with username: " + username);
		}else {
			List<String> userRoles = userRoleRepository.findRoleByUserName(username);
			return new CustomUserDetails(user, userRoles);
		}
		
	}
	
	public User addUser(User user) {
		return userRepository.save(user);
	}
	
	public UserRole addUserRole(UserRole role) {
		return userRoleRepository.save(role);
	}
	
}
