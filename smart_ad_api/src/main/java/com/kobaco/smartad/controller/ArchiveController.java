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
@RequestMapping(value="/archive")
public class ArchiveController {

	private static final Logger logger = LoggerFactory.getLogger(ArchiveController.class);
	
	@Autowired
	public ArchiveService archiveService;

	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<ArchiveInfo> get(@ModelAttribute ArchiveInfo info){
		if(info.getArchiveNo()==null){
			return new CommonSingleResult<ArchiveInfo>(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
		}
		return archiveService.getInfo(info);
	}
	
	
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<ArchiveInfo>  list(@ModelAttribute CommonPage cp,
			@ModelAttribute ArchiveInfo info){	
		new CommonListResult<SAArchive>();
		if(cp.getCurrentPage()<=0 ||cp.getUnitPerPage() <=0){
			return new CommonListResult<ArchiveInfo>(
					new CommonResult(CommonMsg.failCodeInvalidInput, CommonMsg.failMsgeInvalidInput),
					null,
					null);
		}	
		return archiveService.getList(info, cp);
	}
}
