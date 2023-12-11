package team8.laps.javaca.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import team8.laps.javaca.model.Leave_Applied;

public class StaffController {
	@GetMapping("/staff/submitLeave")
	public String newSubmitLeave(Model model) 
	{
		model.addAttribute("leaveApplied", new Leave_Applied());
		return "submitLeave";
	}
	
//	@PostMapping("/staff/submitLeave")
//	public String createSubmitLeave(Leave_Applied leaveApplied, HttpSession session) 
//	{
//		//TODO: to have user session class
//		//TODO: to implement Leave_Applied service
//	}
	

}
