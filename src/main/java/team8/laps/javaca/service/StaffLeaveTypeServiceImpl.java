package team8.laps.javaca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import team8.laps.javaca.interfacemethods.StaffLeaveTypeService;
import team8.laps.javaca.model.Leave_Type;
import team8.laps.javaca.model.Staff;
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
	public Staff_Leave_Type findleavetypebalance(Leave_Type lt, String des) {
		// TODO Auto-generated method stub
		if(staffLeaveTypeRepository.getleavetype(lt,des) == null) {
			return null;
		}
		return staffLeaveTypeRepository.getleavetype(lt,des).get(0);
	}
	@Override
	public Staff_Leave_Type findanualLeave(Staff staff) {
		// TODO Auto-generated method stub
		return staffLeaveTypeRepository.getanualleavetype(staff);
	}
	@Override
	public void updateentitle(int entitle, Leave_Type lt, String s) {
		if(lt.getLeaveType().equalsIgnoreCase("Anual Leave")) {
			staffLeaveTypeRepository.UpdatealAnualEntitle(entitle, lt, s);
		}else {
			staffLeaveTypeRepository.UpdatealOtherEntitle(entitle, lt);
		}
		
	}

}
