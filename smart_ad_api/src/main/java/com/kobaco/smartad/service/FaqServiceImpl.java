package com.kobaco.smartad.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;








import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kobaco.smartad.dao.CommonDao;
import com.kobaco.smartad.model.data.ParamsCommonFilter;
import com.kobaco.smartad.model.data.ParamsCommonOrder;
import com.kobaco.smartad.model.data.ParamsCommonPage;
import com.kobaco.smartad.model.data.SAFaq;
import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.FaqInfo;
import com.kobaco.smartad.model.service.QnaInfo;
import com.kobaco.smartad.utils.CommonMsg;
@Service
public class FaqServiceImpl implements FaqService {
	
	@Autowired
	CommonDao<SAFaq> faqDao;


	@Override
	public CommonListResult<FaqInfo> getFaqList(CommonPage page) {
		CommonListResult<FaqInfo> result = new CommonListResult<FaqInfo>();	
		
		if(page.getCurrentPage()<=0 ||page.getUnitPerPage() <=0){
			CommonResult k = new CommonResult();			
			k.setResultCode(CommonMsg.failCodeInvalidInput);
			k.setResultMsg(CommonMsg.failMsgeInvalidInput);	
			result.setResult(k);
			return result;
		}
		
		int totalCount = faqDao.count(new SAFaq());
		int totalPage =  (int) Math.ceil((double)totalCount /(double) page.getUnitPerPage() );	
		
		ParamsCommonPage list = new ParamsCommonPage();
		list.setCurrentPage(page.getCurrentPage());
		list.setUnitPerPage(page.getUnitPerPage());
		
		List<SAFaq> info = faqDao.list(new SAFaq(), list);	
		
		if(info.size()>0){
			List<FaqInfo> resultList = new ArrayList<FaqInfo>();
			
			for(SAFaq is : info){
				FaqInfo temp = new FaqInfo();
				temp.setFaqNo(is.getFAQ_NO());
				temp.setQuestion(is.getQ_CNTT());
				temp.setAnswer(is.getA_CNTT());
				resultList.add(temp);
			}
			
			CommonResult k = new CommonResult();
			k.setResultCode(CommonMsg.successCode);
			k.setResultMsg(CommonMsg.successMsg);
			page.setTotalCount(totalCount);
			page.setTotalPage(totalPage);
			result.setList(resultList);
			result.setPage(page);
			result.setResult(k);
			return result;
			
		}else{
			CommonResult k = new CommonResult();			
			k.setResultCode(CommonMsg.failCodeNotFound);
			k.setResultMsg(CommonMsg.failMsgNotFound);	
			result.setResult(k);
			return result;
		}		
	}


	@Override
	public CommonListResult<FaqInfo> insertFaq(FaqInfo faq) {
		// TODO Auto-generated method stub
	CommonListResult<FaqInfo> result = new CommonListResult<FaqInfo>();	
		
		if(faq.getAnswer()==null || faq.getQuestion()==null || 
			faq.getAnswer().equals("") ||faq.getQuestion().equals("") ){
			CommonResult k = new CommonResult();			
			k.setResultCode(CommonMsg.failCodeInvalidInput);
			k.setResultMsg(CommonMsg.failMsgeInvalidInput);	
			result.setResult(k);
			return result;
		}
	
		SAFaq resultFaq = faqDao.insert(new SAFaq(faq));
		if(resultFaq!= null){

			CommonResult k = new CommonResult();
			k.setResultCode(CommonMsg.successCode);
			k.setResultMsg(CommonMsg.successMsg);
			result.setResult(k);
			return result;
		}else {
			CommonResult k = new CommonResult();			
			k.setResultCode(CommonMsg.failCodeNotFound);
			k.setResultMsg(CommonMsg.failMsgNotFound);	
			result.setResult(k);			
			return result;
		}
	
		
	}		
}
