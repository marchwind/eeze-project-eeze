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
import com.kobaco.smartad.model.service.ManagerInfo;
import com.kobaco.smartad.model.service.QnaInfo;
import com.kobaco.smartad.model.service.UserInfo;
import com.kobaco.smartad.service.QnaService;

@Controller
@RequestMapping(value="/qna")
@SessionAttributes("sessionUserInfo")
public class QnaController {

	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	
	@Autowired
	public QnaService qnaService;
	
	@ModelAttribute("sessionUserInfo")
	public UserInfo setSessionUserinfo() {
		return new UserInfo(){{
			setLogin(false);
		}};
	}
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(@RequestParam (value="id",defaultValue="" )String id){	
      
        return "qna/"+id+"Form";
   }
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<QnaInfo> info(@ModelAttribute QnaInfo qna){
				
		CommonSingleResult<QnaInfo> is = qnaService.getQna(qna);
		
		return is;
	}
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<QnaInfo>  list(@ModelAttribute QnaInfo qna,
														 @ModelAttribute CommonPage cp,
														 @ModelAttribute("sessionUserInfo") UserInfo sessUser ){	
		System.out.println("id "+sessUser.getUserId());
		CommonListResult<QnaInfo> is = qnaService.getQnaList(cp, qna,sessUser);
		return is;
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<QnaInfo> add(@ModelAttribute QnaInfo qna,
														 @ModelAttribute ManagerInfo manager,
														 @ModelAttribute("sessionUserInfo") UserInfo sessUser){
		qna.setUser(sessUser);
		qna.setManager(manager);		
		CommonSingleResult<QnaInfo> is = qnaService.registeQna(qna);		
		return is;
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<QnaInfo> update(@ModelAttribute QnaInfo qna,@ModelAttribute("sessionUserInfo") UserInfo sessUser){
		qna.setUser(sessUser);
		CommonSingleResult<QnaInfo> is = qnaService.updateQna(qna);		
		return is;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<QnaInfo> delete(@ModelAttribute QnaInfo qna,@ModelAttribute("sessionUserInfo") UserInfo sessUser){
		qna.setUser(sessUser);
		CommonSingleResult<QnaInfo> is = qnaService.unregisteQna(qna);
		return is;
	}


	
}
