package com.kobaco.smartAD.web.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("main.mainController")
public class MainController {

	@RequestMapping("/main.do")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView("jsp/index");		
		return mav;
	}
	
}
