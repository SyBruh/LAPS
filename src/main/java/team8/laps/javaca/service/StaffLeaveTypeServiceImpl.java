package team8.laps.javaca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import team8.laps.javaca.interfacemethods.StaffLeaveTypeService;
import team8.laps.javaca.repository.StaffLeaveTypeRepository;

@Service
@Transactional
public class StaffLeaveTypeServiceImpl implements StaffLeaveTypeService{

	@Autowired
	private StaffLeaveTypeRepository staffLeaveTypeRepository;
	@Override
	@Transactional
	public void updatebalance(int balance, int staffid, String leavetype) {
		// TODO Auto-generated method stub
		staffLeaveTypeRepository.UpdateLeaveBalance(balance, staffid, leavetype);
	}

}
