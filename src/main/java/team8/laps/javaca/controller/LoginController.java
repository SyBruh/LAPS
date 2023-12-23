package team8.laps.javaca.controller;
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

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import team8.laps.javaca.interfacemethods.StaffService;
import team8.laps.javaca.interfacemethods.UserService;
import team8.laps.javaca.model.Role;
import team8.laps.javaca.model.User;
import team8.laps.javaca.service.StaffServiceImpl;
import team8.laps.javaca.service.UserServiceImpl;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	public void setService(UserServiceImpl userService) {
		this.userService = userService;
	}
	

	@GetMapping("")
	public String login(Model model) 
	{
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}
	
	@PostMapping("")
	public String login(@ModelAttribute("user")@Valid User user,BindingResult bindingresult,Model model,@RequestParam("keyword") String keyword, HttpSession sessionobj) {
		if(bindingresult.hasErrors()) {
			return "login";
		}
		User rluser = userService.userAuthentication(user.getUser_name(), user.getPassword());
		if(rluser != null) {
			sessionobj.setAttribute("staffid", rluser.getStaff().getId()); 
			sessionobj.setAttribute("username", rluser.getUser_name());
			sessionobj.setAttribute("staffname", rluser.getStaff().getStaff_name());
			List<Role> roles = userService.getroles(rluser.getId());
			List<String> rol = new ArrayList<String>();
			roles.stream().forEach(r -> rol.add(r.getRole()));
			if(rol.contains("admin") && keyword.equals("admin")) {
				sessionobj.setAttribute("role", "admin");
				return "admin";
			}else if(rol.contains("manager")) {
				sessionobj.setAttribute("role", "manager");
				return "manager";
			}else {
				sessionobj.setAttribute("role", "staff");
				return "staff";
			}
		}else {
			return "login";
		}

	}

}
