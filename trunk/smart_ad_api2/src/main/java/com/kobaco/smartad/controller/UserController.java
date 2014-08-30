package com.kobaco.smartad.controller;

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
import com.kobaco.smartad.model.service.ReserveInfo;
import com.kobaco.smartad.model.service.UserInfo;
import com.kobaco.smartad.service.ReserveService;
import com.kobaco.smartad.service.UserService;
import com.kobaco.smartad.utils.CommonMsg;

@Controller
@RequestMapping(value="/user")
@SessionAttributes("sessionUserInfo")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	public UserService userService;
	@Autowired
	public ReserveService reserveService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String form(@RequestParam (value="id", defaultValue="" )String id){	
	    return "user/"+id+"Form";
	}
	
	@ModelAttribute("sessionUserInfo")
	public UserInfo setSessionUserinfo() {
		return new UserInfo(){{
			setLogin(false);
		}};
	}

	@RequestMapping(value = "/session", method = RequestMethod.GET)
	public @ResponseBody CommonSingleResult<UserInfo> getSession(
			@ModelAttribute("sessionUserInfo") UserInfo sessUser, 
			HttpSession session) {
		if (sessUser.getUserNo() == null || !sessUser.isLogin()) {
			return new CommonSingleResult<UserInfo> (
					new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized),
					null);
		} else {
			return new CommonSingleResult<UserInfo> (
					new CommonResult(CommonMsg.successCode, CommonMsg.successMsg),
					sessUser);
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<UserInfo> login(
			@ModelAttribute("sessionUserInfo") UserInfo sessUser, 
			@ModelAttribute UserInfo user, 
			HttpSession session) {
		// HttpSession session, HttpServletRequest request
		
		CommonSingleResult<UserInfo> is = userService.login(user.getUserId(), user.getUserPassword());
		if(is.getResult().getResultCode().equals(CommonMsg.successCode)) {
			sessUser.setLogin(true);
			sessUser.setUserNo(is.getInfo().getUserNo());
			sessUser.setUserId(is.getInfo().getUserId());
			sessUser.setUserName(is.getInfo().getUserName());
			sessUser.setUserEmail(is.getInfo().getUserEmail());
			sessUser.setUserPassword(null);
		}
		
		return is;
	}
	

	
	@RequestMapping("/logout")
	public @ResponseBody CommonSingleResult<UserInfo> logout(
			@ModelAttribute("sessionUserInfo") UserInfo sessUser, 
			SessionStatus session) {
		UserInfo ui = new UserInfo();
		if (sessUser != null) { 
			ui.setUserNo(sessUser.getUserNo());
			ui.setUserId(sessUser.getUserId());
			ui.setUserName(sessUser.getUserName());
		}
		userService.logout(sessUser.getUserNo());
		session.setComplete();
		logger.info("/logout");
		
		return new CommonSingleResult<UserInfo>(
				new CommonResult(CommonMsg.successCode,CommonMsg.successMsg),
				ui); 
	}
	
	@RequestMapping("/subscribe")
    public @ResponseBody CommonSingleResult<UserInfo> insert(
    		@ModelAttribute UserInfo user ){
		user.setAuthor("WEB");
		CommonSingleResult<UserInfo> result = userService.subscribe(user);        
        return result;
   }


	@RequestMapping("/update")
    public @ResponseBody CommonSingleResult<UserInfo> update(
    		@ModelAttribute("sessionUserInfo") UserInfo sessUser, 
    		@ModelAttribute UserInfo userInfo, 
			SessionStatus session){		
		
		// check the login user
		if ( sessUser.getUserNo() == null  ) {
			return new CommonSingleResult<UserInfo> (
					new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized),
					null);
		}
		
		CommonSingleResult<UserInfo> result;
		
		if ( sessUser.getUserNo().equals(userInfo.getUserNo()) ) {
			result = userService.update(userInfo);  
		} else {
			result = new CommonSingleResult<UserInfo> (
					new CommonResult(CommonMsg.failCodeInvalidData, CommonMsg.failMsgInvalidData),
					null);
		}
  
        return result;
   }
	
	@RequestMapping("/unsubscribe")
    public @ResponseBody CommonSingleResult<UserInfo> delete(
    		@ModelAttribute("sessionUserInfo") UserInfo sessUser, 
    		@ModelAttribute UserInfo userInfo, 
			SessionStatus session){			
		
		// check the login user
		if ( !(sessUser.getUserNo() != null && sessUser.isLogin()) ) {
			return new CommonSingleResult<UserInfo> (
					new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized),
					null);
		}
		
		CommonSingleResult<UserInfo> result;
		
		if ( sessUser.getUserNo().equals(userInfo.getUserNo()) ) {
			result = userService.unsubscribe(userInfo);  
			session.setComplete();
		} else {
			result = new CommonSingleResult<UserInfo> (
					new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized),
					null);
		}

        return result;
   }

//	@RequestMapping("/list")
//    public @ResponseBody CommonSingleResult<SAUser> list(@ModelAttribute SAUser user){		
//		CommonSingleResult<SAUser> result = userService.listUser(user);        
//        return result;
//   }
	
	@RequestMapping("/get")
    public @ResponseBody CommonSingleResult<UserInfo> info(
    		@ModelAttribute("sessionUserInfo") UserInfo sessUser, 
    		@ModelAttribute UserInfo userInfo, 
			SessionStatus session){
		
		if ( !(sessUser.getUserNo() != null && sessUser.isLogin()) ) {
			return new CommonSingleResult<UserInfo> (
					new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized),
					null);
		}
		
		CommonSingleResult<UserInfo> result;
		if ( (userInfo.getUserNo() != null && sessUser.getUserNo().equals(userInfo.getUserNo())) ||
			 (userInfo.getUserId() != null && sessUser.getUserId().equals(userInfo.getUserId())) ) {
		
			result = userService.get(userInfo);
		} else {
			result = new CommonSingleResult<UserInfo> (
					new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized),
					null);
		}			
        return result;
	}
	
	@RequestMapping("/certEmail")
    public @ResponseBody CommonResult certEmail(@RequestParam (value="emailCertKey",defaultValue="" )String emailCertKey){		
		CommonResult result = userService.emailCert(emailCertKey);        
        return result;
	}
	
	//id check
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<UserInfo> idCheck(@ModelAttribute UserInfo user) {
		CommonSingleResult<UserInfo>  is  = userService.idCheck(user);	
		return is;
	}
	@RequestMapping(value = "/reserve/list", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<ReserveInfo>  list(@ModelAttribute ReserveInfo rev,
															 @ModelAttribute CommonPage cp,
														 	 @ModelAttribute("sessionUserInfo") UserInfo sessUser){		
		CommonListResult<ReserveInfo> is = reserveService.getUserReserveList(rev, sessUser, cp);
		return is;
	}
	//id check
	@RequestMapping(value = "/findUserId", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<UserInfo> finUserdId(@ModelAttribute UserInfo user) {
		CommonSingleResult<UserInfo>  is  = userService.findUser(user);	
		return is;
	}
	@RequestMapping(value = "/findUserPassword", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<UserInfo> findUserPassword(@ModelAttribute UserInfo user) {
		CommonSingleResult<UserInfo>  is  = userService.findUserPassword(user);	
		return is;
	}
	
	/**
	 * update password
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<UserInfo> updatePassword(@ModelAttribute UserInfo user,
																	@RequestParam(value="userNewPassword",defaultValue="" )String newPw) {
		CommonSingleResult<UserInfo>  is  = userService.updatePassword(user,newPw);	
		return is;
	}
}
