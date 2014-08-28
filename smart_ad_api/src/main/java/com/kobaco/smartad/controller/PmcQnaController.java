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
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.PmcSessionInfo;
import com.kobaco.smartad.model.service.QnaInfo;
import com.kobaco.smartad.service.PmcQnaService;


@Controller
@SessionAttributes("sessionManagerInfo")
@RequestMapping(value="/pmc/qna")
public class PmcQnaController {

	private static final Logger logger = LoggerFactory.getLogger(PmcQnaController.class);
	
	@Autowired
	public PmcQnaService qnaService;
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(@RequestParam (value="id",defaultValue="" )String id){	
      
        return "pmcqna/"+id+"Form";
   }
	@ModelAttribute("sessionManagerInfo")
	public PmcSessionInfo setSessionManagerinfo() {
		return new PmcSessionInfo(){{
			setLogin(false);
		}};
	}	
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<QnaInfo> get(@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														@ModelAttribute QnaInfo QnaInfo){				
		CommonSingleResult<QnaInfo> is = qnaService.getPmcQna(info, new PmcMnagerInfo(sessUser), QnaInfo);			
		return is;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<QnaInfo>  list(@ModelAttribute CommonPage cp,
														 @ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														 @ModelAttribute PmcMnagerInfo info ){	
		CommonListResult<QnaInfo> is = qnaService.getPmcList(info,cp,new PmcMnagerInfo(sessUser));		
		return is;
	}

	@RequestMapping(value = "/answer", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<QnaInfo> update(@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														@ModelAttribute QnaInfo QnaInfo){				
		CommonSingleResult<QnaInfo> is = qnaService.getPmcAnswer(info, new PmcMnagerInfo(sessUser), QnaInfo);			
		return is;
	}

}
