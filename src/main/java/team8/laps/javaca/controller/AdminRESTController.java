package team8.laps.javaca.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team8.laps.javaca.model.Anual_Holiday;
import team8.laps.javaca.model.Leave_Type;
import team8.laps.javaca.model.Role;
import team8.laps.javaca.model.Staff;
import team8.laps.javaca.model.Staff_Leave_Type;
import team8.laps.javaca.model.User;
import team8.laps.javaca.model.UserStaffDTO;
import team8.laps.javaca.service.AnualHolidayServiceImpl;
import team8.laps.javaca.service.LeaveTypeServiceImpl;
import team8.laps.javaca.service.RoleServiceImpl;
import team8.laps.javaca.service.StaffLeaveTypeServiceImpl;
import team8.laps.javaca.service.StaffServiceImpl;
import team8.laps.javaca.service.UserServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("api")
public class AdminRESTController {	
  
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

	@GetMapping("/getmanagers")
	public List<String> getManagers(){
		List<User> managers = userServiceImpl.findManagers();
		List<String> manager = new ArrayList<String>();
		for(User m : managers) {
			manager.add(m.getStaff().getStaff_name());
		}
		return manager;
	}
	@GetMapping("/getroles")
	public List<String> getRoles(){
		List<Role> roles = roleServiceImpl.findallroles();
		List<String> role = new ArrayList<String>();
		for(Role r : roles) {
			role.add(r.getRole());
		}
		return role;
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<String> SaveUser(@ModelAttribute Staff staff,
	        @ModelAttribute User user) {
		 try {
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
			  return new ResponseEntity<String>("created",HttpStatus.CREATED);
		  }catch(Exception e) {
			  return new ResponseEntity<String>("Not good",HttpStatus.EXPECTATION_FAILED);
		  }	
	}
	
	//Add user will map with (/createUser)
	//Update Entitle will map with (updateEntitle)
	@GetMapping("/leaveTypeList")
	public List<Leave_Type> LeaveTypeList() {	
		List<Leave_Type> lts = leaveTypeServiceImpl.getAllLeaveType();
		return lts;
	}
	 @GetMapping("/updateEntitleLeave/{id}")
	 public Leave_Type GetLeaveType(@PathVariable("id") int id)
	 {		 
		 Leave_Type lt = leaveTypeServiceImpl.getleavetypebyID(id);
		 return lt;
	 }
	 @GetMapping("/getbalance/{id}")
	 public Integer GetLeaveTypeBalance(@PathVariable("id") int id) {
		 Leave_Type lt = leaveTypeServiceImpl.getleavetypebyID(id);
		 return staffLeaveTypeServiceImpl.findleavetypebalance(lt,"professional").getLeave_entitle();
	 }
	 
	 @PutMapping("/updateEntitleLeave/{id}")
	 public void updateEntitleLeaveForm(@PathVariable("id") int id, @RequestParam("leave_entitle") int entitle, @RequestParam("employeeType") String desgination) {
		 Leave_Type lt = leaveTypeServiceImpl.getleavetypebyID(id);
		 staffLeaveTypeServiceImpl.updateentitle(entitle, lt, desgination);
	 }
	//UserList will map with (/viewUsers)
	 @GetMapping("/userList")
	 public List<HashMap<String,Object>> userListForm()
	 {
		 List<Staff> staffs = staffServiceImpl.getallStaff();	 
		 List<HashMap<String,Object>> returnthings = new ArrayList<>();
		 for(Staff s:staffs) {
			 HashMap<String,Object> staff = new HashMap<String,Object>();
			 staff.put("id", s.getId());
			 staff.put("staff_name", s.getStaff_name());
			 staff.put("designation", s.getDesignation());
			 returnthings.add(staff);
		 }
		 return returnthings;
		 //return staffServiceImpl.getallStaff();
	 }

	
	
	//Update User will (/updateUser)
	//Bind User object to form model
	@GetMapping("/getstaff/{id}")
	public Staff updateUserForm(@PathVariable("id") int staffid) {
		return staffServiceImpl.findstaffbyID(staffid);
	}
	@GetMapping("/getuser")
	public User getuser(@PathVariable("id") int id) {
		return staffServiceImpl.findstaffbyID(id).getUser();
	}
	
	
//	@DeleteMapping("/deleteUser/{id}")
//	public String deleteUser(Model model,@PathVariable("id") int staffid) {
//		staffServiceImpl.deletestaff(staffid);
//		return "redirect:/admin/userList";
//	}
	
	
	//POST update user
	@PutMapping("/updateUser")
	public ResponseEntity<User> updateUser(@ModelAttribute Staff staff,
	        @ModelAttribute User user) {
		try {
			staff.setUser(user);
			userServiceImpl.updateUser(user);
			staffServiceImpl.saveStaff(staff);
			Staff_Leave_Type slt = staffLeaveTypeServiceImpl.findanualLeave(staff);
			Integer balance = staffLeaveTypeServiceImpl.findleavetypebalance(slt.getLeavetype(),staff.getDesignation()).getLeave_entitle();
			slt.setLeave_entitle(balance);
			staffLeaveTypeServiceImpl.addslt(slt);
			  return new ResponseEntity<User>(user,HttpStatus.OK);
		  }catch(Exception e) {
			  return new ResponseEntity<User>(HttpStatus.EXPECTATION_FAILED);
		  }	
	}
	
	//View holiday list
	@GetMapping("/holidayList")
	public List<Anual_Holiday> findAllHoliday() {		
		return anualHolidayServiceImpl.findAllHoliday();
	}
	
	@GetMapping("/GetHoliday")
	public Anual_Holiday updateHolidayForm(@RequestParam("selectedHolidayId") int holidayId){
		Anual_Holiday updateHoliday = anualHolidayServiceImpl.findHolidayById(holidayId);	
		return updateHoliday;
	}
	
	//Post update or delete holiday
	@PutMapping("/updateHoliday")
	public ResponseEntity<Anual_Holiday> updateHoliday(@RequestBody Anual_Holiday updateholiday) {
	
		try {
			anualHolidayServiceImpl.updateAnualHoliday(updateholiday);
			  return new ResponseEntity<Anual_Holiday>(updateholiday,HttpStatus.CREATED);
		  }catch(Exception e) {
			  return new ResponseEntity<Anual_Holiday>(HttpStatus.EXPECTATION_FAILED);
		  }	
	
	}
	@DeleteMapping("/deleteHoliday/{id}")
	public ResponseEntity<Anual_Holiday> DeleteCourse(@PathVariable("id") int id){
		  try {
			  anualHolidayServiceImpl.deleteHolidayById(id);
			  return new ResponseEntity<Anual_Holiday>(HttpStatus.NO_CONTENT);
		  }catch(Exception e) {
			  return new ResponseEntity<Anual_Holiday>(HttpStatus.EXPECTATION_FAILED);
		  }
	  }
	
	
	//Post add holiday
	@PostMapping("/addHoliday")
	public ResponseEntity<Anual_Holiday> addHoliday(@ModelAttribute("addholiday")Anual_Holiday addholiday) {
		try {
			anualHolidayServiceImpl.createAnualHoliday(addholiday);
			  return new ResponseEntity<Anual_Holiday>(addholiday,HttpStatus.CREATED);
		  }catch(Exception e) {
			  return new ResponseEntity<Anual_Holiday>(HttpStatus.EXPECTATION_FAILED);
		  }	
	}
	
	
}
