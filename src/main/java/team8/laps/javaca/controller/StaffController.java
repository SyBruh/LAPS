package team8.laps.javaca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import team8.laps.javaca.model.Leave_Applied;
import team8.laps.javaca.service.LeaveAppliedService;

@Controller
@RequestMapping("staff")
public class StaffController {
	@Autowired
	private LeaveAppliedService leaveAppliedService;
	
	@GetMapping("/submitLeave")
	public String submitLeaveForm(Model model) 
	{
		Leave_Applied leave_applied = new Leave_Applied();
		model.addAttribute("leaveApplied", leave_applied);
		return "submitLeave";
	}
	
	@PostMapping("/submitLeave")
	public String createSubmitLeave(@ModelAttribute("leaveApplied")Leave_Applied leave_applied){
		leaveAppliedService.createLeave(leave_applied);
		return "redirect:/staff";
	}
	
	

}
