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
	
	@PostMapping("/manager")
	public String managerlogin()
	{
		return "";
	}
	
	@PostMapping("/admin")
	public String adminlogin()
	{
		return "";
	}

}
