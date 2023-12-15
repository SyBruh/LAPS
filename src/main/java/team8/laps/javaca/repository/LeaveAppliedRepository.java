package team8.laps.javaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import team8.laps.javaca.model.Leave_Applied;

public interface LeaveAppliedRepository extends JpaRepository<Leave_Applied, Integer>{
	
	@Query("Select la From Leave_Applied la Where id = :id")
	public Leave_Applied getLeaveDetail(@Param("id") int id);

}
