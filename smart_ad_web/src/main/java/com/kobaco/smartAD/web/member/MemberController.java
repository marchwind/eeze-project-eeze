package com.kobaco.smartAD.web.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("member.memberController")
public class MemberController {
	
	@RequestMapping("/member/loginSession.do")
	public ModelAndView loginSession(HttpServletRequest req, HttpServletResponse res,
			@RequestParam (value="userNo",defaultValue="" )String userNo,
			@RequestParam (value="userName",defaultValue="" )String userName,
			@RequestParam (value="userEmail",defaultValue="" )String userEmail) {
		ModelAndView mav = new ModelAndView("jsonView");		
		
		if(!userNo.equals("") && !userName.equals("")) {
		
			HttpSession session = req.getSession();
			session.setAttribute("login", true);
			session.setAttribute("userNo", userNo);
			session.setAttribute("userName", userName);
			session.setAttribute("userEmail", userEmail);
			
			mav.addObject("result","0000");
		} else {
			mav.addObject("result","1000");
		}
		
		return mav;
	}
	
	@RequestMapping("/member/logoutSession.do")
	public ModelAndView logoutSession(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("jsonView");		
		
		HttpSession session = req.getSession();
		session.setAttribute("login", false);
		session.setAttribute("userNo", null);
		session.setAttribute("userName", null);
		session.setAttribute("userEmail", null);
			
		mav.addObject("result","0000");
		
		return mav;
	}
	
	@RequestMapping("/member/getSession.do")
	public ModelAndView getSession(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("jsonView");		
		
		HttpSession session = req.getSession();
		boolean login = false;
		
		try{
			login =  (Boolean) session.getAttribute("login");	
		} catch(NullPointerException e) {
			
		}
		
		mav.addObject("result", "0000");
		mav.addObject("login", login);
		
		if(login) {
			mav.addObject("userNo", session.getAttribute("userNo"));
			mav.addObject("userName", session.getAttribute("userName"));
			mav.addObject("userEmail", session.getAttribute("userEmail"));
		} 
		
		return mav;
	}
	
	@RequestMapping("/member/login.do")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("jsp/member/login");		
		return mav;
	}
	
	@RequestMapping("/member/joinStep1.do")
	public ModelAndView joinStep1() {
		ModelAndView mav = new ModelAndView("jsp/member/join_1");		
		return mav;
	}
	
	@RequestMapping("/member/joinStep2.do")
	public ModelAndView joinStep2() {
		ModelAndView mav = new ModelAndView("jsp/member/join_2");		
		return mav;
	}
	
	@RequestMapping("/member/joinStep3.do")
	public ModelAndView joinStep3(@RequestParam (value="email",defaultValue="" )String email) {
		ModelAndView mav = new ModelAndView("jsp/member/join_3");
		
		mav.addObject("email", email);
		return mav;
	}
	
	@RequestMapping("/member/emailCerti.do")
	public ModelAndView emailCerti(@RequestParam (value="emailCertKey",defaultValue="" )String emailCertKey) {
		ModelAndView mav = new ModelAndView("jsp/member/emailCerti");		
		mav.addObject("emailCertKey", emailCertKey);
		return mav;
	}
	
	@RequestMapping("/member/modify.do")
	public ModelAndView modify() {
		ModelAndView mav = new ModelAndView("jsp/member/modify");		
		return mav;
	}
	
	@RequestMapping("/member/memDrop.do")
	public ModelAndView memberDrop() {
		ModelAndView mav = new ModelAndView("jsp/member/mem_drop");		
		return mav;
	}
	
	@RequestMapping("/member/idSearch.do")
	public ModelAndView idSearch() {
		ModelAndView mav = new ModelAndView("jsp/member/id_search");		
		return mav;
	}
	
	@RequestMapping("/member/idSearchResult.do")
	public ModelAndView idSearchResult(@RequestParam (value="userId",defaultValue="" )String userId) {
		ModelAndView mav = new ModelAndView("jsp/member/id_search_result");	
		
		mav.addObject("userId", userId);
		return mav;
	}
	
	@RequestMapping("/member/pwSearch.do")
	public ModelAndView pwSearch() {
		ModelAndView mav = new ModelAndView("jsp/member/pw_search");		
		return mav;
	}
	
	@RequestMapping("/member/pwSearchResult.do")
	public ModelAndView pwSearchResult(@RequestParam (value="userName",defaultValue="" )String userName) {
		ModelAndView mav = new ModelAndView("jsp/member/pw_search_result");	
		
		mav.addObject("userName", userName);
		return mav;
	}
	
	@RequestMapping("/member/pwChange.do")
	public ModelAndView pwChange() {
		ModelAndView mav = new ModelAndView("jsp/member/pw_change");		
		return mav;
	}
	
	@RequestMapping("/member/myReserve.do")
	public ModelAndView myReservation() {
		ModelAndView mav = new ModelAndView("jsp/member/my_reserve");		
		return mav;
	}
	
	@RequestMapping("/member/privatePolicy.do")
	public ModelAndView privatePolicy() {
		ModelAndView mav = new ModelAndView("jsp/member/private_policy");		
		return mav;
	}
	
	@RequestMapping("/member/emailPolicy.do")
	public ModelAndView emailPolicy() {
		ModelAndView mav = new ModelAndView("jsp/member/email_policy");		
		return mav;
	}
	
	@RequestMapping("/member/copyrightPolicy.do")
	public ModelAndView copyrightPolicy() {
		ModelAndView mav = new ModelAndView("jsp/member/copyright_policy");		
		return mav;
	}
	
	@RequestMapping("/member/accessTerms.do")
	public ModelAndView accessTerms() {
		ModelAndView mav = new ModelAndView("jsp/member/access_terms");		
		return mav;
	}
	
	@RequestMapping("/member/inquiryList.do")
	public ModelAndView inquiryList() {
		ModelAndView mav = new ModelAndView("jsp/member/inquiry_list");		
		return mav;
	}
	
	@RequestMapping("/member/inquiryDetail.do")
	public ModelAndView inquiryDetail(@RequestParam (value="no",defaultValue="" )String no) {
		ModelAndView mav = new ModelAndView("jsp/member/inquiry_detail");		
		
		mav.addObject("no", no);
		return mav;
	}
}
