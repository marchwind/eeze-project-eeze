package com.kobaco.smartAD.pmc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */

@Controller("controller.mainController")
public class MainController {

	@RequestMapping("/login.do")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("jsp/login/login");		
		return mav;
	}
	
	@RequestMapping("/joinRequst.do")
	public ModelAndView joinRequest() {
		ModelAndView mav = new ModelAndView("jsp/login/join_request");		
		return mav;
	}
	
	@RequestMapping("/changePwd.do")
	public ModelAndView changePwd() {
		ModelAndView mav = new ModelAndView("jsp/login/change_password");		
		return mav;
	}
	
	@RequestMapping("/main.do")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView("jsp/index");		
		return mav;
	}
	
	@RequestMapping("/gnbSearch.do")
	public ModelAndView gnbSearch() {
		ModelAndView mav = new ModelAndView("jsp/searchResult");		
		return mav;
	}
}
