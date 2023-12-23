package team8.laps.javaca.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import team8.laps.javaca.interfacemethods.AnualHolidayService;
import team8.laps.javaca.model.Anual_Holiday;
import team8.laps.javaca.repository.AnualHolidayRepository;

@Service
@Transactional
public class AnualHolidayServiceImpl implements AnualHolidayService{
	
	@Autowired
	private AnualHolidayRepository anualHolidayRepository;

	@Override
	@Transactional
	public List<Anual_Holiday> getholiday(LocalDate StartDate, LocalDate EndDate) {
		// TODO Auto-generated method stub
		return anualHolidayRepository.getholiday(StartDate, EndDate);
	}

}
