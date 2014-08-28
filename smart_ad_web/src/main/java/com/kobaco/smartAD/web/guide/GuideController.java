package com.kobaco.smartAD.web.guide;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("guide.GuideController")
public class GuideController {

	@RequestMapping("/guide/faq.do")
	public ModelAndView faq() {
		ModelAndView mav = new ModelAndView("jsp/guide/faq");		
		return mav;
	}
	
	@RequestMapping("/guide/noticeList.do")
	public ModelAndView noticeList() {
		ModelAndView mav = new ModelAndView("jsp/guide/notice_list");		
		return mav;
	}
	
	@RequestMapping("/guide/noticeDetail.do")
	public ModelAndView noticeDetail(@RequestParam (value="no",defaultValue="" )String no) {
		ModelAndView mav = new ModelAndView("jsp/guide/notice_detail");		
		
		mav.addObject("no", no);
		return mav;
	}
	
	@RequestMapping("/guide/inquiry.do")
	public ModelAndView inquiry() {
		ModelAndView mav = new ModelAndView("jsp/guide/inquiry");		
		return mav;
	}
	
	@RequestMapping("/guide/location.do")
	public ModelAndView location() {
		ModelAndView mav = new ModelAndView("jsp/guide/location");		
		return mav;
	}
	
	@RequestMapping("/guide/networking.do")
	public ModelAndView networking() {
		ModelAndView mav = new ModelAndView("jsp/guide/networking");		
		return mav;
	}
	
}
