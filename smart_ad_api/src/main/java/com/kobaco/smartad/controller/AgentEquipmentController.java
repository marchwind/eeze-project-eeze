package com.kobaco.smartad.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.EquipInfo;
import com.kobaco.smartad.model.service.EquipLogInfo;
import com.kobaco.smartad.service.PmcEquipmentService;
import com.kobaco.smartad.utils.CommonMsg;


@Controller
@RequestMapping(value="/agent/equip")
public class AgentEquipmentController {

	private static final Logger logger = LoggerFactory.getLogger(AgentEquipmentController.class);
	
	@Autowired
	public PmcEquipmentService equipService;
		
	@RequestMapping(value = "/subscribe", method = RequestMethod.POST)
	public @ResponseBody CommonResult subscribe(@ModelAttribute EquipInfo equipInfo){				
		return equipService.agentSubscribe(equipInfo);
	}
	//public @ResponseBody CommonResult delete(@ModelAttribute EquipLogInfo equipLog){
	@RequestMapping(value = "/addLog", 
			method = RequestMethod.POST, 
			produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            headers="Accept=application/json")
	public @ResponseBody CommonResult addLog(@RequestBody EquipLogInfo equipLog){
		logger.debug("Process List : " + equipLog.getEquipProcessesToString());
//		return new CommonResult(CommonMsg.successCode, CommonMsg.successMsg);
		return equipService.addLog(equipLog);			
	}
}
