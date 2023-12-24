package team8.laps.javaca.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import team8.laps.javaca.interfacemethods.StaffService;
import team8.laps.javaca.model.Anual_Holiday;
import team8.laps.javaca.model.Leave_Type;
import team8.laps.javaca.model.Role;
import team8.laps.javaca.model.Staff;
import team8.laps.javaca.model.Staff_Leave_Type;
import team8.laps.javaca.model.User;
import team8.laps.javaca.repository.AnualHolidayRepository;
import team8.laps.javaca.service.AnualHolidayServiceImpl;
import team8.laps.javaca.service.LeaveTypeServiceImpl;
import team8.laps.javaca.service.RoleServiceImpl;
import team8.laps.javaca.service.StaffLeaveTypeServiceImpl;
import team8.laps.javaca.service.StaffServiceImpl;
import team8.laps.javaca.model.User;
import team8.laps.javaca.service.UserServiceImpl;

@Controller
@RequestMapping("admin")
public class AdminController {	
  
   @Autowired
   private UserServiceImpl userServiceImpl;
  
   @Autowired
   private AnualHolidayServiceImpl anualHolidayServiceImpl;
   
   @Autowired 
   private RoleServiceImpl roleServiceImpl;
   
   @Autowired
   private StaffServiceImpl staffServiceImpl;
   
   @Autowired
   private StaffLeaveTypeServiceImpl staffLeaveTypeServiceImpl;
   
   @Autowired
   private LeaveTypeServiceImpl leaveTypeServiceImpl;

	@GetMapping("/createUser")
	public String addUserForm(Model model) {
	    User user = new User();
	    Staff staff = new Staff();
	    List<User> managers = userServiceImpl.findManagers();
	    List<Role> allroles = roleServiceImpl.findallroles();
	    model.addAttribute("user", user);
	    model.addAttribute("staff", staff);
	    model.addAttribute("managers", managers);
	    model.addAttribute("allroles", allroles);
	    
	    
	    return "addUser"; 
	}
	
	@PostMapping("/addUser")
	public String SaveUser(@ModelAttribute("staff") Staff staff, @ModelAttribute("user") User user, Model model) {
		staff.setUser(user);
		userServiceImpl.updateUser(user);
		staffServiceImpl.saveStaff(staff);
		for(Leave_Type lt:leaveTypeServiceImpl.getAllLeaveType()) {
			Staff_Leave_Type slt = new Staff_Leave_Type();
			slt.setLeavetype(lt);
			slt.setStaff(staff);
			slt.setLeave_entitle(staffLeaveTypeServiceImpl.findleavetypebalance(lt,staff.getDesignation()).getLeave_entitle());
			slt.setLeave_balance(staffLeaveTypeServiceImpl.findleavetypebalance(lt,staff.getDesignation()).getLeave_entitle());
			staffLeaveTypeServiceImpl.addslt(slt);
		}
		
		return "admin";
	}
	
	//Add user will map with (/createUser)
	//Update Entitle will map with (updateEntitle)
	@GetMapping("/leaveTypeList")
	public String LeaveTypeList(Model model) {
		model.addAttribute("leavetypes", leaveTypeServiceImpl.getAllLeaveType());
		return "leavetypeList";
	}
	 @GetMapping("/updateEntitleLeave/{id}")
	 public String updateEntitleLeaveForm(Model model, @PathVariable("id") int id)
	 {
		 
		 Leave_Type lt = leaveTypeServiceImpl.getleavetypebyID(id);
		 if(lt.getLeaveType().equals("Anual Leave")) {
			 model.addAttribute("anual","true");
		 }else {
			 model.addAttribute("anual","false");
		 }
		 model.addAttribute("leavetype", lt);
		 model.addAttribute("entitle", staffLeaveTypeServiceImpl.findleavetypebalance(lt,"professional").getLeave_entitle());
		 return "updateEntitleLeave";
	 }
	 @PostMapping("/updateEntitleLeave")
	 public String updateEntitleLeaveForm(@ModelAttribute("leavetype") Leave_Type lt, @RequestParam("leave_entitle") int entitle, @RequestParam("employeeType") String desgination) {
		 staffLeaveTypeServiceImpl.updateentitle(entitle, lt, desgination);
		 return "redirect:/admin/leaveTypeList";
	 }
	//UserList will map with (/viewUsers)
	 @GetMapping("/userList")
	 public String userListForm(Model model)
	 {
		 model.addAttribute("staffs", staffServiceImpl.getallStaff());
		 return "userList";
	 }

