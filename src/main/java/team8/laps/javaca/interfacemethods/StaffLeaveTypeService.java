package team8.laps.javaca.interfacemethods;

import team8.laps.javaca.model.Leave_Type;
import team8.laps.javaca.model.Staff;
import team8.laps.javaca.model.Staff_Leave_Type;

public interface StaffLeaveTypeService {
	void updatebalance(int balance, int staffid, int leavetypeid);
	void addslt(Staff_Leave_Type slt);
	Staff_Leave_Type findleavetypebalance(Leave_Type lt, String des);
	Staff_Leave_Type findanualLeave(Staff staff);
	void updateentitle(int entitle, Leave_Type lt, String s);
}