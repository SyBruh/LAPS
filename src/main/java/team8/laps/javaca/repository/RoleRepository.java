package team8.laps.javaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import team8.laps.javaca.model.Role;
import team8.laps.javaca.model.User;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	//Find role by id
	@Query("SELECT r FROM Role r WHERE r.id = :id")
	public List<Role> findRoleById(@Param("id") int id);

	//Find role by role
	@Query("SELECT r FROM Role r WHERE r.role = :role")
	public List<Role> findRoleByRole(@Param("role") String role);
	
	//Find Id by role
	@Query("SELECT r.id FROM Role r WHERE r.role = :role")
	public List<Role> findIdByRole(@Param("role") String role);
	
	//Find role by Id
	@Query("SELECT r.role FROM Role r WHERE r.id = :id")
	public List<Role> findRoleIById(@Param("id") int id);
	
	//Find users by role
	@Query("SELECT r.users FROM Role r WHERE r.role = :role")
	public List<User> findUserByRole(@Param("role") String role);


}


