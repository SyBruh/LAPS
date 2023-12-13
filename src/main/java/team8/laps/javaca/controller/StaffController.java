package team8.laps.javaca.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import team8.laps.javaca.model.Leave_Applied;
import team8.laps.javaca.service.LeaveAppliedService;

@Controller
@RequestMapping("staff")
public class StaffController {
	@Autowired
	private LeaveAppliedService leaveAppliedService;
	
	/*
	 * For staff.html page. Find leave by Staff Id, Status, Date Applied
	 */
	
	//Find all leave by Staff ID
	public String findLeaveByStaffId(Model model) 
	{
		int staff_id = 0;//Get staff id from session??
		model.addAttribute("leaves",leaveAppliedService.findLeaveByStaffId(staff_id)); 
		return "staff";
	}
		
	//Find leave by status (for manager to find leave that are still pending)
	public String findLeaveByStatus(Model model, String status) 
	{
		model.addAttribute("status", leaveAppliedService.findLeaveStatus(status));
		return "staff";
	}
	
	//Find leave by date applied
	public String findLeaveByDateApplied(Model model, Date date_applied) 
	{
		model.addAttribute("date_applied", leaveAppliedService.findLeaveByDateApplied(date_applied));
		return "staff";
	}
		
	/*
	 * submitLeave.html page
	 */
	
	//Bind submit leave form model
	@GetMapping("/submitLeave")
	public String submitLeaveForm(Model model) 
	{
		Leave_Applied leave_applied = new Leave_Applied();
		model.addAttribute("leaveApplied", leave_applied);
		return "submitLeave";
	}
	
	//POST submit leave
	@PostMapping("/submitLeave")
	public String createSubmitLeave(@ModelAttribute("leaveApplied")Leave_Applied leave_applied){
		leaveAppliedService.createLeave(leave_applied);
		return "redirect:/staff";
	}	
	
	

}
