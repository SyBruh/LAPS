package team8.laps.javaca.interfacemethods;

import java.util.List;

import team8.laps.javaca.model.Role;
import team8.laps.javaca.model.User;

public interface RoleService {

	List<Role> findRoleById(int id);
	List<Role> findRoleByRole(String role);
	List<Role> findIdByRole(String role);
	List<Role> findRoleIById(int id);
	void removeRole(Role role);
	List<Role> findallroles();
}
