package com.kobaco.smartad.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kobaco.smartad.dao.CommonDao;
import com.kobaco.smartad.model.Result;
import com.kobaco.smartad.model.data.ParamsCommonAggregator;
import com.kobaco.smartad.model.data.ParamsCommonFilter;
import com.kobaco.smartad.model.data.ParamsCommonPage;
import com.kobaco.smartad.model.data.SAQna;
import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.MailSend;
import com.kobaco.smartad.model.service.QnaInfo;
import com.kobaco.smartad.model.service.UserInfo;
import com.kobaco.smartad.utils.CommonMsg;
import com.kobaco.smartad.utils.email.MailSendService;

@Service
public class QnaServiceImpl implements QnaService {

	@Autowired
	CommonDao<SAQna> qnaDao;
	@Autowired(required=false)
	MailSendService mail = new MailSendService() ;	
	
	@SuppressWarnings("serial")
	@Override
	public CommonListResult<QnaInfo> getQnaList(CommonPage page ,QnaInfo qnaInfo, UserInfo sessUser) {
		
		CommonListResult<QnaInfo> result = new CommonListResult<QnaInfo>();
		
		if(page.getCurrentPage()<=0 ||page.getUnitPerPage() <=0){
			CommonResult k = new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput);
			result.setResult(k);
			return result;
		}
		
