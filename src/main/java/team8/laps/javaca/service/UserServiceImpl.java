package team8.laps.javaca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import team8.laps.javaca.interfacemethods.UserService;
import team8.laps.javaca.model.User;
import team8.laps.javaca.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	//Find All users
	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
	
	//Update user
	@Override
	@Transactional
	public User updateUser(User user) {
		return userRepository.save(user);
	}

}
