package vn.edu.vnu.ai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TextAccentController {

	
	@RequestMapping(value="/recover-accent", method=RequestMethod.GET)
	public String openRecoverAccentPage() {
		return "recover-accent";
	}
	
	@RequestMapping(value="/training", method=RequestMethod.GET)
	public String training() {
		return "training";
	}
	
}
