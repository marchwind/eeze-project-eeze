package com.kobaco.smartad.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.EquipStatisticsInfo;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.PmcSessionInfo;
import com.kobaco.smartad.model.service.StatisticsInfo;
import com.kobaco.smartad.service.PmcStatisticsService;
import com.kobaco.smartad.utils.CommonMsg;

@Controller
@SessionAttributes("sessionManagerInfo")
@RequestMapping(value="/pmc/statistics")
public class PmcStatistics {

	private static final Logger logger = LoggerFactory.getLogger(PmcStatistics.class);
	
	@Autowired
	public PmcStatisticsService statisticsService;
	
	@ModelAttribute("sessionManagerInfo")
	public PmcSessionInfo setSessionManagerinfo() {
		return new PmcSessionInfo(){{
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
			@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser ){	
		
//		if( !sessUser.isLogin() ||  !CommonCode.PmcManagerCode.PMC_YES.equals(mode)) {	
//			return  new CommonListResult<StatisticsInfo> (new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized),
//					null,
//					null);
//		}
		return statisticsService.getFacility(new PmcMnagerInfo(sessUser));
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<StatisticsInfo>  user(
			@RequestParam (value="managerMode",defaultValue="" ) String mode,
			@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser ){
//		if( !sessUser.isLogin() ||  !CommonCode.PmcManagerCode.PMC_YES.equals(mode)) {	
//			return  new CommonListResult<StatisticsInfo> (new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized),
//					null,
//					null);
//		}
		return statisticsService.getUser(new PmcMnagerInfo(sessUser));
	}
	
	@RequestMapping(value = "/reserve", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<StatisticsInfo>  reserve(
			@RequestParam (value="managerMode",defaultValue="" ) String mode,
			@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser ){
//		if( !sessUser.isLogin() ||  !CommonCode.PmcManagerCode.PMC_YES.equals(mode)) {	
//			return  new CommonListResult<StatisticsInfo> (new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized),
//					null,
//					null);
//		}
		return statisticsService.getReserve(new PmcMnagerInfo(sessUser));
	}
	
	@RequestMapping(value = "/equipment", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody CommonSingleResult<EquipStatisticsInfo>  equipment(
			@RequestParam (value="managerMode",defaultValue="" ) String mode,
			@RequestParam (value="equipNo",defaultValue="" ) String equipNo,
			@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser ){
		
//		if( !sessUser.isLogin() ||  !CommonCode.PmcManagerCode.PMC_YES.equals(mode)) {	
//			return  new CommonListResult<StatisticsInfo> (new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized),
//					null,
//					null);
//		}
		//return statisticsService.getReserve(new PmcMnagerInfo(sessUser));
		
		
		EquipStatisticsInfo info = new EquipStatisticsInfo();
		List<EquipStatisticsInfo.GValue> cpuUse = new ArrayList<EquipStatisticsInfo.GValue>();
		cpuUse.add(info.new GValue("12:00", "1"));
		cpuUse.add(info.new GValue("12:10", "2"));
		cpuUse.add(info.new GValue("12:20", "2.1"));
		cpuUse.add(info.new GValue("12:30", "3"));
		cpuUse.add(info.new GValue("12:40", "4.1"));
		cpuUse.add(info.new GValue("12:50", "1.4"));
		
		List<EquipStatisticsInfo.GValue> memUse = new ArrayList<EquipStatisticsInfo.GValue>();
		memUse.add(info.new GValue("12:00", "1212344"));
		memUse.add(info.new GValue("12:10", "1323454"));
		memUse.add(info.new GValue("12:20", "2323454"));
		memUse.add(info.new GValue("12:30", "1423454"));
		memUse.add(info.new GValue("12:40", "3323454"));
		memUse.add(info.new GValue("12:50", "4323454"));
		
		List<EquipStatisticsInfo.PValue> process = new ArrayList<EquipStatisticsInfo.PValue>();
		process.add(info.new PValue("Crome", 6));
		process.add(info.new PValue("Inter Explorer", 1));
		process.add(info.new PValue("Microsoft Excel", 1));
		process.add(info.new PValue("Microsoft Word", 2));
		process.add(info.new PValue("Adobe", 3));
		
		info.setCpuUse(cpuUse);
		info.setMemoryUse(memUse);
		info.setProcess(process);
		CommonSingleResult<EquipStatisticsInfo> result = 
				new CommonSingleResult<EquipStatisticsInfo> (
						new CommonResult(CommonMsg.successCode, CommonMsg.successMsg),
						info);
		return result;
	}
}