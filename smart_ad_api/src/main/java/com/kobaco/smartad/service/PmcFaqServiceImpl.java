package com.kobaco.smartad.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kobaco.smartad.dao.CommonDao;
import com.kobaco.smartad.model.data.ParamsCommonPage;
import com.kobaco.smartad.model.data.SAFaq;
import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.FaqInfo;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.utils.CommonCode;
import com.kobaco.smartad.utils.CommonMsg;
@Service
public class PmcFaqServiceImpl implements PmcFaqService {
	
	@Autowired
	CommonDao<SAFaq> faqDao;

	@Override
	public CommonListResult<FaqInfo> getPmcFaqList(PmcMnagerInfo info,
			CommonPage page, PmcMnagerInfo sessUser) {
		// TODO Auto-generated method stub
		CommonListResult<FaqInfo> result = new CommonListResult<FaqInfo>();
		if(page.getCurrentPage()<=0 ||page.getUnitPerPage() <=0){
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}	
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null ||
				info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
			return result;
		}
		int totalCount = faqDao.count(new SAFaq());
		int totalPage =  (int) Math.ceil((double)totalCount /(double) page.getUnitPerPage() );	
		
		ParamsCommonPage list = new ParamsCommonPage();
		list.setCurrentPage(page.getCurrentPage());
		list.setUnitPerPage(page.getUnitPerPage());
		
		List<SAFaq> infoResult = faqDao.list(new SAFaq(), list);	
		
		if(infoResult.size()>0){
			List<FaqInfo> resultList = new ArrayList<FaqInfo>();
			
			for(SAFaq is : infoResult){
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
	public CommonSingleResult<FaqInfo> getPmcAdd(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, FaqInfo faqInfo) {
		// TODO Auto-generated method stub
		CommonSingleResult<FaqInfo> result = new CommonSingleResult<FaqInfo>();
		if(faqInfo==null || faqInfo.getQuestion().equals("") || faqInfo.getAnswer().equals("")){
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}	
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null ||
				info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
			return result;
		}
		SAFaq k = new SAFaq(faqInfo);
		k.setREG_ID(info.getManagerId());
		k.setUPD_ID("PMC");
		SAFaq faqResult = faqDao.insert(k);
		
		if(faqResult!=null){
			result.setInfo(new FaqInfo(faqResult));					
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
			return result;
		}else{
			result.setResult(new CommonResult(CommonMsg.FailCodeFaqService.II_FAQ_REGISTER,
					CommonMsg.FailMsgCRUDService.II_REGISTER));
			return result;
		}
		
		
	}

	@Override
	public CommonSingleResult<FaqInfo> getPmcUpdate(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, FaqInfo faqInfo) {
		// TODO Auto-generated method stub
		CommonSingleResult<FaqInfo> result = new CommonSingleResult<FaqInfo>();
		if(faqInfo==null){
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}	
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null ||
				info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
			return result;
		}
		SAFaq k =new SAFaq(faqInfo);
		k.setUPD_ID(sessUser.getManagerId());
		int faqResult = faqDao.update(k);
		
		if(faqResult>0){
			result.setInfo(faqInfo);					
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
			return result;
		}else{
			result.setResult(new CommonResult(CommonMsg.FailCodeFaqService.II_FAQ_UPDATE,
					CommonMsg.FailMsgCRUDService.II_UPDATE));
			return result;
		}
	}

	@Override
	public CommonSingleResult<FaqInfo> getPmcDelete(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, FaqInfo faqInfo) {
		// TODO Auto-generated method stub
		CommonSingleResult<FaqInfo> result = new CommonSingleResult<FaqInfo>();
		if(faqInfo==null){
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}	
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null ||
				info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
			return result;
		}
		
		int faqResult = faqDao.delete(new SAFaq(faqInfo));
		
		if(faqResult>0){
			result.setInfo(faqInfo);					
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
			return result;
		}else{
			result.setResult(new CommonResult(CommonMsg.FailCodeFaqService.II_FAQ_DELETE,
					CommonMsg.FailMsgCRUDService.II_DELETE));
			return result;
		}
	}



}
