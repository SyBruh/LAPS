package team8.laps.javaca.interfacemethods;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.query.Param;

import team8.laps.javaca.model.Anual_Holiday;

public interface AnualHolidayService {
	Anual_Holiday createAnualHoliday(Anual_Holiday holiday);
	Anual_Holiday updateAnualHoliday(Anual_Holiday holiday);
	void deleteAnualHolidaybyId(int id);
	public void deleteHolidayById(int id);
	
	public List<Anual_Holiday> findAllHoliday();
	public List<Anual_Holiday> findHolidayByName(String name);
	public List<Anual_Holiday> findHolidayByDescription(String description);
	public List<Anual_Holiday> findHolidayByStartDate(LocalDate startdate);
	public List<Anual_Holiday> findHolidayByEndDate(LocalDate enddate);
	public Anual_Holiday findHolidayById(int id);
}
