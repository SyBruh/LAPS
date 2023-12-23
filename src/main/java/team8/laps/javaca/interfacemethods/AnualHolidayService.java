package team8.laps.javaca.interfacemethods;

import java.time.LocalDate;
import java.util.List;

import team8.laps.javaca.model.Anual_Holiday;

public interface AnualHolidayService {

	List<Anual_Holiday> getholiday(LocalDate StartDate,LocalDate EndDate);
}
