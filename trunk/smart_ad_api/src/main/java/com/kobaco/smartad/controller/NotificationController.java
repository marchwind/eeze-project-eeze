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

import com.kobaco.smartad.model.Page;
import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.NotificationInfo;
import com.kobaco.smartad.model.service.QnaInfo;
import com.kobaco.smartad.model.service.UserInfo;
import com.kobaco.smartad.service.NotificationService;


@Controller
@RequestMapping(value="/noti")
@SessionAttributes("sessionUserInfo")
public class NotificationController {

	private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);
	
	@Autowired
	public NotificationService notiService;
	
	@ModelAttribute("sessionUserInfo")
	public UserInfo setSessionUserinfo() {
		return new UserInfo(){{
			setLogin(false);
		}};
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(@RequestParam (value="id",defaultValue="" )String id){	
      
        return "notification/"+id+"Form";
   }
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<NotificationInfo> get(@ModelAttribute NotificationInfo notiInfo,@ModelAttribute("sessionUserInfo") UserInfo sessUser){
		
		CommonSingleResult<NotificationInfo> is = notiService.getNoti(notiInfo,sessUser);
		
		return is;
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<NotificationInfo> list(
			@ModelAttribute CommonPage page,
			@ModelAttribute NotificationInfo notiSearchInfo){
				
		CommonListResult<NotificationInfo> is = notiService.getNotificationList(notiSearchInfo, page);
		
		return is;
	}
}
