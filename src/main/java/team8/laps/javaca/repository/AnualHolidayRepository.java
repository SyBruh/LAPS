package team8.laps.javaca.repository;

import java.time.LocalDate;
import java.util.List;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import team8.laps.javaca.model.Anual_Holiday;

public interface AnualHolidayRepository extends JpaRepository<Anual_Holiday,Integer> {
	
	@Query("Select ah From Anual_Holiday ah Where ah.StartDate > :StartDate And ah.EndDate < :EndDate")
	public List<Anual_Holiday> getholiday(@Param("StartDate") LocalDate StartDate, @Param("EndDate") LocalDate EndDate);

	@Query("Select ah From Anual_Holiday ah Where ah.StartDate <= :Date And ah.EndDate >= :Date")
	public List<Anual_Holiday> checkholiday(@Param("Date") LocalDate date);
	//Find All Holiday
	@Query("SELECT ah FROM Anual_Holiday ah")
	public List<Anual_Holiday> findAllHoliday();
	
	//Find Holiday by name
	@Query("SELECT ah FROM Anual_Holiday ah WHERE ah.name LIKE %:name%")
	public List<Anual_Holiday> findHolidayByName(@Param("name") String name);
	
	//Find Holiday by description
	@Query("SELECT ah FROM Anual_Holiday ah WHERE ah.Description LIKE %:description%")
	public List<Anual_Holiday> findHolidayByDescription(@Param("description") String description);
	
	//Find Holiday By StartDate
	@Query("SELECT ah FROM Anual_Holiday ah WHERE ah.StartDate = :startdate")
	public List<Anual_Holiday> findHolidayByStartDate(@Param("startdate")LocalDate startdate);

	//Find Holiday By EndDate
	@Query("SELECT ah FROM Anual_Holiday ah WHERE ah.EndDate = :enddate")
	public List<Anual_Holiday> findHolidayByEndDate(@Param("enddate")LocalDate enddate);
	
	//Find Holiday by Id
	@Query("SELECT ah FROM Anual_Holiday ah WHERE ah.id = :id")
	public Anual_Holiday findHolidayById(@Param("id")int id);
	
	//Delete Holiday by Id
	@Modifying
	@Query("DELETE FROM Anual_Holiday ah WHERE ah.id = :id")
	public void deleteHolidayById(int id);
	

}
