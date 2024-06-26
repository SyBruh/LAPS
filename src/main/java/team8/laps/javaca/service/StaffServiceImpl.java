package team8.laps.javaca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team8.laps.javaca.model.Leave_Applied;

import org.springframework.transaction.annotation.Transactional;

//import org.springframework.transaction.annotation.Transactional;


import team8.laps.javaca.interfacemethods.StaffService;
import team8.laps.javaca.model.Staff;
import team8.laps.javaca.repository.StaffRepository;

@Service
@Transactional
public class StaffServiceImpl implements StaffService{

  @Autowired
	private StaffRepository staffrepository;

	
	@Override
	@Transactional
	public List<Staff> findStaffByManagerId(int id) {
		
		return staffrepository.findStaffByManagerId(id);
	}
		
	@Override
	@Transactional
	public List<Leave_Applied> getLeaveHistory(int id) {
		// TODO Auto-generated method stub
		return staffrepository.findLeaveByStaffId(id);
	}

	@Override
	public void saveStaff(Staff staff) {
		// TODO Auto-generated method stub
		staffrepository.save(staff);
	}

	@Override
	public List<Staff> getallStaff() {
		// TODO Auto-generated method stub
		return staffrepository.findAll();
	}

	@Override
	public Staff findstaffbyID(int id) {
		// TODO Auto-generated method stub
		return staffrepository.findById(id).get();
	}

	@Override
	public void deletestaff(int id) {
		// TODO Auto-generated method stub
		staffrepository.deleteById(id);
	}

}
