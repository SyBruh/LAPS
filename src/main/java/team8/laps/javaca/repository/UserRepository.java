package team8.laps.javaca.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



import org.springframework.data.repository.query.Param;

import team8.laps.javaca.model.Role;
import team8.laps.javaca.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {

	@Query("Select u From User u Where user_name = :n And password = :p")
	public User UserAuthentication(@Param("n") String name,@Param("p") String password);
	@Query("Select u.roles From User u Where id = :id ")
	public List<Role> getUserRoles(@Param("id") int id);	
	//Find all users
	@Query("SELECT u FROM User u")
	public List<User> findAllUsers();
	
	@Query("Select DISTINCT u From User u Join u.roles r Where r.role = 'manager'")
	public List<User> findManagers();

}
	

