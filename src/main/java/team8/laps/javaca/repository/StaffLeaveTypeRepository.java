package team8.laps.javaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import team8.laps.javaca.model.Leave_Type;
import team8.laps.javaca.model.Staff_Leave_Type;

public interface StaffLeaveTypeRepository extends JpaRepository<Staff_Leave_Type, Integer>{
	
	@Modifying
	@Query("Update Staff_Leave_Type slt set slt.leave_balance = :leavebalance where slt.staff.id = :staff_id AND slt.leavetype.id = :leavetypeid")
	public void UpdateLeaveBalance(@Param("leavebalance") int balance, @Param("staff_id") int staffid, @Param("leavetypeid") int leavetype);
	
	@Query("Select slt From Staff_Leave_Type slt Where leavetype = :leavetype")
	public List<Staff_Leave_Type> getleavetype(@Param("leavetype") Leave_Type lt);
}
