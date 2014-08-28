package com.kobaco.smartAD.web.intro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("intro.IntroController")
public class IntroController {

	@RequestMapping("/intro/intro.do")
	public ModelAndView intro() {
		ModelAndView mav = new ModelAndView("jsp/intro/intro");		
		return mav;
	}
	
	@RequestMapping("/intro/place_list.do")
	public ModelAndView placeList() {
		ModelAndView mav = new ModelAndView("jsp/intro/place_list");		
		return mav;
	}
	
	@RequestMapping("/intro/popupPlaceIntro.do")
	public ModelAndView popupPlaceIntro() {
		ModelAndView mav = new ModelAndView("jsp/intro/popup_place_intro");		
		return mav;
	}
}
