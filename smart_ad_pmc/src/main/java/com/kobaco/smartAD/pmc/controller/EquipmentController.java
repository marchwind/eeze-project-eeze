package com.kobaco.smartAD.pmc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("controller.EquipmentController")
@RequestMapping("/equipment")
public class EquipmentController {

	@RequestMapping("/list.do")
	public ModelAndView equipmentList(@RequestParam (value="type",defaultValue="common" )String type) {
		ModelAndView mav = new ModelAndView("jsp/equipment/list");		
		mav.addObject("type", type);
		return mav;
	}
	
	@RequestMapping("/commonList.do")
	public ModelAndView equipmentCommonList() {
		ModelAndView mav = new ModelAndView("jsp/equipment/listHTML");		
		return mav;
	}
	
	@RequestMapping("/detail.do")
	public ModelAndView equipmentDetail(@RequestParam (value="no",defaultValue="common" )String no) {
		ModelAndView mav = new ModelAndView("jsp/equipment/detail");		
		mav.addObject("no", no);
		return mav;
	}
	
	@RequestMapping("/detailHTML.do")
	public ModelAndView equipmentDetailHTML(@RequestParam (value="no",defaultValue="common" )String no) {
		ModelAndView mav = new ModelAndView("jsp/equipment/equipmentDetailHTML");	
		mav.addObject("no", no);
		return mav;
	}
	
	@RequestMapping("/modifyHTML.do")
	public ModelAndView equipmentModifyHTML(@RequestParam (value="no",defaultValue="common" )String no) {
		ModelAndView mav = new ModelAndView("jsp/equipment/equipmentModifyHTML");	
		mav.addObject("no", no);
		return mav;
	}
	
	@RequestMapping("/usedList.do")
	public ModelAndView equipmentUsedList(@RequestParam (value="no",defaultValue="common" )String no) {
		ModelAndView mav = new ModelAndView("jsp/equipment/usedList");		
		mav.addObject("no", no);
		return mav;
	}
	
	@RequestMapping("/modify.do")
	public ModelAndView equipmentModify(@RequestParam (value="no",defaultValue="common" )String no) {
		ModelAndView mav = new ModelAndView("jsp/equipment/modify");		
		mav.addObject("no", no);
		return mav;
	}
	
	@RequestMapping("/add.do")
	public ModelAndView equipmentAdd() {
		ModelAndView mav = new ModelAndView("jsp/equipment/add");		
		return mav;
	}
	
	@RequestMapping("/checkHTML.do")
	public ModelAndView equipmentCheckHTML() {
		ModelAndView mav = new ModelAndView("jsp/equipment/checkHTML");		
		return mav;
	}
	
	@RequestMapping("/smartList.do")
	public ModelAndView equipmentSmartList() {
		ModelAndView mav = new ModelAndView("jsp/equipment/smartListHTML");		
		return mav;
	}
	
	@RequestMapping("/smartDetail.do")
	public ModelAndView equipmentSmartDetail(@RequestParam (value="no",defaultValue="common" )String no) {
		ModelAndView mav = new ModelAndView("jsp/equipment/smartDetail");		
		mav.addObject("no", no);
		return mav;
	}
	
	@RequestMapping("/smartDetailHTML.do")
	public ModelAndView equipmentSmartDetailHTML(@RequestParam (value="no",defaultValue="common" )String no) {
		ModelAndView mav = new ModelAndView("jsp/equipment/smartDetailHTML");
		mav.addObject("no", no);
		return mav;
	}
	
	@RequestMapping("/smartModifyHTML.do")
	public ModelAndView equipmentSmartModifyHTML(@RequestParam (value="no",defaultValue="common" )String no) {
		ModelAndView mav = new ModelAndView("jsp/equipment/smartModifyHTML");		
		mav.addObject("no", no);
		return mav;
	}
	
	@RequestMapping("/smartRentHTML.do")
	public ModelAndView equipmentSmartRentHTML(@RequestParam (value="no",defaultValue="common" )String no) {
		ModelAndView mav = new ModelAndView("jsp/equipment/rentPopupHTML");		
		mav.addObject("no", no);
		return mav;
	}
		
	@RequestMapping("/smartReturnHTML.do")
	public ModelAndView equipmentSmartReturnHTML(
				@RequestParam (value="no",defaultValue="common" )String no,
				@RequestParam (value="equipRentalNo",defaultValue="common" )String equipRentalNo) {
		ModelAndView mav = new ModelAndView("jsp/equipment/returnPopupHTML");
		mav.addObject("no", no);
		mav.addObject("equipRentalNo", equipRentalNo);
		return mav;
	}
	
	@RequestMapping("/nfc.do")
	public ModelAndView equipmentNFC(@RequestParam (value="nfcTagId",defaultValue="" )String nfcTagId) {
		ModelAndView mav = new ModelAndView("jsp/equipment/nfc");		
		mav.addObject("nfcTagId", nfcTagId);
		return mav;
	}
}
