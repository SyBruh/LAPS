package team8.laps.javaca.service;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team8.laps.javaca.model.Leave_Applied;
import team8.laps.javaca.repository.LeaveAppliedRepository;


@Service
@Transactional(readOnly=true)
public class LeaveAppliedServiceImpl implements LeaveAppliedService{
	@Autowired
	private LeaveAppliedRepository leaveAppliedRespository;
	
	//Create Leave
	@Override
	@Transactional(readOnly=false)
	public Leave_Applied createLeave(Leave_Applied leaveApplied) {
		return leaveAppliedRespository.save(leaveApplied);
	}	
	
	//Find leave by leave applied Id
	@Override
	public List<Leave_Applied> findByLeaveAppliedId(int id)
	{
		return leaveAppliedRespository.findByLeaveAppliedId(id);
	}
	
	//Find leave by Staff Id
	@Override
	public List<Leave_Applied> findLeaveByStaffId(int staff_id)
	{
		return leaveAppliedRespository.findLeaveByStaffId(staff_id);
	}
	
	//Find leave by Status
	@Override
	public List<Leave_Applied> findLeaveStatus(String status)
	{
		return leaveAppliedRespository.findLeaveStatus(status);
	}
	
	//Find leave by date applied
	@Override
	public List<Leave_Applied> findLeaveByDateApplied(Date date_applied)
	{
		return leaveAppliedRespository.findLeaveByDateApplied(date_applied);
	}
	
	//Remove leave applied
	@Override
	@Transactional
	public void removeLeaveApplied(Leave_Applied leave) 
	{
		leaveAppliedRespository.delete(leave);
	}
	
}
