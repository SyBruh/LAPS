package team8.laps.javaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import team8.laps.javaca.model.Leave_Applied;
import team8.laps.javaca.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer>{
	@Query("Select s.applications From Staff s where id = :id ")
	public List<Leave_Applied> findLeaveByStaffId(@Param("id") int id);
	
	
}
