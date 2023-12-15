package team8.laps.javaca.interfacemethods;

import org.springframework.data.repository.query.Param;

import team8.laps.javaca.model.Leave_Applied;

public interface LeaveAppliedService {
	Leave_Applied getLeaveDetail(int id);
}
