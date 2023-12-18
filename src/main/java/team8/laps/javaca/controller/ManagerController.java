package team8.laps.javaca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import team8.laps.javaca.interfacemethods.LeaveAppliedService;
import team8.laps.javaca.interfacemethods.StaffService;
import team8.laps.javaca.model.Leave_Applied;
import team8.laps.javaca.service.LeaveAppliedServiceImpl;
import team8.laps.javaca.service.StaffServiceImpl;

@Controller
@RequestMapping("manager")
public class ManagerController {
	//LeaveApplication List will be (/viewLeaves)
	//view employee is (/viewEmployee)
	//personal leave history is (/viewLeaveHistory)
	//Leave Detail is (/viewDetail)
	//submit leave is (/submitLeave)
	//If need more controller map it accordingly
	
	@Autowired
	private StaffService staffService;
	@Autowired
	private LeaveAppliedService leaveAppliedService;
	
	public void setService(StaffServiceImpl staffService, LeaveAppliedServiceImpl leaveAppliedService) {
		this.staffService = staffService;
		this.leaveAppliedService = leaveAppliedService;
	}
	@GetMapping("/viewEmployee/{id}")
	public String viewEmployee(@PathVariable("id")int id, Model model) {
		model.addAttribute("staffs",staffService.findStaffByManagerId(id) );
		return "viewEmployee";
		
	}
	
	@PostMapping("/updateStatus")
	public String UpdateStatus(@ModelAttribute("Leave") Leave_Applied la,Model model) {
		leaveAppliedService.updateLeave(la);
		return "redirect:/staff/viewDetail/" + la.getId();
	}
}

