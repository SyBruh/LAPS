package team8.laps.javaca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Admin")
public class AdminController {

	

	 @GetMapping("/Admin/updateEntitle")
	 public String UpdateEntitle(Model model) { 
	 return "displayCountries";
	 }
}
