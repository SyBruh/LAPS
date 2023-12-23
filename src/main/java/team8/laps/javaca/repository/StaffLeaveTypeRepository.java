package team8.laps.javaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import team8.laps.javaca.model.Staff_Leave_Type;

public interface StaffLeaveTypeRepository extends JpaRepository<Staff_Leave_Type, Integer>{
	//Update entitle leave
	//First Table join the designation,entitle,leave type together
	@Query("Select sl From Staff_Leave_Type sl Where sl.leavetype.id = :LeaveType_id And sl.staff.designation = :designation")
	public List<Staff_Leave_Type> updateEntitleLeave_Annual(@Param("LeaveType_id") int LeaveType_id, @Param("designation") String designation);
	
	
	
	@Query("Select s From Staff_Leave_Type s Where s.leavetype.id = :LeaveType_id")
	public List<Staff_Leave_Type>updateEntitleLeave_Medical(@Param("LeaveType_id") int LeaveType_id);
}
