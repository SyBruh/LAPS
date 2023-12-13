package team8.laps.javaca.interfacemethods;
import java.util.Date;
import java.util.List;

import team8.laps.javaca.model.Leave_Applied;

public interface LeaveAppliedService {
	Leave_Applied createLeave(Leave_Applied leaveApplied);
	List<Leave_Applied> findByLeaveAppliedId(int id);
	List<Leave_Applied> findLeaveByStaffId(int staff_id);
	List<Leave_Applied> findLeaveStatus(String status);
	List<Leave_Applied> findLeaveByDateApplied(Date date_applied);
	void removeLeaveApplied(Leave_Applied leave);	
}
