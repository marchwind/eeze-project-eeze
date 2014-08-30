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
import com.kobaco.smartad.model.service.FaqInfo;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.PmcSessionInfo;
import com.kobaco.smartad.service.PmcFaqService;


@Controller
@SessionAttributes("sessionManagerInfo")
@RequestMapping(value="/pmc/faq")
public class PmcFaqController {

	private static final Logger logger = LoggerFactory.getLogger(PmcFaqController.class);
	
	@Autowired
	public PmcFaqService faqService;
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(@RequestParam (value="id",defaultValue="" )String id){	
      
        return "pmcfaq/"+id+"Form";
   }
	@ModelAttribute("sessionManagerInfo")
	public PmcMnagerInfo setSessionManagerinfo() {
		return new PmcMnagerInfo(){{
			setLogin(false);
		}};
	}	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<FaqInfo> get(@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														@ModelAttribute FaqInfo faqInfo){				
		CommonSingleResult<FaqInfo> is = faqService.getPmcAdd(info, new PmcMnagerInfo(sessUser), faqInfo);			
		return is;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<FaqInfo>  list(@ModelAttribute CommonPage cp,
														 @ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														 @ModelAttribute PmcMnagerInfo info ){	
		CommonListResult<FaqInfo> is = faqService.getPmcFaqList(info,cp,new PmcMnagerInfo(sessUser));		
		return is;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<FaqInfo> update(@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														@ModelAttribute FaqInfo faqInfo){				
		CommonSingleResult<FaqInfo> is = faqService.getPmcUpdate(info, new PmcMnagerInfo(sessUser), faqInfo);			
		return is;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<FaqInfo> delete(@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														@ModelAttribute FaqInfo faqInfo){				
		CommonSingleResult<FaqInfo> is = faqService.getPmcDelete(info, new PmcMnagerInfo(sessUser), faqInfo);			
		return is;
	}
}
