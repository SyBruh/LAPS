package team8.laps.javaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import team8.laps.javaca.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	
	//Find all users
	@Query("SELECT u FROM User u")
	public List<User> findAllUsers();
}
	

