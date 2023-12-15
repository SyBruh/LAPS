package team8.laps.javaca.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Anual_Holiday {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDateTime StartDate;
	private LocalDateTime EndDate;
	private String Description;
	private String name;
	public Anual_Holiday() {
		
	}
	public Anual_Holiday(LocalDateTime StartDate,LocalDateTime EndDate, String Description,String name) {
		this.StartDate = StartDate;
		this.EndDate = EndDate;
		this.Description = Description;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public LocalDateTime getStartDate() {
		return StartDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		StartDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return EndDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		EndDate = endDate;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
