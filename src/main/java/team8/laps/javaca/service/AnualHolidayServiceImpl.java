package team8.laps.javaca.service;

import java.time.LocalDate;
import java.util.List;

import java.util.Optional;


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

	public Anual_Holiday createAnualHoliday(Anual_Holiday holiday) {
		return anualHolidayRepository.save(holiday);
	}

	@Override
	public Anual_Holiday updateAnualHoliday(Anual_Holiday holiday) {
		Optional<Anual_Holiday> getHoliday = anualHolidayRepository.findById(holiday.getId());
		if(getHoliday.isPresent()) {
			Anual_Holiday updateHoliday = getHoliday.get();
			updateHoliday.setDescription(holiday.getDescription());
			updateHoliday.setStartDate(holiday.getStartDate());
			updateHoliday.setEndDate(holiday.getEndDate());
			updateHoliday.setName(holiday.getName());
			updateHoliday.setDescription(holiday.getDescription());
			anualHolidayRepository.save(updateHoliday);
		}
		return getHoliday.get();
	}
	
	
	@Override
	public void deleteAnualHolidaybyId(int id) {
		anualHolidayRepository.deleteById(id);
	}

	@Override
	public List<Anual_Holiday> findAllHoliday() {
		return anualHolidayRepository.findAll();
	}

	@Override
	public List<Anual_Holiday> findHolidayByName(String name) {
		return anualHolidayRepository.findHolidayByName(name);
	}

	@Override
	public List<Anual_Holiday> findHolidayByDescription(String description) {
		return anualHolidayRepository.findHolidayByDescription(description);
	}

	@Override
	public List<Anual_Holiday> findHolidayByStartDate(LocalDate startdate) {
		return anualHolidayRepository.findHolidayByStartDate(startdate);
	}

	@Override
	public List<Anual_Holiday> findHolidayByEndDate(LocalDate enddate) {
		return anualHolidayRepository.findHolidayByEndDate(enddate);
	}

	@Override
	public Anual_Holiday findHolidayById(int id) {
		return anualHolidayRepository.findHolidayById(id);
	}

	@Transactional
	@Override
	public void deleteHolidayById(int id) {
		anualHolidayRepository.deleteHolidayById(id);		
	}

	@Override
	public boolean checkHoliday(LocalDate date) {
		// TODO Auto-generated method stub
		if(anualHolidayRepository.checkholiday(date) != null) {
			return false;
		}
		return true;
	}
	
}
