package team8.laps.javaca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team8.laps.javaca.interfacemethods.StaffService;
import team8.laps.javaca.model.Staff;
import team8.laps.javaca.repository.StaffRepository;

@Service
@Transactional(readOnly=true)
public class StaffServiceImpl implements StaffService{

	@Autowired
	private StaffRepository staffRepo;
	
	@Override
	public List<Staff> findStaffByManagerId(int id) {
		
		return staffRepo.findStaffByManagerId(id);
	}
	

}
