package team8.laps.javaca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import team8.laps.javaca.interfacemethods.LeaveTypeService;
import team8.laps.javaca.model.Leave_Type;
import team8.laps.javaca.repository.LeaveTypeRepository;

@Service
@Transactional
public class LeaveTypeServiceImpl implements LeaveTypeService{

	@Autowired
	private LeaveTypeRepository leaveTypeRepository;
	
	@Override
	public List<Leave_Type> getAllLeaveType() {
		return leaveTypeRepository.findAll();
	}

	@Override
	public Leave_Type getleavetypebyID(int id) {
		// TODO Auto-generated method stub
		return leaveTypeRepository.findById(id).get();
	}

}
