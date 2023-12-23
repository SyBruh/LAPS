package team8.laps.javaca.service;

import java.util.List;

import team8.laps.javaca.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import team8.laps.javaca.interfacemethods.RoleService;
import team8.laps.javaca.repository.RoleRepository;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> findRoleById(int id) {
		return roleRepository.findRoleById(id);
	}

	@Override
	public List<Role> findRoleByRole(String role) {
		return roleRepository.findRoleByRole(role);
	}

	@Override
	public List<Role> findIdByRole(String role) {
		return roleRepository.findIdByRole(role);
	}

	@Override
	public List<Role> findRoleIById(int id) {
		return roleRepository.findRoleById(id);
	}

	@Override
	public void removeRole(Role role) {
		roleRepository.delete(role);		
	}


	



}
