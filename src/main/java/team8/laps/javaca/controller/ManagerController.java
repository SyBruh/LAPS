package team8.laps.javaca.controller;

import java.io.Console;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import team8.laps.javaca.interfacemethods.AnualHolidayService;
import team8.laps.javaca.interfacemethods.LeaveAppliedService;
import team8.laps.javaca.interfacemethods.StaffLeaveTypeService;
import team8.laps.javaca.interfacemethods.StaffService;
import team8.laps.javaca.model.Anual_Holiday;
import team8.laps.javaca.model.LeaveStatusEnum;
import team8.laps.javaca.model.Leave_Applied;
import team8.laps.javaca.model.Staff;
import team8.laps.javaca.model.Staff_Leave_Type;
import team8.laps.javaca.service.AnualHolidayServiceImpl;
import team8.laps.javaca.service.LeaveAppliedServiceImpl;
import team8.laps.javaca.service.StaffLeaveTypeServiceImpl;
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
	private AnualHolidayService anualHolidayService;
	@Autowired
	private StaffLeaveTypeService staffLeaveTypeService;
	@Autowired
	private LeaveAppliedService leaveAppliedService;
	
	@Autowired
	public void setService(StaffServiceImpl staffService, LeaveAppliedServiceImpl leaveAppliedService,StaffLeaveTypeServiceImpl staffLeaveTypeService, AnualHolidayServiceImpl anualHolidayService) {
		this.staffService = staffService;
		this.leaveAppliedService = leaveAppliedService;
		this.anualHolidayService = anualHolidayService;
		this.staffLeaveTypeService = staffLeaveTypeService;
	}
	@GetMapping("/viewEmployee/{id}")
	public String viewEmployee(@PathVariable("id")int id, Model model) {
		model.addAttribute("staffs",staffService.findStaffByManagerId(id) );
		return "viewEmployee";
		
	}
	@GetMapping("/viewLeaveHistory/{staff_id}")// will change to HttpSession
	public String ViewLeaveHistory(Model model, @PathVariable("staff_id") int id, HttpSession sessionobj) {
		sessionobj.setAttribute("view", "manager");
		model.addAttribute("leaveList", staffService.getLeaveHistory(id));
		return "PersonalLeaveHistory";
	}
	//Leave Detail is (/viewDetail)
//	@GetMapping("/viewDetail/{la_id}")
//	public String ViewDetail(Model model, @PathVariable("la_id") int id){	
//		model.addAttribute("Leave", leaveAppliedService.getLeaveDetail(id));
//		return "leaveDetail";
//	}
	
	@PostMapping("/updateStatus")
	public String UpdateStatus(@ModelAttribute("Leave") Leave_Applied la,Model model) {
		
		leaveAppliedService.updateLeave(la);
		if(la.getStatus().equals(LeaveStatusEnum.Rejected)){
			Staff newstaff = la.getStaff();
			for(Staff_Leave_Type slt:newstaff.getStaffleaves()) {
				if(slt.getLeavetype() == la.getLeavetype()) {
				int remainbalance = (int) (slt.getLeave_balance() + la.getLeavecount());
				staffLeaveTypeService.updatebalance(remainbalance, la.getStaff().getId(), la.getLeavetype().getId());
				}
			}
		}
		
		return "redirect:/staff/viewDetail/" + la.getId();
	}
	
	@GetMapping("/ApplicationList")
	public String ViewApplicationList(Model model, HttpSession sessionobj) {
		sessionobj.setAttribute("view", "manager");
		model.addAttribute("leaves", leaveAppliedService.getLeaveApplied());
		return "leaveApplicationList";
	}
	
	@PostMapping("/ApplicationList")
	public String viewApplicationList(@RequestParam("startdate") LocalDate start, @RequestParam("enddate") LocalDate end, Model model) {
		model.addAttribute("leaves", leaveAppliedService.getLeaveAppliedbetween(start, end));
		return "leaveApplicationList";
	}
}

