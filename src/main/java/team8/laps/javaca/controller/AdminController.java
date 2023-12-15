package team8.laps.javaca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import team8.laps.javaca.interfacemethods.StaffService;
import team8.laps.javaca.model.Staff;
import team8.laps.javaca.model.User;
import team8.laps.javaca.service.StaffServiceImpl;
import team8.laps.javaca.model.User;
import team8.laps.javaca.service.UserServiceImpl;

@Controller
@RequestMapping("admin")
public class AdminController {	
  
  @Autowired
	private UserServiceImpl userServiceImpl;

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
	
	//If need more controller map it accordingly
	
	
}
