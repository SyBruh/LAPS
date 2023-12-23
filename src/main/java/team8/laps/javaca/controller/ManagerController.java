package team8.laps.javaca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import team8.laps.javaca.interfacemethods.StaffService;

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
	@GetMapping("/viewEmployee/{id}")
	public String viewEmployee(@PathVariable("id")int id, Model model) {
		model.addAttribute("staffs",staffService.findStaffByManagerId(id) );
		return "viewEmployee";
		
	}
}

