package team8.laps.javaca.controller;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import team8.laps.javaca.interfacemethods.LeaveAppliedService;
import team8.laps.javaca.interfacemethods.LeaveTypeService;
import team8.laps.javaca.interfacemethods.StaffService;
import team8.laps.javaca.model.LeaveStatusEnum;
import team8.laps.javaca.model.Leave_Applied;
import team8.laps.javaca.service.LeaveAppliedServiceImpl;
import team8.laps.javaca.service.StaffServiceImpl;


@Controller
@RequestMapping("staff")
public class StaffController {
	
	@Autowired
	private StaffService staffservice;
	@Autowired
	private LeaveAppliedService leaveAppliedservice;
	@Autowired
	private LeaveTypeService leaveTypeService;
	
	@Autowired
	public void getservice(StaffServiceImpl staffservice, LeaveAppliedServiceImpl leaveAppliedservice, LeaveTypeService leaveTypeService) {
		this.staffservice = staffservice;
		this.leaveAppliedservice = leaveAppliedservice;
		this.leaveTypeService = leaveTypeService;
	}
	//personal leave history is (/viewLeaveHistory)
	@GetMapping("/viewLeaveHistory/{staff_id}")// will change to HttpSession
	public String ViewLeaveHistory(Model model, @PathVariable("staff_id") int id) {
		model.addAttribute("leaveList", staffservice.getLeaveHistory(id));
		return "PersonalLeaveHistory";
	}
	//Leave Detail is (/viewDetail)
	@GetMapping("/viewDetail/{la_id}")
	public String ViewDetail(Model model, @PathVariable("la_id") int id){		
		model.addAttribute("Leave", leaveAppliedservice.getLeaveDetail(id));
		return "leaveDetail";
	}
	//submit leave is (/submitLeave)
	//If need more controller map it accordingly	
	/*
	 * For staff.html page. Find leave by Staff Id, Status, Date Applied
	 */
	
	//Find all leave by Staff ID
	public String findLeaveByStaffId(Model model) 
	{
		int staff_id = 0;//Get staff id from session??
		model.addAttribute("leaves",leaveAppliedservice.findLeaveByStaffId(staff_id)); 
		return "staff";
	}
		
	//Find leave by status (for manager to find leave that are still pending)
	public String findLeaveByStatus(Model model, String status) 
	{
		model.addAttribute("status", leaveAppliedservice.findLeaveStatus(status));
		return "staff";
	}
	
	//Find leave by date applied
	public String findLeaveByDateApplied(Model model, Date date_applied) 
	{
		model.addAttribute("date_applied", leaveAppliedservice.findLeaveByDateApplied(date_applied));
		return "staff";
	}
		
	/*
	 * submitLeave.html page
	 */
	
	//Bind Leave_Applied object to form model
	@GetMapping("/submitLeave")
	public String submitLeaveForm(Model model, HttpSession sessionObj) 
	{
		//Bind username to model
		String username = (String)sessionObj.getAttribute("username");
		model.addAttribute("username", username);
		
		//Bind Leave Applied object to form model
		Leave_Applied leave_applied = new Leave_Applied();
		model.addAttribute("leaveApplied", leave_applied);		
		
		//Bind Leave types for drop down list
		model.addAttribute("leave_types", leaveTypeService.getAllLeaveType());
		return "submitLeave";
	}
	
	//POST submit leave
	@PostMapping("/submitLeave")
	public String createSubmitLeave(@ModelAttribute("leaveApplied")Leave_Applied leave_applied, HttpSession sessionObj){
		//Setting user id
		int staff_id = (int) sessionObj.getAttribute("staffid");
		leave_applied.setStaffId(staff_id);
		
		//Setting leave status 
		leave_applied.setStatus(LeaveStatusEnum.Applied);
		
		//Set current date
		LocalDate localdate = LocalDate.now();
		leave_applied.setDate_applied(localdate);
		
		leaveAppliedservice.createLeave(leave_applied);
		return "staff";
	}	
	
}
