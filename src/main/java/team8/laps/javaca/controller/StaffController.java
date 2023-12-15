package team8.laps.javaca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import team8.laps.javaca.interfacemethods.LeaveAppliedService;
import team8.laps.javaca.interfacemethods.StaffService;
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
	public void getservice(StaffServiceImpl staffservice, LeaveAppliedServiceImpl leaveAppliedservice) {
		this.staffservice = staffservice;
		this.leaveAppliedservice = leaveAppliedservice;
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
}
