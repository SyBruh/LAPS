package team8.laps.javaca.repository;

import java.util.List;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import team8.laps.javaca.model.Leave_Type;
import team8.laps.javaca.model.Staff;
import team8.laps.javaca.model.Staff_Leave_Type;

public interface StaffLeaveTypeRepository extends JpaRepository<Staff_Leave_Type, Integer>{
	
	@Modifying
	@Query("Update Staff_Leave_Type slt set slt.leave_balance = :leavebalance where slt.staff.id = :staff_id AND slt.leavetype.id = :leavetypeid")
	public void UpdateLeaveBalance(@Param("leavebalance") int balance, @Param("staff_id") int staffid, @Param("leavetypeid") int leavetype);
	
	@Query("Select slt From Staff_Leave_Type slt Where leavetype = :leavetype And slt.staff.designation = :designation")
	public List<Staff_Leave_Type> getleavetype(@Param("leavetype") Leave_Type lt, @Param("designation") String st);
	
	@Query("Select slt From Staff_Leave_Type slt Where staff = :staff And leavetype.id = 2")
	public Staff_Leave_Type getanualleavetype(@Param("staff") Staff staff);
	
	@Modifying
	@Query("Update Staff_Leave_Type slt set leave_entitle = :leaveentitle where leavetype = :LeaveType And staff.designation = :designation")
	public void UpdatealAnualEntitle(@Param("leaveentitle") int entitle, @Param("LeaveType") Leave_Type lt,@Param("designation") String s);
	
	@Modifying
	@Query("Update Staff_Leave_Type slt set leave_entitle = :leaveentitle where leavetype = :LeaveType")
	public void UpdatealOtherEntitle(@Param("leaveentitle") int entitle, @Param("LeaveType") Leave_Type lt);
}
