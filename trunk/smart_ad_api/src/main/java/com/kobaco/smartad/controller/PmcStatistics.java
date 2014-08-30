package com.kobaco.smartad.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.StatisticsInfo;
import com.kobaco.smartad.service.PmcStatisticsService;
import com.kobaco.smartad.utils.CommonCode;
import com.kobaco.smartad.utils.CommonMsg;

@Controller
@SessionAttributes("sessionManagerInfo")
@RequestMapping(value="/pmc/statistics")
public class PmcStatistics {

	private static final Logger logger = LoggerFactory.getLogger(PmcStatistics.class);
	
	@Autowired
	public PmcStatisticsService statisticsService;
	
	@ModelAttribute("sessionManagerInfo")
	public PmcMnagerInfo setSessionManagerinfo() {
		return new PmcMnagerInfo(){{
			setLogin(false);
		}};
	}	
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(@RequestParam (value="id",defaultValue="" )String id){	
      
        return "manager/"+id+"Form";
    }
	
	
	@RequestMapping(value = "/facility", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<StatisticsInfo>  facility(
			@RequestParam (value="managerMode",defaultValue="" ) String mode,
			@ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser ){	
		
//		if( !sessUser.isLogin() ||  !CommonCode.PmcManagerCode.PMC_YES.equals(mode)) {	
//			return  new CommonListResult<StatisticsInfo> (new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized),
//					null,
//					null);
//		}
		return statisticsService.getFacility(sessUser);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<StatisticsInfo>  user(
			@RequestParam (value="managerMode",defaultValue="" ) String mode,
			@ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser ){
//		if( !sessUser.isLogin() ||  !CommonCode.PmcManagerCode.PMC_YES.equals(mode)) {	
//			return  new CommonListResult<StatisticsInfo> (new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized),
//					null,
//					null);
//		}
		return statisticsService.getUser(sessUser);
	}
	
	@RequestMapping(value = "/reserve", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<StatisticsInfo>  reserve(
			@RequestParam (value="managerMode",defaultValue="" ) String mode,
			@ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser ){
//		if( !sessUser.isLogin() ||  !CommonCode.PmcManagerCode.PMC_YES.equals(mode)) {	
//			return  new CommonListResult<StatisticsInfo> (new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized),
//					null,
//					null);
//		}
		return statisticsService.getReserve(sessUser);
	}
}