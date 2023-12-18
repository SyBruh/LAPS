package team8.laps.javaca.model;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Leave_Applied")
public class Leave_Applied {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;	

	@Column(name="leave_start")
	private LocalDate leave_start;
	
	@Column(name="leave_end")
	private LocalDate leave_end;
	
	@Column(name="date_applied")
	private LocalDate date_applied;
	
	@Column(name="status")
	private LeaveStatusEnum status;
	
	@Column(name="comment")
	private String comment;
	
	//Mappings
	@ManyToOne 
	@JoinColumn(name="leave_type_id",  insertable = false, updatable = false)
	private Leave_Type leavetype;
	
	@Column(name="leave_type_id")
	private Integer leavetype_id;
	
	@ManyToOne 
	@JoinColumn(name="staff_id",  insertable = false, updatable = false)
	private Staff staff;
	
	@Column(name="staff_id")
	private int staffId;
	
	//Constructors
	public Leave_Applied() {}
	

	public Leave_Applied(int id,LocalDate leave_start, LocalDate leave_end, LocalDate date_applied,
			LeaveStatusEnum status, String comment) {
		super();
		this.id = id;
		this.leave_start = leave_start;
		this.leave_end = leave_end;
		this.date_applied = date_applied;
		this.status = status;
		this.comment = comment;		
	}

	//Getters and setters
	public int getId() {
		return id;
	}

	public LocalDate getLeave_start() {
		return leave_start;
	}
	public void setLeave_start(LocalDate leave_start) {
		this.leave_start = leave_start;
	}
	
	public LocalDate getLeave_end() {
		return leave_end;
	}
	public void setLeave_end(LocalDate leave_end) {
		this.leave_end = leave_end;
	}
	
	public LocalDate getDate_applied() {
		return date_applied;
	}
	public void setDate_applied(LocalDate date_applied) {
		this.date_applied = date_applied;
	}
	
	public LeaveStatusEnum getStatus() {
		return status;
	}

	public void setStatus(LeaveStatusEnum status) {
		this.status = status;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;

	}	

	public Leave_Type getLeavetype() {
		return leavetype;
	}

	public void setLeavetype(Leave_Type leave_type) {
		this.leavetype = leave_type;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}


	public int getStaffId() {
		return staffId;
	}


	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}


	public Integer getLeave_type_id() {
		return leavetype_id;
	}


	public void setLeave_type_id(Integer leavetype_id) {
		this.leavetype_id = leavetype_id;
	}
	


}
