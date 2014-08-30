package com.kobaco.smartad.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.support.SessionStatus;

import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.QnaInfo;
import com.kobaco.smartad.model.service.UserInfo;
import com.kobaco.smartad.service.PmcManagerService;
import com.kobaco.smartad.utils.CommonMsg;


@Controller
@SessionAttributes("sessionManagerInfo")
@RequestMapping(value="/pmc/manager")
public class PmcManagerController {

	private static final Logger logger = LoggerFactory.getLogger(PmcManagerController.class);
	
	@Autowired
	public PmcManagerService managerService;
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(@RequestParam (value="id",defaultValue="" )String id){	
      
        return "manager/"+id+"Form";
   }
	@ModelAttribute("sessionManagerInfo")
	public PmcMnagerInfo setSessionManagerinfo() {
		return new PmcMnagerInfo(){{
			setLogin(false);
		}};
	}

	@RequestMapping(value = "/getSession", method = RequestMethod.GET)
	public @ResponseBody CommonSingleResult<PmcMnagerInfo> getSession(@ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser,
			HttpSession session) {	
		if (sessUser.getManagerNo() == null || !sessUser.isLogin()) {
			return new CommonSingleResult<PmcMnagerInfo> (
					new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized));
		} else {
			sessUser.setManagerPassword(null);
			return new CommonSingleResult<PmcMnagerInfo> (
					new CommonResult(CommonMsg.successCode, CommonMsg.successMsg),
					sessUser);
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<PmcMnagerInfo> login(
			@ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser, 
			@ModelAttribute PmcMnagerInfo manager, 
			HttpSession session) {
		// HttpSession session, HttpServletRequest request
		
		CommonSingleResult<PmcMnagerInfo> is = managerService.login(manager);
		if(is.getResult().getResultCode().equals(CommonMsg.successCode)) {
			sessUser.setLogin(true);
			sessUser.setManagerId(is.getInfo().getManagerId());
			sessUser.setManagerName(is.getInfo().getManagerName());
			sessUser.setManagerNo(is.getInfo().getManagerNo());
			sessUser.setManagerEmail(is.getInfo().getManagerEmail());
			sessUser.setLoginDate(new Date());
		}
			return is;
	}
	
	@RequestMapping("/logout")
	public @ResponseBody CommonSingleResult<PmcMnagerInfo> logout(
			@ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser, 
			SessionStatus session) {
		session.setComplete();
		CommonSingleResult<PmcMnagerInfo> is = managerService.logout(sessUser);
		return is;		
	}	
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<PmcMnagerInfo> get(@ModelAttribute PmcMnagerInfo info
			,@ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser){				
		CommonSingleResult<PmcMnagerInfo> is = managerService.getPmcMangerList(info, sessUser);			
		return is;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<PmcMnagerInfo>  list(@ModelAttribute PmcMnagerInfo info,
														 @ModelAttribute CommonPage cp,
														 @ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser ){	
		CommonListResult<PmcMnagerInfo> is = managerService.getPmcMnagerList(info,cp,sessUser);
		return is;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<PmcMnagerInfo> add(@ModelAttribute PmcMnagerInfo info
			,@ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser){				
		CommonSingleResult<PmcMnagerInfo> is = managerService.getPmcMangerRegister(info, sessUser)	;	
		return is;
	}
	
	@RequestMapping(value = "/subscribe", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<PmcMnagerInfo> subscribe(@ModelAttribute PmcMnagerInfo info){				
		CommonSingleResult<PmcMnagerInfo> is = managerService.getPmcMangerSubscribe(info);		
		return is;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<PmcMnagerInfo> update(@ModelAttribute PmcMnagerInfo info
			,@ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser){				
		CommonSingleResult<PmcMnagerInfo> is = managerService.getPmcMangerUpdate(info,sessUser);		
		return is;
	}
	
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<PmcMnagerInfo> modify(@ModelAttribute PmcMnagerInfo info
			,@ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser){				
		CommonSingleResult<PmcMnagerInfo> is = managerService.getPmcMangerModify(info,sessUser);		
		return is;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<PmcMnagerInfo> delete(@ModelAttribute PmcMnagerInfo info
			,@ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser){				
		CommonSingleResult<PmcMnagerInfo> is = managerService.getPmcMangerDelete(info,sessUser);		
		return is;
	}
	
	@RequestMapping(value = "/ack", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<PmcMnagerInfo> ack(@ModelAttribute PmcMnagerInfo info
			,@ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser){				
		CommonSingleResult<PmcMnagerInfo> is = managerService.getPmcMangerAck(info,sessUser);		
		return is;
	}
	
	@RequestMapping(value = "/passwordReset", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<PmcMnagerInfo> passwordReset(@ModelAttribute PmcMnagerInfo info
			,@ModelAttribute("sessionManagerInfo") PmcMnagerInfo sessUser){				
		CommonSingleResult<PmcMnagerInfo> is = managerService.getPmcMangerReset(info,sessUser);		
		return is;
	}
}
