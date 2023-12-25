package team8.laps.javaca.model;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity

@Table(name="StaffLeaveType")
public class Staff_Leave_Type {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int leave_entitle;
	private int leave_balance;
	
	//mapping to table "Staff"
	@ManyToOne
	@JoinColumn(name="staff_id")
	@JsonIgnore
	private Staff staff;
	//mapping to table "Leave_Type"
	@ManyToOne
	@JoinColumn(name="Leave_Type_id")
	@JsonIgnore
	private Leave_Type leavetype;
	
	
	//Empty Constructor
	public Staff_Leave_Type() {
		
	}
	
	//Getter and Setter

	public Staff_Leave_Type(int leave_entitle, int leave_balance, Staff staff, Leave_Type leavetype) {
		this.leave_entitle = leave_entitle;
		this.leave_balance = leave_balance;
		this.staff = staff;
		this.leavetype = leavetype;
	}
	public int getId() {
		return id;
	}

	public int getLeave_entitle() {
		return leave_entitle;
	}
	public void setLeave_entitle(int leave_entitle) {
		this.leave_entitle = leave_entitle;
	}
	public int getLeave_balance() {
		return leave_balance;
	}
	public void setLeave_balance(int leave_balance) {
		this.leave_balance = leave_balance;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public Leave_Type getLeavetype() {
		return leavetype;
	}
	public void setLeavetype(Leave_Type leavetype) {
		this.leavetype = leavetype;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
