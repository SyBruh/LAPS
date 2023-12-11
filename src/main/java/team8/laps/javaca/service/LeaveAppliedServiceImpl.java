package team8.laps.javaca.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team8.laps.javaca.model.Leave_Applied;
import team8.laps.javaca.repository.LeaveAppliedRepository;


@Service
@Transactional(readOnly=true)
public class LeaveAppliedServiceImpl {
	@Autowired
	private LeaveAppliedRepository leaveAppliedRespository;
	
	@Transactional(readOnly=false)
	Leave_Applied createLeave(Leave_Applied leaveApplied) {
		return leaveAppliedRespository.save(leaveApplied);
	}
	
}
