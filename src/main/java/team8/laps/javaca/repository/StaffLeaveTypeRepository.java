package team8.laps.javaca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import team8.laps.javaca.model.Staff_Leave_Type;

public interface StaffLeaveTypeRepository extends JpaRepository<Staff_Leave_Type, Integer>{
	
	@Modifying
	@Query("Update Staff_Leave_Type slt set slt.leave_balance = :leavebalance where slt.staff.id = :staff_id AND slt.leavetype.LeaveType = :leavetype")
	public void UpdateLeaveBalance(@Param("leavebalance") int balance, @Param("staff_id") int staffid, @Param("leavetype") String leavetype);
}
