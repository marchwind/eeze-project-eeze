package com.kobaco.smartAD.pmc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("controller.FacilityController")
@RequestMapping("/facility")
public class FacilityController {

	@RequestMapping("/list.do")
	public ModelAndView facilityList() {
		ModelAndView mav = new ModelAndView("jsp/facility/list");		
		return mav;
	}
	
	@RequestMapping("/detail.do")
	public ModelAndView facilityDetail(@RequestParam (value="no",defaultValue="" )String no) {
		ModelAndView mav = new ModelAndView("jsp/facility/detail");
		mav.addObject("no", no);
		return mav;
	}
	
	@RequestMapping("/detailHTML.do")
	public ModelAndView facilityDetailHTML() {
		ModelAndView mav = new ModelAndView("jsp/facility/facilityDetailHTML");		
		return mav;
	}
	
	@RequestMapping("/modifyHTML.do")
	public ModelAndView facilityModifyHTML() {
		ModelAndView mav = new ModelAndView("jsp/facility/facilityModifyHTML");		
		return mav;
	}
	
	@RequestMapping("/modify.do")
	public ModelAndView facilityModify() {
		ModelAndView mav = new ModelAndView("jsp/facility/modify");		
		return mav;
	}
	
}
