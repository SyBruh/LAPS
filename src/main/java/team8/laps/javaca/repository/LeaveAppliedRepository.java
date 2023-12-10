package team8.laps.javaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import team8.laps.javaca.model.Leave_Applied;

public interface LeaveAppliedRepository extends JpaRepository<Leave_Applied, Integer>{
	public List<Leave_Applied> findByLeaveAppliedId(int id);
	public List<Leave_Applied> findByStaffId(int staff_id);

}
