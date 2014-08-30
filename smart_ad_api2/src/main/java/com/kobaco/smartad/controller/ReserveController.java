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
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.ReserveInfo;
import com.kobaco.smartad.model.service.UserInfo;
import com.kobaco.smartad.service.ReserveService;

@Controller
@RequestMapping(value="/reserve")
@SessionAttributes("sessionUserInfo")
public class ReserveController {

	private static final Logger logger = LoggerFactory.getLogger(ReserveController.class);
	
	@Autowired
	public ReserveService reserveService;
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(@RequestParam (value="id",defaultValue="" )String id){	
      
        return "reserve/"+id+"Form";
   }
	@ModelAttribute("sessionUserInfo")
	public UserInfo setSessionUserinfo() {
		return new UserInfo(){{
			setLogin(false);
		}};
	}
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<ReserveInfo>  list(@ModelAttribute ReserveInfo rev){		
		CommonListResult<ReserveInfo> is = reserveService.getReserveList(rev);
		return is;
	}
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<ReserveInfo> delete(@ModelAttribute ReserveInfo rev
																,@ModelAttribute("sessionUserInfo") UserInfo sessUser){
		CommonSingleResult<ReserveInfo> is = reserveService.unregisteReserve(rev,sessUser);
		return is;
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<ReserveInfo> add(@ModelAttribute ReserveInfo rev
														,@ModelAttribute("sessionUserInfo") UserInfo sessUser
														,@RequestParam(value="reserveArray[]",defaultValue="")List<String> k){		
		CommonSingleResult<ReserveInfo> is = reserveService.registeReserve(rev,sessUser,k);		
		return is;
	}

}
