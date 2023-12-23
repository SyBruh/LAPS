package team8.laps.javaca.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import team8.laps.javaca.interfacemethods.AnualHolidayService;
import team8.laps.javaca.interfacemethods.LeaveAppliedService;
import team8.laps.javaca.interfacemethods.LeaveTypeService;
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
@RequestMapping("staff")
public class StaffController {

	@Autowired
	private StaffService staffservice;
	@Autowired
	private LeaveAppliedService leaveAppliedservice;
	@Autowired
	private LeaveTypeService leaveTypeService;
	@Autowired
	private AnualHolidayService anualHolidayService;
	@Autowired
	private StaffLeaveTypeService staffLeaveTypeService;

	@Autowired
	public void getservice(StaffServiceImpl staffservice, StaffLeaveTypeServiceImpl staffLeaveTypeService,
			AnualHolidayServiceImpl anualHolidayService, LeaveAppliedServiceImpl leaveAppliedservice,
			LeaveTypeService leaveTypeService) {
		this.staffservice = staffservice;
		this.leaveAppliedservice = leaveAppliedservice;
		this.leaveTypeService = leaveTypeService;
		this.anualHolidayService = anualHolidayService;
		this.staffLeaveTypeService = staffLeaveTypeService;
	}

	// personal leave history is (/viewLeaveHistory)
	@GetMapping("/viewLeaveHistory/{staff_id}") // will change to HttpSession
	public String ViewLeaveHistory(Model model, @PathVariable("staff_id") int id, HttpSession sessionobj) {
		sessionobj.setAttribute("view", "staff");
		model.addAttribute("leaveList", staffservice.getLeaveHistory(id));
		return "PersonalLeaveHistory";
	}

	// Leave Detail is (/viewDetail)
	@GetMapping("/viewDetail/{la_id}")
	public String ViewDetail(Model model, @PathVariable("la_id") int id, HttpSession sessionobj) {
		Leave_Applied la = leaveAppliedservice.getLeaveDetail(id);
		if (la.getStatus().equals(LeaveStatusEnum.Applied) || la.getStatus().equals(LeaveStatusEnum.Updated)) {
			sessionobj.setAttribute("permission", "true");
		} else {
			sessionobj.setAttribute("permission", "false");
		}

		model.addAttribute("Leave", la);
		return "leaveDetail";
	}

	@PostMapping("/update")
	public String UpdateHandler(@ModelAttribute("Leave") Leave_Applied leave_applied,
			@RequestParam("action") String action, Model model, HttpSession sessionobj) {
		if ("Update".equals(action)) {
			if (!checkdates(leave_applied.getLeave_start(), leave_applied.getLeave_end())) {
				return "redirect:/staff/viewDetail/" + leave_applied.getId();
			}
			// Setting user id
			// Staff staff = (Staff) sessionobj.getAttribute("staff");
			// leave_applied.setStaff(staff);

			// Setting leave status
			leave_applied.setStatus(LeaveStatusEnum.Updated);

			// Set current date
//			LocalDate localdate = LocalDate.now();
//			leave_applied.setDate_applied(localdate);
			Integer oldleavecount = leave_applied.getLeavecount();
			Integer leavecount = LeaveLogic(leave_applied.getLeave_start(), leave_applied.getLeave_end());
			leave_applied.setLeavecount(leavecount);
			Staff newstaff = leave_applied.getStaff();
			for (Staff_Leave_Type slt : newstaff.getStaffleaves()) {
				if (slt.getLeavetype() == leave_applied.getLeavetype()) {
					int remainbalance = (int) (slt.getLeave_balance() - leavecount + oldleavecount);
					if (remainbalance < 0) {
						return "redirect:/staff/viewDetail/" + leave_applied.getId();
					}
					staffLeaveTypeService.updatebalance(remainbalance, leave_applied.getStaff().getId(),
							leave_applied.getLeavetype().getId());
				}
			}

			leaveAppliedservice.updateLeave(leave_applied);
			return "redirect:/staff/viewDetail/" + leave_applied.getId();
		} else if ("Delete".equals(action)) {
			Staff newstaff = leave_applied.getStaff();
			for (Staff_Leave_Type slt : newstaff.getStaffleaves()) {
				if (slt.getLeavetype() == leave_applied.getLeavetype()) {
					int remainbalance = (int) (slt.getLeave_balance() + leave_applied.getLeavecount());
					staffLeaveTypeService.updatebalance(remainbalance, leave_applied.getStaff().getId(),
							leave_applied.getLeavetype().getId());
				}
			}

			leaveAppliedservice.removeLeaveApplied(leave_applied);
			return "redirect:/staff/viewLeaveHistory/" + sessionobj.getAttribute("staffid");
		} else if ("Cancel".equals(action)) {
			if (leave_applied.getStatus().equals(LeaveStatusEnum.Rejected)) {

			} else {
				Staff newstaff = leave_applied.getStaff();
				for (Staff_Leave_Type slt : newstaff.getStaffleaves()) {
					if (slt.getLeavetype() == leave_applied.getLeavetype()) {
						int remainbalance = (int) (slt.getLeave_balance() + leave_applied.getLeavecount());
						staffLeaveTypeService.updatebalance(remainbalance, leave_applied.getStaff().getId(),
								leave_applied.getLeavetype().getId());
					}
				}
			}
			leave_applied.setStatus(LeaveStatusEnum.Cancelled);

			leaveAppliedservice.updateLeave(leave_applied);
			return "redirect:/staff/viewDetail/" + leave_applied.getId();

		}
		return "staff";

	}
	// submit leave is (/submitLeave)
	// If need more controller map it accordingly
	/*
	 * For staff.html page. Find leave by Staff Id, Status, Date Applied
	 */

