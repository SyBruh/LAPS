package team8.laps.javaca.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@GetMapping("")
	public String login() 
	{
		return "";
	}
	
	@PostMapping("/staff")
	public String stafflogin()
	{
		return "";
	}
	
	@GetMapping("/manager")
	public String managerlogin()
	{
		return "manager";
	}
	
	@GetMapping("/admin")
	public String adminlogin()
	{
		return "admin";
	}

}