		if (sessUser.getUserNo() == null) {
			CommonResult k = new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized);
			result.setResult(k);
			return result;
		}
		ParamsCommonFilter filter = new ParamsCommonFilter();
		Map<String, Object> pp = new HashMap<String, Object>();
		pp.put("userNo",sessUser.getUserNo());
		pp.put("querySubject",qnaInfo.getQuerySubject());
		pp.put("queryContent",qnaInfo.getQueryContent());
		pp.put("queryEmail", qnaInfo.getQueryEmail());
		filter.setColumns(pp);
		
		int totalCount = qnaDao.count(new SAQna(),filter);
		int totalPage =   (int) Math.ceil((double)totalCount /(double) page.getUnitPerPage() );		
		
		ParamsCommonPage p = new ParamsCommonPage();
		p.setCurrentPage(page.getCurrentPage());
		p.setUnitPerPage(page.getUnitPerPage());
		
		
		
		ParamsCommonAggregator list = new ParamsCommonAggregator();
		list.filter(filter);
		list.page(p);
		
		List<SAQna> info = qnaDao.list(new SAQna(),list);	
		if(info.size()>0){
			
			List<QnaInfo> resultList = new ArrayList<QnaInfo>();
			
			for(SAQna is : info){
				QnaInfo temp = new QnaInfo();
				temp.setQnaNo(is.getQNA_NO());
				temp.setQuerySubject(is.getQ_SBJT());
				temp.setQueryDate(is.getQ_DTT());
				resultList.add(temp);
			}
			CommonResult k = new CommonResult(CommonMsg.successCode,CommonMsg.successMsg);	
			page.setTotalCount(totalCount);
			page.setTotalPage(totalPage);
			result.setList(resultList);
			result.setPage(page);
			result.setResult(k);
		}else{
			CommonResult k = new CommonResult(CommonMsg.failCodeNotFound,CommonMsg.failMsgNotFound);
			result.setResult(k);
		}
		return result;
	}


	@Override
	public CommonSingleResult<QnaInfo> getQna(final QnaInfo qnaInfo) {
		// TODO Auto-generated method stub

		final SAQna qna = qnaDao.info(new SAQna() {
			{
				setQNA_NO(qnaInfo.getQnaNo());
			}
		});
		
		CommonSingleResult<QnaInfo> result = new CommonSingleResult<QnaInfo>();
		
		if(qna != null){
			QnaInfo resultInfo = new QnaInfo(){
				{
					setAnswerContent(qna.getA_CNTT());
					setAnwserDate(qna.getA_DTT());
					setAnswerSubject( qna.getA_SBJT());
					setQueryContent(qna.getQ_CNTT());
					setQueryDate(qna.getQ_DTT());
					setQuerySubject(qna.getQ_SBJT());
				}
			};
			 result.setInfo(resultInfo);
			 result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
		} else {	
			 result.setResult(new CommonResult(CommonMsg.failCodeNotFound,CommonMsg.failMsgNotFound));		
		}
		
		return result;
	}

	@Override
	public CommonSingleResult<QnaInfo> registeQna(final QnaInfo qnaInfo) {
		// TODO Auto-generated method stub		
		CommonSingleResult<QnaInfo> result = new CommonSingleResult<QnaInfo>();
		if ( (qnaInfo.getQueryEmail() == null || qnaInfo.getQueryEmail().equals(""))) {
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput, CommonMsg.failMsgeInvalidInput));
			return result;
		}
		if(qnaInfo.getQuerySubject()==null ||qnaInfo.getQueryContent()==null
		 || qnaInfo.getQuerySubject().equals("") ||qnaInfo.getQueryContent().equals("")	){
			CommonResult k = new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput);
			result.setResult(k);
			return result;
		}
		final SAQna qna = qnaDao.insert(new SAQna(){
			{
				setQ_USR_NO(qnaInfo.getUser().getUserNo());
				setQ_SBJT(qnaInfo.getQuerySubject());
				setQ_CNTT(qnaInfo.getQueryContent());
				setQ_DTT(new Date());
				setQ_EML(qnaInfo.getQueryEmail());
				setQ_NM(qnaInfo.getQueryName());
				setQ_PHN(qnaInfo.getQueryPhone());
			}
		});
		
		if(qna != null){
			QnaInfo resultInfo = new QnaInfo(){
				{					
					setQuerySubject(qna.getQ_SBJT());
					setQueryDate(qna.getQ_DTT());
					setQnaNo(qna.getQNA_NO());
				}
			};			
			 result.setInfo(resultInfo);		
			 result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
		
		} else {			
			 result.setResult(new CommonResult(CommonMsg.FailCodeQnaService.II_QA_REGISTER, CommonMsg.FailMsgCRUDService.II_REGISTER));
		}
		
		return result;
	}

	@Override
	public CommonSingleResult<QnaInfo> unregisteQna(final QnaInfo qnaInfo) {
		// TODO Auto-generated method stub
		CommonSingleResult<QnaInfo> result = new CommonSingleResult<QnaInfo>();
		
		if (qnaInfo.getUser().getUserNo() == null) {
			CommonResult k = new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized);
			result.setResult(k);
			return result;
		}
		final SAQna info = qnaDao.info(new SAQna(){
			{
				setQNA_NO(qnaInfo.getQnaNo());
			}
		}); 
		if(info==null){
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound, CommonMsg.failMsgNotFound));
			return result;
		}
		int qna = qnaDao.delete(info);	
		
		if(qna==1){
			
			QnaInfo resultInfo = new QnaInfo(){
				{					
					setQuerySubject(info.getQ_SBJT());
					setQnaNo(info.getQNA_NO());
				}
			};
			
			 result.setInfo(resultInfo);
			 result.setResult((new CommonResult(CommonMsg.successCode,CommonMsg.successMsg)));
		} else {
			 result.setResult(new CommonResult(CommonMsg.FailCodeQnaService.II_QA_DELETE,CommonMsg.FailMsgCRUDService.II_DELETE));	
		}
		return result;
	}

	@Override
	public CommonSingleResult<QnaInfo> updateQna(final QnaInfo qnaInfo) {
		// TODO Auto-generated method stub
		CommonSingleResult<QnaInfo> result = new CommonSingleResult<QnaInfo>();
		
		if (qnaInfo.getUser().getUserNo() == null) {
			CommonResult k = new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized);
			result.setResult(k);
			return result;
		}
		final SAQna select = qnaDao.info(new SAQna(){
			{
				setQNA_NO(qnaInfo.getQnaNo());
			}
		});
		if(select==null){
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound, CommonMsg.failMsgNotFound));
			return result;
		}		
		select.setQ_SBJT(qnaInfo.getQuerySubject());
		select.setQ_CNTT(qnaInfo.getQueryContent());
		
		int qna = qnaDao.update(select);
		
		if(qna==1){
			QnaInfo resultInfo = new QnaInfo(){
				{					
					setQuerySubject(select.getQ_SBJT());
					setQueryDate(select.getQ_DTT());
					setQnaNo(select.getQNA_NO());
				}
			};
			 result.setInfo(resultInfo);
			 result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
			 
		} else {			
			result.setResult(new CommonResult(CommonMsg.FailCodeQnaService.II_QA_UPDATE,CommonMsg.FailMsgCRUDService.II_UPDATE));	
		}
		return result;
	}
}
