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
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.FacilityInfo;
import com.kobaco.smartad.model.service.NotificationInfo;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.service.PmcFacilityService;
import com.kobaco.smartad.service.PmcQnaService;


@Controller
@SessionAttributes("sessionManagerInfo")
@RequestMapping(value="/pmc/facility")
public class PmcFacilityController {

	private static final Logger logger = LoggerFactory.getLogger(PmcFacilityController.class);
	
	@Autowired
	public PmcFacilityService facService;
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(@RequestParam (value="id",defaultValue="" )String id){	
      
        return "pmcfaci/"+id+"Form";
   }
	@ModelAttribute("sessionManagerInfo")
	public PmcMnagerInfo setSessionManagerinfo() {
		return new PmcMnagerInfo(){{
			setLogin(false);
		}};
	}	
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<FacilityInfo> get(@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser,
														@ModelAttribute FacilityInfo facilityInfo){				
		CommonSingleResult<FacilityInfo> is = facService.getFac(info, sessUser, facilityInfo);			
		return is;
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<FacilityInfo>  list(@ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser,
														 @ModelAttribute PmcMnagerInfo info,
														 @ModelAttribute FacilityInfo facilityInfo){	
		CommonListResult<FacilityInfo> is = facService.getFacList(info,sessUser,facilityInfo);		
		return is;
	}

	@RequestMapping(value = "/usedHistory", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<FacilityInfo>  usedHistory(@ModelAttribute CommonPage cp,
														 @ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser,
														 @ModelAttribute PmcMnagerInfo info,
														 @ModelAttribute FacilityInfo facilityInfo){	
		CommonListResult<FacilityInfo> is = facService.getFacUsedHistory(info,cp,sessUser,facilityInfo);		
		return is;
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<FacilityInfo> update(@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser,
														@ModelAttribute FacilityInfo facilityInfo){				
		CommonSingleResult<FacilityInfo> is = facService.getUpdate(info, sessUser, facilityInfo);			
		return is;
	}

}
