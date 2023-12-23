package team8.laps.javaca.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import team8.laps.javaca.interfacemethods.LeaveAppliedService;
import team8.laps.javaca.model.Leave_Applied;
import team8.laps.javaca.repository.LeaveAppliedRepository;


@Service
@Transactional
public class LeaveAppliedServiceImpl implements LeaveAppliedService{
	
	@Autowired
	private LeaveAppliedRepository leaveAppliedRepository;

	@Override
	@Transactional
	public Leave_Applied getLeaveDetail(int id) {
		// TODO Auto-generated method stub
		return leaveAppliedRepository.findById(id).get();
	}
	
	//Create Leave
	@Override
	@Transactional
	public Leave_Applied createLeave(Leave_Applied leaveApplied) {
		return leaveAppliedRepository.save(leaveApplied);
	}	
	
	//Find leave by leave applied Id
	@Override
	public List<Leave_Applied> findByLeaveAppliedId(int id)
	{
		return leaveAppliedRepository.findByLeaveAppliedId(id);
	}
	
	//Find leave by Staff Id
	@Override
	public List<Leave_Applied> findLeaveByStaffId(int staff_id)
	{
		return leaveAppliedRepository.findLeaveByStaffId(staff_id);
	}
	
	//Find leave by Status
	@Override
	public List<Leave_Applied> findLeaveStatus(String status)
	{
		return leaveAppliedRepository.findLeaveStatus(status);
	}
	
	//Find leave by date applied
	@Override
	public List<Leave_Applied> findLeaveByDateApplied(Date date_applied)
	{
		return leaveAppliedRepository.findLeaveByDateApplied(date_applied);
	}
	
	//Remove leave applied
	@Override
	@Transactional
	public void removeLeaveApplied(Leave_Applied leave) 
	{
		leaveAppliedRepository.delete(leave);
	}

	@Override
	@Transactional
	public Leave_Applied updateLeave(Leave_Applied leaveApplied) {
		// TODO Auto-generated method stub
		Optional<Leave_Applied> ola = leaveAppliedRepository.findById(leaveApplied.getId());
		if(ola.isPresent()) {
			Leave_Applied la = ola.get();
			la.setComment(leaveApplied.getComment());
			la.setDate_applied(leaveApplied.getDate_applied());
			la.setLeave_end(leaveApplied.getLeave_end());
			la.setLeave_start(leaveApplied.getLeave_start());
			la.setLeavetype(leaveApplied.getLeavetype());;
			la.setStatus(leaveApplied.getStatus());
			la.setLeavecount(leaveApplied.getLeavecount());
			leaveAppliedRepository.save(la);
		}		
		return ola.get();
	}
	
}
