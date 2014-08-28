package com.kobaco.smartAD.pmc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("controller.CmsController")
@RequestMapping("/cms")
public class CmsController {

	@RequestMapping("/list.do")
	public ModelAndView cmsList(
			@RequestParam (value="type",defaultValue="notice" )String type,
			@RequestParam (value="page",defaultValue="1" )String page) {
		ModelAndView mav = new ModelAndView("jsp/cms/list");		
		
		mav.addObject("type", type);
		mav.addObject("page", page);
		return mav;
	}
	
	@RequestMapping("/noticeList.do")
	public ModelAndView noticeList() {
		ModelAndView mav = new ModelAndView("jsp/cms/notice_list");		
		return mav;
	}
	
	@RequestMapping("/noticeDetail.do")
	public ModelAndView noticeDetail(@RequestParam (value="no",defaultValue="" )String no) {
		ModelAndView mav = new ModelAndView("jsp/cms/notice_detail");
		
		mav.addObject("no", no);
		return mav;
	}
	
	@RequestMapping("/noticReg.do")
	public ModelAndView noticeRegister() {
		ModelAndView mav = new ModelAndView("jsp/cms/notice_reg");		
		return mav;
	}
	
	@RequestMapping("/noticeModify.do")
	public ModelAndView noticeModify() {
		ModelAndView mav = new ModelAndView("jsp/cms/notice_modify");		
		return mav;
	}
	
	@RequestMapping("/faqList.do")
	public ModelAndView faqList() {
		ModelAndView mav = new ModelAndView("jsp/cms/faq_list");		
		return mav;
	}
	
	@RequestMapping("/faqReg.do")
	public ModelAndView faqRegister() {
		ModelAndView mav = new ModelAndView("jsp/cms/faq_reg");		
		return mav;
	}
	
	@RequestMapping("/faqModify.do")
	public ModelAndView faqModify() {
		ModelAndView mav = new ModelAndView("jsp/cms/faq_modify");		
		return mav;
	}
	
	@RequestMapping("/inquiryList.do")
	public ModelAndView inquiryList() {
		ModelAndView mav = new ModelAndView("jsp/cms/inquiry_list");		
		return mav;
	}
	
	@RequestMapping("/inquiryDetail.do")
	public ModelAndView inquiryDetail(@RequestParam (value="no",defaultValue="" )String no) {
		ModelAndView mav = new ModelAndView("jsp/cms/inquiry_detail");
		
		mav.addObject("no", no);
		return mav;
	}
	
}
