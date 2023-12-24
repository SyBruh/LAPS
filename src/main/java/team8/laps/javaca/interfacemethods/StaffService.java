package team8.laps.javaca.interfacemethods;

import java.util.List;


import team8.laps.javaca.model.Staff;
import team8.laps.javaca.model.Leave_Applied;
public interface StaffService {
	List<Staff> findStaffByManagerId(int id);
  List<Leave_Applied> getLeaveHistory(int id);
  void saveStaff(Staff staff);
	List<Staff> getallStaff();
	Staff findstaffbyID(int id);
	void deletestaff(int id);
}
