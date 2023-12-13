package team8.laps.javaca.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Staff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String staff_name;
	private String designation;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Staff manager;
	
	@OneToMany(mappedBy = "staff")
	private List<Leave_Applied> applications;
	
	@OneToMany(mappedBy = "staff")
	private List<Staff_Leave_Type> staffleaves;
	
	public Staff() {
		
	}
	public Staff(String staff_name, String designation, User user, Staff manager) {
		this.staff_name = staff_name;
		this.designation = designation;
		this.user = user;
		this.manager = manager;
	}
	public int getId() {
		return id;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Staff getManager() {
		return manager;
	}
	public void setManager(Staff manager) {
		this.manager = manager;
	}
	public List<Leave_Applied> getApplications() {
		return applications;
	}
	public void setApplications(List<Leave_Applied> applications) {
		this.applications = applications;
	}
	public List<Staff_Leave_Type> getStaffleaves() {
		return staffleaves;
	}
	public void setStaffleaves(List<Staff_Leave_Type> staffleaves) {
		this.staffleaves = staffleaves;
	}
}
