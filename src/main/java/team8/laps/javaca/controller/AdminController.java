package team8.laps.javaca.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
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
import team8.laps.javaca.model.Staff;
import team8.laps.javaca.model.User;
import team8.laps.javaca.repository.AnualHolidayRepository;
import team8.laps.javaca.service.AnualHolidayServiceImpl;
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

	@GetMapping("/createUser")
	public String addUserForm(Model model) {
	    User user = new User();
	    Staff staff = new Staff();
	    
	    model.addAttribute("user", user);
	    model.addAttribute("staff", staff);
	    
	    
	    return "addUser"; 
	}
	
	//Add user will map with (/createUser)
	//Update Entitle will map with (updateEntitle)
	 @GetMapping("/updateEntitleLeave")
	 public String updateEntitleLeaveForm()
	 {
		 return "updateEntitleLeave";
	 }
	//UserList will map with (/viewUsers)
	 @GetMapping("/userList")
	 public String userListForm()
	 {
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
	@GetMapping("/updateUser")
	public String updateUserForm(Model model) {
		User updateUser = new User();
		model.addAttribute("updateUser", updateUser);
		return "updateUser";
	}
	
	//POST update user
	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute("updateUser")User updateUser) {
		userServiceImpl.updateUser(updateUser);
		return "redirect:/userList";
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