	// Find all leave by Staff ID
	public String findLeaveByStaffId(Model model) {
		int staff_id = 0;// Get staff id from session??
		model.addAttribute("leaves", leaveAppliedservice.findLeaveByStaffId(staff_id));
		return "staff";
	}

	// Find leave by status (for manager to find leave that are still pending)
	public String findLeaveByStatus(Model model, String status) {
		model.addAttribute("status", leaveAppliedservice.findLeaveStatus(status));
		return "staff";
	}

	// Find leave by date applied
	public String findLeaveByDateApplied(Model model, Date date_applied) {
		model.addAttribute("date_applied", leaveAppliedservice.findLeaveByDateApplied(date_applied));
		return "staff";
	}

	/*
	 * submitLeave.html page
	 */

	// Bind Leave_Applied object to form model
	@GetMapping("/submitLeave")
	public String submitLeaveForm(Model model, HttpSession sessionObj) {
		// Bind username to model
		String username = (String) sessionObj.getAttribute("username");
		model.addAttribute("username", username);

		// Bind Leave Applied object to form model
		Leave_Applied leave_applied = new Leave_Applied();
		model.addAttribute("leaveApplied", leave_applied);

		// Bind Leave types for drop down list
		model.addAttribute("leave_types", leaveTypeService.getAllLeaveType());
		return "submitLeave";
	}

	// POST submit leave
	@PostMapping("/submitLeave")
	public String createSubmitLeave(@ModelAttribute("leaveApplied") Leave_Applied leave_applied,
			HttpSession sessionObj) {
		if (!checkdates(leave_applied.getLeave_start(), leave_applied.getLeave_end())) {
			return "submitLeave";
		}
		// Setting user id
		Staff staff = (Staff) sessionObj.getAttribute("staff");
		leave_applied.setStaff(staff);

		// Setting leave status
		leave_applied.setStatus(LeaveStatusEnum.Applied);

		// Set current date
		LocalDate localdate = LocalDate.now();
		leave_applied.setDate_applied(localdate);

		Integer leavecount = LeaveLogic(leave_applied.getLeave_start(), leave_applied.getLeave_end());
		leave_applied.setLeavecount(leavecount);
		Staff newstaff = leave_applied.getStaff();
		for (Staff_Leave_Type slt : newstaff.getStaffleaves()) {
			if (slt.getLeavetype().getId() == leave_applied.getLeave_type_id()) {
				int remainbalance = (int) (slt.getLeave_balance() - leavecount);
				if (remainbalance < 0) {
					return "redirect:/staff/submitLeave";
				}
				staffLeaveTypeService.updatebalance(remainbalance, leave_applied.getStaff().getId(),
						leave_applied.getLeave_type_id());
			}
		}

		leaveAppliedservice.createLeave(leave_applied);
		return "staff";
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
		Integer numberofleaves = (int) (ChronoUnit.DAYS.between(start, end) + 1);
		String startday = dtf.format(start);
		if (numberofleaves <= 14) {
			dateleaves = recurDays(date, dateleaves, numberofleaves, startday);
			leavecount = numberofleaves - getweekends(dateleaves) - getholiday(start, end);
			System.out.println(dateleaves);
		} else {
			leavecount = numberofleaves;
		}
		return leavecount;
	}

	public List<String> recurDays(List<String> date, List<String> dateleaves, long limit, String startday) {
		if (dateleaves.size() < limit) {
			for (String day : date) {
				if (dateleaves.size() < limit) {
					if (dateleaves.size() >= 1) {
						dateleaves.add(day);
					} else {
						if (day.equals(startday)) {
							dateleaves.add(day);
						}
					}
				}

			}
			return recurDays(date, dateleaves, limit, startday);
		} else {
			return dateleaves;
		}
	}

	public Integer getweekends(List<String> days) {
		int count = 0;
		for (String day : days) {
			if (day == "Sat" || day == "Sun") {
				count++;
			}
		}
		return count;
	}

	public Integer getholiday(LocalDate StartDate, LocalDate EndDate) {
		int count = 0;
		List<Anual_Holiday> holidays = anualHolidayService.getholiday(StartDate, EndDate);
		if (!holidays.isEmpty()) {
			for (Anual_Holiday ah : holidays) {
				count = (int) (count + ChronoUnit.DAYS.between(ah.getStartDate(), ah.getEndDate()) + 2);
			}
		}
		return count;
	}

	public boolean checkdates(LocalDate start, LocalDate end) {
		DayOfWeek startday = DayOfWeek.of(start.get(ChronoField.DAY_OF_WEEK));
		DayOfWeek endday = DayOfWeek.of(end.get(ChronoField.DAY_OF_WEEK));
		if (startday == DayOfWeek.SATURDAY || startday == DayOfWeek.SUNDAY) {
			return false;
		}
		if (endday == DayOfWeek.SATURDAY || endday == DayOfWeek.SUNDAY) {
			return false;
		}
		if (anualHolidayService.checkHoliday(start) && anualHolidayService.checkHoliday(end)) {
			return true;
		} else {
			return false;
		}
	}
}
