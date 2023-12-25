package team8.laps.javaca.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Leave_Type {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int id;
	private String LeaveType;

	@OneToMany(mappedBy = "leavetype")
	@JsonIgnore
	private List<Staff_Leave_Type> staffleaves;

	@OneToMany(mappedBy = "leavetype")
	@JsonIgnore
	private List<Leave_Applied> applications;

	public Leave_Type() {
	}

	public Leave_Type(int id, String LeaveType) {
		this.id = id;
		this.LeaveType = LeaveType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLeaveType() {
		return LeaveType;
	}

	public void setLeaveType(String LeaveType) {
		this.LeaveType = LeaveType;
	}

	public List<Staff_Leave_Type> getStaffLeaves() {
		return staffleaves;
	}

	public void setStaffLeaves(List<Staff_Leave_Type> staffleaves) {
		this.staffleaves = staffleaves;
	}

	public List<Leave_Applied> getApplications() {
		return applications;
	}

	public void setApplications(List<Leave_Applied> applications) {
		this.applications = applications;
	}

}
