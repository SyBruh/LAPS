package team8.laps.javaca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import team8.laps.javaca.model.Leave_Applied;

@Controller
@RequestMapping("staff")
public class StaffController {
	@GetMapping("/submitLeave")
	public String submitLeaveForm(Model model) 
	{
		Leave_Applied leave_applied = new Leave_Applied();
		model.addAttribute("leaveApplied", leave_applied);
		return "submitLeave";
	}
	
	@PostMapping("/submitLeave")
	public String createSubmitLeave(@ModelAttribute("leave_applied")Leave_Applied leaveApplied, HttpSession session) 
	{
		//TODO: to have user session class
		//TODO: to implement Leave_Applied service
	}
	

}
