package team8.laps.javaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import team8.laps.javaca.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer>{
	@Query("SELECT s FROM Staff s LEFT JOIN s.manager WHERE s.manager.id=:managerId")
	List<Staff>findStaffByManagerId(@Param("managerId")int manager_id);
}
