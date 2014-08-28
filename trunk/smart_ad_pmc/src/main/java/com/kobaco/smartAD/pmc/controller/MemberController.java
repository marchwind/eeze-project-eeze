package com.kobaco.smartAD.pmc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("controller.MemberController")
@RequestMapping("/member")
public class MemberController {

	@RequestMapping("/list.do")
	public ModelAndView memberList() {
		ModelAndView mav = new ModelAndView("jsp/member/list");		
		return mav;
	}
	
	@RequestMapping("/detail.do")
	public ModelAndView memberDetail(@RequestParam (value="no",defaultValue="" )String no) {
		ModelAndView mav = new ModelAndView("jsp/member/detail");
		
		mav.addObject("no", no);
		return mav;
	}
	
	@RequestMapping("/memberDetailHTML.do")
	public ModelAndView memberDetailHTML() {
		ModelAndView mav = new ModelAndView("jsp/member/memberDetailHTML");
		
		return mav;
	}
	
	@RequestMapping("/memberModifyHTML.do")
	public ModelAndView memberModifyHTML() {
		ModelAndView mav = new ModelAndView("jsp/member/memberModifyHTML");
		
		return mav;
	}
	
	@RequestMapping("/modify.do")
	public ModelAndView memberModify() {
		ModelAndView mav = new ModelAndView("jsp/member/modify");		
		return mav;
	}
	
	@RequestMapping("/join.do")
	public ModelAndView memberJoin() {
		ModelAndView mav = new ModelAndView("jsp/member/join");		
		return mav;
	}
	
	@RequestMapping("/popupList.do")
	public ModelAndView popupMemberList() {
		ModelAndView mav = new ModelAndView("jsp/member/memberPopupHTML");		
		return mav;
	}
}
