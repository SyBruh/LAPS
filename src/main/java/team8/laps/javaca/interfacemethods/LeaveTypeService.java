package team8.laps.javaca.interfacemethods;

import java.util.List;

import team8.laps.javaca.model.Leave_Type;

public interface LeaveTypeService {
	public List<Leave_Type> getAllLeaveType();
	Leave_Type getleavetypebyID(int id);
}
