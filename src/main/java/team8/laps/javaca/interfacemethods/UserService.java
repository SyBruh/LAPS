package team8.laps.javaca.interfacemethods;

import java.util.List;

import team8.laps.javaca.model.Role;
import team8.laps.javaca.model.User;

public interface UserService {
	User userAuthentication(String name ,String password);
	List<Role> getroles(int id);
}
