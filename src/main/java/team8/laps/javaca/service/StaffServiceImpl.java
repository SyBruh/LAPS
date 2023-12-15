package team8.laps.javaca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import team8.laps.javaca.interfacemethods.StaffService;
import team8.laps.javaca.model.Leave_Applied;
import team8.laps.javaca.repository.StaffRepository;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffRepository staffrepository;
	@Override
	@Transactional
	public List<Leave_Applied> getLeaveHistory(int id) {
		// TODO Auto-generated method stub
		return staffrepository.findLeaveByStaffId(id);
	}

}
