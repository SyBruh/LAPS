package team8.laps.javaca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import team8.laps.javaca.interfacemethods.StaffLeaveTypeService;
import team8.laps.javaca.model.Leave_Type;
import team8.laps.javaca.model.Staff_Leave_Type;
import team8.laps.javaca.repository.StaffLeaveTypeRepository;

@Service
@Transactional
public class StaffLeaveTypeServiceImpl implements StaffLeaveTypeService{

	@Autowired
	private StaffLeaveTypeRepository staffLeaveTypeRepository;
	@Override
	@Transactional
	public void updatebalance(int balance, int staffid, int leavetypeid) {
		// TODO Auto-generated method stub
		staffLeaveTypeRepository.UpdateLeaveBalance(balance, staffid, leavetypeid);
	}
	@Override
	public void addslt(Staff_Leave_Type slt) {
		// TODO Auto-generated method stub
		staffLeaveTypeRepository.save(slt);
	}
	@Override
	public Staff_Leave_Type findleavetypebalance(Leave_Type lt) {
		// TODO Auto-generated method stub
		return staffLeaveTypeRepository.getleavetype(lt).get(0);
	}

}
