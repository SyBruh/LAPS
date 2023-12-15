package team8.laps.javaca.interfacemethods;

import java.util.List;

import team8.laps.javaca.model.Leave_Applied;

public interface StaffService {
	List<Leave_Applied> getLeaveHistory(int id);
}
