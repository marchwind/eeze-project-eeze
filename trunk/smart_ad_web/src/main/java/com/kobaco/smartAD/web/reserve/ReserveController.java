package com.kobaco.smartAD.web.reserve;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("reserve.ReserveController")
public class ReserveController {

	@RequestMapping("/reserve/guide.do")
	public ModelAndView reserveGuide() {
		ModelAndView mav = new ModelAndView("jsp/reserve/guide");		
		return mav;
	}
	
	@RequestMapping("/reserve/situation.do")
	public ModelAndView reserveSituation(@RequestParam (value="id",defaultValue="" )String id,
			@RequestParam (value="y",defaultValue="" )String y,
			@RequestParam (value="m",defaultValue="" )String m) {
		ModelAndView mav = new ModelAndView("jsp/reserve/situation");	
		
		mav.addObject("id", id);
		mav.addObject("y", y);
		mav.addObject("m", m);
		return mav;
	}
	
	@RequestMapping("/reserve/popupSituation.do")
	public ModelAndView reservePopupSituation(@RequestParam (value="id",defaultValue="" )String id) {
		ModelAndView mav = new ModelAndView("jsp/reserve/popupSituation");
		
		mav.addObject("id", id);		
		return mav;
	}
	
	@RequestMapping("/reserve/reserveCal.do")
	public ModelAndView reserveCal() {
		ModelAndView mav = new ModelAndView("jsp/reserve/reserve_cal");
		return mav;
	}
	
	@RequestMapping("/reserve/reserveInput.do")
	public ModelAndView reserveInput() {
		ModelAndView mav = new ModelAndView("jsp/reserve/reserve_input");
		return mav;
	}
	
	@RequestMapping("/reserve/reserveConfirm.do")
	public ModelAndView reserveConfirm() {
		ModelAndView mav = new ModelAndView("jsp/reserve/reserve_confirm");		
		return mav;
	}
	
	@RequestMapping("/reserve/reserveResult.do")
	public ModelAndView reserveResult() {
		ModelAndView mav = new ModelAndView("jsp/reserve/reserve_result");		
		return mav;
	}
}
