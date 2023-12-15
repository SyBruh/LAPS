package team8.laps.javaca.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import team8.laps.javaca.model.LeaveStatusEnum;
import team8.laps.javaca.model.Leave_Applied;
import team8.laps.javaca.service.LeaveAppliedServiceImpl;

@Controller
@RequestMapping("staff")
public class StaffController {
	@Autowired
	private LeaveAppliedServiceImpl leaveAppliedServiceImpl;
	
	/*
	 * For staff.html page. Find leave by Staff Id, Status, Date Applied
	 */
	
	//Find all leave by Staff ID
	public String findLeaveByStaffId(Model model) 
	{
		int staff_id = 0;//Get staff id from session??
		model.addAttribute("leaves",leaveAppliedServiceImpl.findLeaveByStaffId(staff_id)); 
		return "staff";
	}
		
	//Find leave by status (for manager to find leave that are still pending)
	public String findLeaveByStatus(Model model, String status) 
	{
		model.addAttribute("status", leaveAppliedServiceImpl.findLeaveStatus(status));
		return "staff";
	}
	
	//Find leave by date applied
	public String findLeaveByDateApplied(Model model, Date date_applied) 
	{
		model.addAttribute("date_applied", leaveAppliedServiceImpl.findLeaveByDateApplied(date_applied));
		return "staff";
	}
		
	/*
	 * submitLeave.html page
	 */
	
	//Bind Leave_Applied object to form model
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
		//Setting leave status using LeaveStausEnum
		leave_applied.setStatus(LeaveStatusEnum.Submitted);
		leaveAppliedServiceImpl.createLeave(leave_applied);
		return "redirect:/staff";
	}	
	
	

}
