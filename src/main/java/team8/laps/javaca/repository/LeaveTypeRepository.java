package team8.laps.javaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import team8.laps.javaca.model.Leave_Type;

public interface LeaveTypeRepository extends JpaRepository<Leave_Type,Integer>{

}
