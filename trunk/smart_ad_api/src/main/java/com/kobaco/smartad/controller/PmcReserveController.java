package com.kobaco.smartad.controller;

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
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.ReserveInfo;
import com.kobaco.smartad.model.service.UserInfo;
import com.kobaco.smartad.service.PmcReserveService;
import com.kobaco.smartad.service.PmcUserService;


@Controller
@SessionAttributes("sessionManagerInfo")
@RequestMapping(value="/pmc/reserve")
public class PmcReserveController {

	private static final Logger logger = LoggerFactory.getLogger(PmcReserveController.class);
	
	@Autowired
	public PmcReserveService reserveService;
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(@RequestParam (value="id",defaultValue="" )String id){	
      
        return "pmcreserve/"+id+"Form";
   }
	@ModelAttribute("sessionManagerInfo")
	public PmcMnagerInfo setSessionManagerinfo() {
		return new PmcMnagerInfo(){{
			setLogin(false);
		}};
	}	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<ReserveInfo> add(@ModelAttribute UserInfo user,
														@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser,
														@ModelAttribute ReserveInfo rev,
														@RequestParam(value="reserveArray[]",defaultValue="")List<String> k){				
		CommonSingleResult<ReserveInfo> is = reserveService.getPmcReserveAdd(info, sessUser, user, rev, k);			
		return is;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<ReserveInfo>  list(@ModelAttribute CommonPage cp,
														 @ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser,
														 @ModelAttribute ReserveInfo rev,
														 @ModelAttribute PmcMnagerInfo info ){	
		CommonListResult<ReserveInfo> is = reserveService.getPmcReserveList(info,cp,sessUser,rev);		
		return is;
	}
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<ReserveInfo> get(@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser,
														@ModelAttribute ReserveInfo rev){				
		CommonSingleResult<ReserveInfo> is = reserveService.getPmcReserveGet(info, sessUser, rev);			
		return is;
	}
	@RequestMapping(value = "/getCheckIn", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<ReserveInfo> getCheckIn(@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser,
														@ModelAttribute ReserveInfo rev){				
		CommonSingleResult<ReserveInfo> is = reserveService.getPmcReserveGetCheckIn(info, sessUser, rev);			
		return is;
	}
	@RequestMapping(value = "/checkIn", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<ReserveInfo> checkIn(@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser,
														@ModelAttribute ReserveInfo rev){				
		CommonSingleResult<ReserveInfo> is = reserveService.setPmcReserveCheckIn(info, sessUser, rev);			
		return is;
	}
	@RequestMapping(value = "/cancle", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<ReserveInfo> cancle(@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser,
														@ModelAttribute ReserveInfo rev){				
		CommonSingleResult<ReserveInfo> is = reserveService.pmcReserveCancle(info, sessUser, rev);			
		return is;
	}
	@RequestMapping(value = "/checkOut", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<ReserveInfo> checkOut(@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser,
														@ModelAttribute ReserveInfo rev){				
		CommonSingleResult<ReserveInfo> is = reserveService.pmcReserveCheckOut(info, sessUser, rev);			
		return is;
	}
	
}
