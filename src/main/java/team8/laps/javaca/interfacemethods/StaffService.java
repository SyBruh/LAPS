package team8.laps.javaca.interfacemethods;

import java.util.List;

import team8.laps.javaca.model.Staff;

public interface StaffService {
	List<Staff> findStaffByManagerId(int id);

}
