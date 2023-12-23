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
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping("/updateStatus")
	public String UpdateStatus(@ModelAttribute("Leave") Leave_Applied la,Model model) {
		
		if(la.getStatus() == LeaveStatusEnum.Approved) {
			Integer leavecount = LeaveLogic(la.getLeave_start(),la.getLeave_end());
			la.setLeavecount(leavecount);
			Staff staff = la.getStaff();
			for(Staff_Leave_Type slt:staff.getStaffleaves()) {
				if(slt.getLeavetype().equals(la.getLeavetype())) {
				int remainbalance = (int) (slt.getLeave_balance() - leavecount);
				staffLeaveTypeService.updatebalance(remainbalance, la.getStaff().getId(), la.getLeavetype().getLeaveType());
				}
			}
		}
		leaveAppliedService.updateLeave(la);
		
		//staffLeaveTypeService.updatebalance(la.getLeavecount(), la.getStaff().getId(), la.getLeavetype().getLeaveType());
		return "redirect:/staff/viewDetail/" + la.getId();
	}
	
	public Integer LeaveLogic(LocalDate start, LocalDate end) {
		Integer leavecount = null;
		List<String> date = new ArrayList<String>();
		date.add("Mon");
		date.add("Tue");
		date.add("Wed");
		date.add("Thu");
		date.add("Fri");
		date.add("Sat");
		date.add("Sun");
		List<String> dateleaves = new ArrayList<String>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E");
		Integer numberofleaves = (int) (ChronoUnit.DAYS.between(start, end) + 2);
		String startday = dtf.format(start);
		if(numberofleaves <= 14) {
			dateleaves = recurDays(date,dateleaves,numberofleaves,startday);
			leavecount = numberofleaves - getweekends(dateleaves) - getholiday(start,end);			
			System.out.println(dateleaves);
		}else {
			leavecount = numberofleaves;
		}
		return leavecount;
	}
	
	public List<String> recurDays(List<String> date,List<String> dateleaves, long limit, String startday){
		if(dateleaves.size() < limit) {
			for(String day :date) {
				if(dateleaves.size() < limit) {
					if(dateleaves.size() >= 1)
					{
						dateleaves.add(day);
					}else {
						if(day.equals(startday)) {
							dateleaves.add(day);
						}
					}	
				}
							
			}
			return recurDays(date,dateleaves,limit,startday);
		}
		else {
			return dateleaves;
		}		
	}
	
	public Integer getweekends(List<String> days) {
		int count = 0;
		for(String day: days ) {
			if(day == "Sat" || day == "Sun") {
				count++;
			}
		}
		return count;
	}
	
	public Integer getholiday(LocalDate StartDate,LocalDate EndDate) {
		int count = 0;
		List<Anual_Holiday> holidays = anualHolidayService.getholiday(StartDate, EndDate);
		if(!holidays.isEmpty()) {
			for(Anual_Holiday ah:holidays) {
			count = (int) (count + ChronoUnit.DAYS.between(ah.getStartDate(), ah.getEndDate()) + 2);
			}
		}
		return count;
	}
}

