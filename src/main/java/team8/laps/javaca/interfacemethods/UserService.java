package team8.laps.javaca.interfacemethods;

import java.util.List;

import team8.laps.javaca.model.User;

public interface UserService {
	public List<User> findAllUsers();
	User updateUser(User user);
}
