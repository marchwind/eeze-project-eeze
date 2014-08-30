package com.kobaco.smartad.controller;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.PmcSessionInfo;
import com.kobaco.smartad.model.service.ReserveInfo;
import com.kobaco.smartad.model.service.UserInfo;
import com.kobaco.smartad.service.PmcUserService;
import com.kobaco.smartad.utils.CommonCode;
import com.kobaco.smartad.utils.CommonMsg;


@Controller
@SessionAttributes("sessionManagerInfo")
@RequestMapping(value="/pmc/user")
public class PmcUserController {

	private static final Logger logger = LoggerFactory.getLogger(PmcUserController.class);
	
	@Autowired
	public PmcUserService userService;
	
	@Autowired
	@Qualifier("props")
	private Properties props;
		
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(@RequestParam (value="id",defaultValue="" )String id){	
      
        return "pmcuser/"+id+"Form";
   }
	@ModelAttribute("sessionManagerInfo")
	public PmcSessionInfo setSessionManagerinfo() {
		return new PmcSessionInfo(){{
			setLogin(false);
		}};
	}

	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<UserInfo> get(@ModelAttribute UserInfo user,
														@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser){				
		CommonSingleResult<UserInfo> is = userService.getPmcUserGet(info, new PmcMnagerInfo(sessUser), user);			
		return is;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<UserInfo>  list(@ModelAttribute UserInfo user,
														 @ModelAttribute CommonPage cp,
														 @ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														 @ModelAttribute PmcMnagerInfo info ){	
		CommonListResult<UserInfo> is = userService.getPmcUserSearch(info,cp,new PmcMnagerInfo(sessUser),user);		
		return is;
	}
	@RequestMapping(value = "/reserve/list", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<ReserveInfo>  reserveList(@ModelAttribute UserInfo user,
														 @ModelAttribute CommonPage cp,
														 @ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														 @ModelAttribute PmcMnagerInfo info ){	
		CommonListResult<ReserveInfo> is = userService.getPmcReserveList(info,cp,new PmcMnagerInfo(sessUser),user);		
		return is;
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<UserInfo> update(@ModelAttribute UserInfo user,
														@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser){				
		CommonSingleResult<UserInfo> is = userService.getPmcUserUpdate(info, new PmcMnagerInfo(sessUser), user);			
		return is;
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<UserInfo> add(@ModelAttribute UserInfo user,
														@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser){				
		CommonSingleResult<UserInfo> is = userService.getPmcUserAdd(info, new PmcMnagerInfo(sessUser), user);	
		return is;
	}
	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<UserInfo> updateStatus(@ModelAttribute UserInfo user,
														@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser){				
		CommonSingleResult<UserInfo> is = userService.getPmcUserUpdateStatus(info, new PmcMnagerInfo(sessUser), user);	
		return is;
	}
	
	@RequestMapping(value = "/setStatusBlock", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<UserInfo> setStatusBlock(@ModelAttribute UserInfo user,
														@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser){
		
		CommonSingleResult<UserInfo> result = new CommonSingleResult<UserInfo>();
		if(sessUser==null ||!sessUser.isLogin() ||info.getManagerMode()==null ||
			info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
			return result;
		}
		
		if(user ==null || user.getUserNo()==null || user.getUserNo().equals("")) {
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		
		user.setUserStatus(props.getProperty("user.state.block.nml"));
		return userService.getPmcUserUpdateStatus(info, new PmcMnagerInfo(sessUser), user);	
	}
	
	@RequestMapping(value = "/setStatusNormal", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<UserInfo> setStatusNormal(@ModelAttribute UserInfo user,
														@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser){
		
		CommonSingleResult<UserInfo> result = new CommonSingleResult<UserInfo>();
		if(sessUser==null ||!sessUser.isLogin() ||info.getManagerMode()==null ||
			info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
			return result;
		}
		if(user ==null || user.getUserNo()==null || user.getUserNo().equals("")) {
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		
		user.setUserStatus(props.getProperty("user.state.act.nml"));
		return userService.getPmcUserUpdateStatus(info, new PmcMnagerInfo(sessUser), user);	
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<UserInfo> delete(@ModelAttribute UserInfo user,
														@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser){	
		
		CommonSingleResult<UserInfo> result = new CommonSingleResult<UserInfo>();
		if(sessUser==null ||!sessUser.isLogin() ||info.getManagerMode()==null ||
			info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
			return result;
		}
		if(user ==null || user.getUserNo()==null || user.getUserNo().equals("")){
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		
		return userService.delete(user);	
	}
	
}
