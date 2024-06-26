package team8.laps.javaca.interfacemethods;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


import team8.laps.javaca.model.Leave_Applied;

public interface LeaveAppliedService {

	Leave_Applied getLeaveDetail(int id);
	Leave_Applied createLeave(Leave_Applied leaveApplied);
	Leave_Applied updateLeave(Leave_Applied leaveApplied);
	List<Leave_Applied> findByLeaveAppliedId(int id);
	List<Leave_Applied> findLeaveByStaffId(int staff_id);
	List<Leave_Applied> findLeaveStatus(String status);
	List<Leave_Applied> findLeaveByDateApplied(Date date_applied);
	void removeLeaveApplied(Leave_Applied leave);
	List<Leave_Applied> getLeaveApplied();
	List<Leave_Applied> getLeaveAppliedbetween(LocalDate start,LocalDate end);

}
