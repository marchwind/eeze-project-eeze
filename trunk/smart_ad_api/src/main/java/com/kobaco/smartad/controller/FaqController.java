package com.kobaco.smartad.controller;

import java.util.ArrayList;
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

import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.FaqInfo;
import com.kobaco.smartad.service.FaqService;


@Controller
@RequestMapping(value="/faq")
public class FaqController {

	private static final Logger logger = LoggerFactory.getLogger(FaqController.class);
	
	@Autowired
	public FaqService faqService;
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(@RequestParam (value="id",defaultValue="" )String id){	
      
        return "faq/"+id+"Form";
   }
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<FaqInfo> list(@ModelAttribute CommonPage page){
				
		CommonListResult<FaqInfo> is = faqService.getFaqList(page);
			
		return is;
	}
	
}
