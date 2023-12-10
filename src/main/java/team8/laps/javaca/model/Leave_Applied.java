package team8.laps.javaca.model;

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
	
	@Column(name="leave_type_id")//This one is not needed, since jpa will create one on it's own
	private int leave_type_id;//Same
	
	@Column(name="leave_start")
	private Date leave_start;
	
	@Column(name="leave_end")
	private Date leave_end;
	
	@Column(name="date_applied")
	private Date date_applied;
	
	@Column(name="staff_id")//The same goes for this one as well
	private int staff_id;//Same
	
	@Column(name="status")
	private String status;
	
	@Column(name="comment")
	private String comment;
	
	//Mappings
	@ManyToOne
	@JoinColumn(name="id")//this one should be other names, id might cause some errors with the PK
	//preferable name are the combination of entity name and primary key(eg.leave_type_id)
	private Leave_Type leave_type;
	
	@ManyToOne 
	@JoinColumn(name="id")//the same goes for this one as well
	private Staff staff;
	
	//Constructors
	public Leave_Applied() {}
	
	public Leave_Applied(int id, int leave_type_id, Date leave_start, Date leave_end, Date date_applied, int staff_id,
			String status, String comment) {//change some arguments ids are not necessary since we will auto-gen them
		super();
		this.id = id;
		this.leave_type_id = leave_type_id;//Instead of this(this.leave_type = leave_type)
		this.leave_start = leave_start;
		this.leave_end = leave_end;
		this.date_applied = date_applied;
		this.staff_id = staff_id;//Instead of this(this.staff = staff)
		this.status = status;
		this.comment = comment;
	}

	//Getters and setters
	public int getId() {
		return id;
	}
	
	public int getLeave_type_id() {
		return leave_type_id;
	}
	public void setLeave_type_id(int leave_type_id) {
		this.leave_type_id = leave_type_id;
	}
	
	public Date getLeave_start() {
		return leave_start;
	}
	public void setLeave_start(Date leave_start) {
		this.leave_start = leave_start;
	}
	
	public Date getLeave_end() {
		return leave_end;
	}
	public void setLeave_end(Date leave_end) {
		this.leave_end = leave_end;
	}
	
	public Date getDate_applied() {
		return date_applied;
	}
	public void setDate_applied(Date date_applied) {
		this.date_applied = date_applied;
	}
	
	public int getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}
	
	private String getStatus() {//public
		return status;
	}
	private void setStatus(String status) {//public
		this.status = status;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}


	

	
}
