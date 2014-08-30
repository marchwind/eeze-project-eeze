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
import com.kobaco.smartad.model.service.NotificationInfo;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.PmcSessionInfo;
import com.kobaco.smartad.service.PmcNotificationService;


@Controller
@SessionAttributes("sessionManagerInfo")
@RequestMapping(value="/pmc/noti")
public class PmcNotificationController {

	private static final Logger logger = LoggerFactory.getLogger(PmcNotificationController.class);
	
	@Autowired
	public PmcNotificationService notiService;
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(@RequestParam (value="id",defaultValue="" )String id){	
      
        return "pmcnoti/"+id+"Form";
   }
	@ModelAttribute("sessionManagerInfo")
	public PmcSessionInfo setSessionManagerinfo() {
		return new PmcSessionInfo(){{
			setLogin(false);
		}};
	}	
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<NotificationInfo> get(@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														@ModelAttribute NotificationInfo notiInfo){				
		CommonSingleResult<NotificationInfo> is = notiService.getPmcNotification(info, new PmcMnagerInfo(sessUser), notiInfo);			
		return is;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<NotificationInfo>  list(@ModelAttribute CommonPage cp,
														 @ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														 @ModelAttribute PmcMnagerInfo info ){	
		CommonListResult<NotificationInfo> is = notiService.getPmcNotificationList(info,cp,new PmcMnagerInfo(sessUser));		
		return is;
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<NotificationInfo> add(@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														@ModelAttribute NotificationInfo notiInfo){				
		CommonSingleResult<NotificationInfo> is = notiService.getPmcNotificationAdd(info, new PmcMnagerInfo(sessUser), notiInfo);			
		return is;
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<NotificationInfo> update(@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														@ModelAttribute NotificationInfo notiInfo){				
		CommonSingleResult<NotificationInfo> is = notiService.getPmcNotificationUpdate(info, new PmcMnagerInfo(sessUser), notiInfo);			
		return is;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<NotificationInfo> delete(@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														@ModelAttribute NotificationInfo notiInfo){				
		CommonSingleResult<NotificationInfo> is = notiService.getPmcNotificationDelete(info, new PmcMnagerInfo(sessUser), notiInfo);			
		return is;
	}

	
	
}
