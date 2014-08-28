package com.kobaco.smartAD.pmc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("controller.AdminController")
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("/list.do")
	public ModelAndView memberList() {
		ModelAndView mav = new ModelAndView("jsp/admin/list");		
		return mav;
	}
	
	@RequestMapping("/detail.do")
	public ModelAndView memberDetail(@RequestParam (value="no",defaultValue="" )String no) {
		ModelAndView mav = new ModelAndView("jsp/admin/detail");	
		mav.addObject("no", no);
		return mav;
	}
	
	@RequestMapping("/modify.do")
	public ModelAndView memberModify(@RequestParam (value="no",defaultValue="" )String no) {
		ModelAndView mav = new ModelAndView("jsp/admin/modify");
		mav.addObject("no", no);
		return mav;
	}
	
	@RequestMapping("/join.do")
	public ModelAndView memberJoin() {
		ModelAndView mav = new ModelAndView("jsp/admin/join");		
		return mav;
	}
}
