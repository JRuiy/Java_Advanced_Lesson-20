package ua.lviv.lgs.repository;

import org.springframework.data.repository.CrudRepository;

import ua.lviv.lgs.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{

	User findByUserName(String userName);
	
}
