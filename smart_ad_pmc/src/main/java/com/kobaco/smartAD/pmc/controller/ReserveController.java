package com.kobaco.smartAD.pmc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("controller.ReserveController")
@RequestMapping("/reserve")
public class ReserveController {

	@RequestMapping("/list.do")
	public ModelAndView reserveList() {
		ModelAndView mav = new ModelAndView("jsp/reserve/list");		
		return mav;
	}
	
	@RequestMapping("/list_cal.do")
	public ModelAndView reserveListCal() {
		ModelAndView mav = new ModelAndView("jsp/reserve/calendar");		
		return mav;
	}
	
	@RequestMapping("/check.do")
	public ModelAndView reserveCheck() {
		ModelAndView mav = new ModelAndView("jsp/reserve/check");		
		return mav;
	}
	
	@RequestMapping("/checkInConfirm.do")
	public ModelAndView reserveCheckInConfirm() {
		ModelAndView mav = new ModelAndView("jsp/reserve/checkIn");		
		return mav;
	}
	
	@RequestMapping("/checkOutModify.do")
	public ModelAndView reserveCheckOutModify() {
		ModelAndView mav = new ModelAndView("jsp/reserve/checkOut_modify");		
		return mav;
	}
	
	@RequestMapping("/input.do")
	public ModelAndView reserveInput() {
		ModelAndView mav = new ModelAndView("jsp/reserve/input");		
		return mav;
	}
	
	@RequestMapping("/memberPopup.do")
	public ModelAndView reserveMemberPopup() {
		ModelAndView mav = new ModelAndView("jsp/reserve/reservePopupHTML");		
		return mav;
	}
}
