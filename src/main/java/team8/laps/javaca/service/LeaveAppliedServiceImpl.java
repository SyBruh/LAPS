package team8.laps.javaca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
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
		return leaveAppliedRepository.getLeaveDetail(id);
	}

}