	//Add user will map with (/createUser)
	//Update Entitle will map with (updateEntitle)
	
	//UserList will map with (/viewUsers)
	public String findAllUsers(Model model) {
		model.addAttribute("users", userServiceImpl.findAllUsers());
		return "userList";
	}
	
	//Update User will (/updateUser)
	//Bind User object to form model
	@GetMapping("/updateUser/{id}")
	public String updateUserForm(Model model,@PathVariable("id") int staffid) {
		Staff staff = staffServiceImpl.findstaffbyID(staffid);
		User user = staff.getUser();
		List<User> managers = userServiceImpl.findManagers();
		List<Role> allroles = roleServiceImpl.findallroles();
		model.addAttribute("user", user);
		model.addAttribute("staff", staff);
		model.addAttribute("managers", managers);
		model.addAttribute("allroles", allroles);
		return "updateUser";
	}
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(Model model,@PathVariable("id") int staffid) {
		staffServiceImpl.deletestaff(staffid);
		return "redirect:/admin/userList";
	}
	//POST update user
	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute("staff") Staff staff, @ModelAttribute("user") User user) {
		staff.setUser(user);
		userServiceImpl.updateUser(user);
		staffServiceImpl.saveStaff(staff);
		Staff_Leave_Type slt = staffLeaveTypeServiceImpl.findanualLeave(staff);
		Integer balance = staffLeaveTypeServiceImpl.findleavetypebalance(slt.getLeavetype(),staff.getDesignation()).getLeave_entitle();
		slt.setLeave_entitle(balance);
		staffLeaveTypeServiceImpl.addslt(slt);
		
		return "redirect:/admin/userList";
	}
	
	//View holiday list
	@GetMapping("/holidayList")
	public String findAllHoliday(Model model, HttpSession sessionObj) {
		String username = (String)sessionObj.getAttribute("username");
		model.addAttribute("username", username);
		model.addAttribute("holidays", anualHolidayServiceImpl.findAllHoliday());
		return "holidayList";
	}
	

	
	//Update Annual holiday
	@GetMapping("/updateHoliday")
	public String updateHolidayForm(@RequestParam("selectedHolidayId") int holidayId, Model model, HttpSession sessionObj){
		Anual_Holiday updateHoliday = anualHolidayServiceImpl.findHolidayById(holidayId);
		
		String username = (String)sessionObj.getAttribute("username");
		model.addAttribute("username", username);
		
		model.addAttribute("updateHoliday", updateHoliday);
		return "updateHoliday";
	}
	
	//Post update or delete holiday
	@PostMapping("/updateHoliday")
	public String updateHoliday(@ModelAttribute("updateholiday") Anual_Holiday updateholiday, 
			@RequestParam(value="action") String action) {
		//Get holiday id for deletion
		int holidayId = updateholiday.getId();
		
		//Update Holiday
		if(action.equals("Update")) {
			anualHolidayServiceImpl.updateAnualHoliday(updateholiday);
		}
		
		//Delete Holiday
		if(action.equals("Delete")) {
			anualHolidayServiceImpl.deleteHolidayById(holidayId);
		}
		
		return "redirect:/admin/holidayList";
	}
	
	//Add holiday object to form
	@GetMapping("/addHoliday")
	public String addHolidayForm(Model model, HttpSession sessionObj) {
		String username = (String)sessionObj.getAttribute("username");
		model.addAttribute("username", username);
		Anual_Holiday addHoliday = new Anual_Holiday();
		model.addAttribute("addholiday", addHoliday);
		return "addHoliday";
	}
	
	//Post add holiday
	@PostMapping("/addHoliday")
	public String addHoliday(@ModelAttribute("addholiday")Anual_Holiday addholiday) {
		anualHolidayServiceImpl.createAnualHoliday(addholiday);
		return "redirect:/admin/holidayList";
	}
	
	
}
