package team8.laps.javaca.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import team8.laps.javaca.model.Anual_Holiday;

public interface AnualHolidayRepository extends JpaRepository<Anual_Holiday,Integer> {
	
	@Query("Select ah From Anual_Holiday ah Where ah.StartDate > :StartDate And ah.EndDate < :EndDate")
	public List<Anual_Holiday> getholiday(@Param("StartDate") LocalDate StartDate, @Param("EndDate") LocalDate EndDate);
}
