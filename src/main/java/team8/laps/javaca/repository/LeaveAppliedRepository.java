package team8.laps.javaca.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import team8.laps.javaca.model.Leave_Applied;

public interface LeaveAppliedRepository extends JpaRepository<Leave_Applied, Integer>{
	
	//Find leave by Leave Applied Id
	@Query("SELECT la FROM Leave_Applied la WHERE la.id = :id")
	public List<Leave_Applied> findByLeaveAppliedId(@Param("id")int id);
	
	//Find leave by Staff Id
	@Query("SELECT la FROM Leave_Applied la WHERE la.staff_id = :staff_id")
	public List<Leave_Applied> findLeaveByStaffId(@Param("staff_id")int staff_id);
	
	//Find leave by Status
	@Query("SELECT la FROM Leave_Applied la WHERE status = :status")
	public List<Leave_Applied> findLeaveStatus(@Param("status")String status);
	
	//Find leave by Date Applied
	@Query("SELECT la FROM Leave_Applied la WHERE la.date_applied = :date_applied")
	public List<Leave_Applied> findLeaveByDateApplied(@Param("date_applied")Date date_applied);
}
