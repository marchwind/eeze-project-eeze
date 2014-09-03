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

import com.kobaco.smartad.model.data.SAArchive;
import com.kobaco.smartad.model.service.ArchiveInfo;
import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.NotificationInfo;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.PmcSessionInfo;
import com.kobaco.smartad.model.service.UploadedFile;
import com.kobaco.smartad.service.ArchiveService;
import com.kobaco.smartad.utils.CommonCode;
import com.kobaco.smartad.utils.CommonMsg;


@Controller
@SessionAttributes("sessionManagerInfo")
@RequestMapping(value="/pmc/archive")
public class PmcArchiveController {

	private static final Logger logger = LoggerFactory.getLogger(PmcArchiveController.class);
	
	@Autowired
	public ArchiveService archiveService;
	
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
	public @ResponseBody CommonSingleResult<ArchiveInfo> get(@RequestParam (value="managerMode",defaultValue="" ) String mode ,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														@ModelAttribute ArchiveInfo info){
		if(info.getArchiveNo()==null){
			return new CommonSingleResult<ArchiveInfo>(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
		}
		if( !sessUser.isLogin() ||  !CommonCode.PmcManagerCode.PMC_YES.equals(mode)) {	
			return new CommonSingleResult<ArchiveInfo> (new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized),
					null);
		}	
		return archiveService.getInfo(info);
	}
	
	
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<ArchiveInfo>  list(@ModelAttribute CommonPage cp,
														 @ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														 @ModelAttribute ArchiveInfo info,
														 @RequestParam (value="managerMode",defaultValue="" ) String mode ){	
		new CommonListResult<SAArchive>();
		if(cp.getCurrentPage()<=0 ||cp.getUnitPerPage() <=0){
			return new CommonListResult<ArchiveInfo>(
					new CommonResult(CommonMsg.failCodeInvalidInput, CommonMsg.failMsgeInvalidInput),
					null,
					null);
		}	
		if( !sessUser.isLogin() ||  !CommonCode.PmcManagerCode.PMC_YES.equals(mode)) {	
			return new CommonListResult<ArchiveInfo> (new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized),
					null,
					null);
		}	
		return archiveService.getList(info, cp);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<ArchiveInfo> add(
			@RequestParam (value="managerMode",defaultValue="" ) String mode,	
			@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
			@ModelAttribute ArchiveInfo info) {
		
		if(info==null){
			return new CommonSingleResult<ArchiveInfo> (new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput),
					null);
		}
		if( !sessUser.isLogin() ||  !CommonCode.PmcManagerCode.PMC_YES.equals(mode)) {	
			return new CommonSingleResult<ArchiveInfo> (new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized),
					null);
		}
		return archiveService.add(new PmcMnagerInfo(sessUser), info);			
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<ArchiveInfo> update(
			@RequestParam (value="managerMode",defaultValue="" ) String mode,
			@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
			@ModelAttribute ArchiveInfo info,
			@ModelAttribute UploadedFile file ){		
		
		if(info==null){
			return new CommonSingleResult<ArchiveInfo> (new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput),
					null);
		}
		if( !sessUser.isLogin() ||  !CommonCode.PmcManagerCode.PMC_YES.equals(mode)) {	
			return new CommonSingleResult<ArchiveInfo> (new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized),
					null);
		}
		
		return archiveService.update(new PmcMnagerInfo(sessUser), info);			
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<ArchiveInfo> delete(
			@RequestParam (value="managerMode",defaultValue="" ) String mode,
			@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
			@ModelAttribute ArchiveInfo info){				
		if(info==null){
			return new CommonSingleResult<ArchiveInfo> (new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput),
					null);
		}
		if( !sessUser.isLogin() ||  !CommonCode.PmcManagerCode.PMC_YES.equals(mode)) {	
			return new CommonSingleResult<ArchiveInfo> (new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized),
					null);
		}
		return archiveService.delete(new PmcMnagerInfo(sessUser), info);
	}

	
	
}
