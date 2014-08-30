package com.kobaco.smartad.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kobaco.smartad.dao.CommonDao;
import com.kobaco.smartad.model.data.ParamsCommonAggregator;
import com.kobaco.smartad.model.data.ParamsCommonFilter;
import com.kobaco.smartad.model.data.ParamsCommonNamespace;
import com.kobaco.smartad.model.data.ParamsCommonPage;
import com.kobaco.smartad.model.data.SAFaq;
import com.kobaco.smartad.model.data.SAPMCManager;
import com.kobaco.smartad.model.data.SAQna;
import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.MailSend;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.QnaInfo;
import com.kobaco.smartad.utils.CommonCode;
import com.kobaco.smartad.utils.CommonMsg;
import com.kobaco.smartad.utils.email.MailSendService;

@Service
public class PmcQnaServiceImpl implements PmcQnaService {

	@Autowired
	CommonDao<SAQna> qnaDao;
	@Autowired
	CommonDao<SAPMCManager> managerDao;
	@Autowired(required=false)
	MailSendService mail = new MailSendService() ;	
	
	@Override
	public CommonSingleResult<QnaInfo> getPmcAnswer(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, QnaInfo qnaInfo) {
		// TODO Auto-generated method stub
		CommonSingleResult<QnaInfo> result = new CommonSingleResult<QnaInfo>();
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null ||
				info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}
		if(qnaInfo==null ||qnaInfo.getQnaNo()==null || qnaInfo.getQnaNo().equals("") ){
			CommonResult k = new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput);
			result.setResult(k);
			return result;
		}
		ParamsCommonNamespace params = new ParamsCommonNamespace();
		Map<String, Object> k=new HashMap<String, Object>();
		k.put("QNA_NO",   qnaInfo.getQnaNo());
		k.put("A_SBJT",   qnaInfo.getAnswerSubject());
		k.put("A_CNTT",   qnaInfo.getAnswerContent());
		k.put("A_MNGR_NO",sessUser.getManagerNo());
		params.setColumns(k, "Answer");
		int infoResult = qnaDao.update(new SAQna(),params); 
		
		if(infoResult==1){
			//qnaInfo.setAnswerYn(CommonCode.PmcManagerCode.PMC_YES);
			result.setInfo(qnaInfo);					
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
			ParamsCommonNamespace param = new ParamsCommonNamespace();
			Map< String, Object> m = new HashMap<String, Object>();
			m.put("QNA_NO", qnaInfo.getQnaNo());
			param.setColumns(m, "Answer");
			SAQna is = qnaDao.info(new SAQna(qnaInfo),param);
			if(is!=null){
				Map< String, Object> map = new HashMap<String, Object>();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String qDate = formatter.format(is.getQ_DTT());
				String aDate = formatter.format(is.getA_DTT());
				map.put("userId", is.getUSR_ID());
				map.put("queryDate",qDate );
				map.put("queryContent", is.getQ_CNTT());
				map.put("answerDate", aDate);
				map.put("answerContent", is.getA_CNTT());
				MailSend mailDto = new MailSend(CommonMsg.EmailMsgService.ADD_FROM,
						is.getQ_EML(),map ,
				CommonMsg.EmailMsgService.QNA_MSG, CommonMsg.EmailMsgService.QNA_EAMIL);
				mail.mailSend(mailDto);
			}
			
			
			 
			return result;
		}else{
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound,CommonMsg.failMsgNotFound));
			return result;
		}
	}

	@Override
	public CommonListResult<QnaInfo> getPmcList(PmcMnagerInfo info,
			CommonPage page, PmcMnagerInfo sessUser) {
		CommonListResult<QnaInfo> result = new CommonListResult<QnaInfo>();
		
		if(page.getCurrentPage()<=0 ||page.getUnitPerPage() <=0){
			CommonResult k = new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput);
			result.setResult(k);
			return result;
		}
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null ||
				info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}
		ParamsCommonFilter filter = new ParamsCommonFilter();
		Map<String, Object> pp = new HashMap<String, Object>();
		/*pp.put("userNo",sessUser.getUserNo());
		pp.put("querySubject",qnaInfo.getQuerySubject());
		pp.put("queryContent",qnaInfo.getQueryContent());
		pp.put("queryEmail", qnaInfo.getQueryEmail());*/
		filter.setColumns(pp);
		
		int totalCount = qnaDao.count(new SAQna(),filter);
		int totalPage =   (int) Math.ceil((double)totalCount /(double) page.getUnitPerPage() );		
		
		ParamsCommonPage p = new ParamsCommonPage();
		p.setCurrentPage(page.getCurrentPage());
		p.setUnitPerPage(page.getUnitPerPage());
		
		
		
		ParamsCommonAggregator list = new ParamsCommonAggregator();
		list.filter(filter);
		list.page(p);
		
		List<SAQna> infoResult = qnaDao.list(new SAQna(),list);	
		if(infoResult.size()>0){
			
			List<QnaInfo> resultList = new ArrayList<QnaInfo>();
			
			for(SAQna is : infoResult){
				QnaInfo temp = new QnaInfo();
				temp.setQnaNo(is.getQNA_NO());
				temp.setQuerySubject(is.getQ_SBJT());
				temp.setQueryDate(is.getQ_DTT());
				temp.setAnswerManagerNo(is.getA_MNGR_NO());
				temp.setAnswerManagerId(is.getMNGR_ID());
				temp.setQueryEmail(is.getQ_EML());
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
	public CommonSingleResult<QnaInfo> getPmcQna(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, QnaInfo qnaInfo) {
		// TODO Auto-generated method stub
		CommonSingleResult<QnaInfo> result = new CommonSingleResult<QnaInfo>();
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null ||
				info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}
		if(qnaInfo==null ||qnaInfo.getQnaNo()==null || qnaInfo.getQnaNo().equals("") ){
			CommonResult k = new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput);
			result.setResult(k);
			return result;
		}
		
		SAQna infoResult = qnaDao.info(new SAQna(qnaInfo)); 
		
		if(infoResult!=null){
			result.setInfo(new QnaInfo(infoResult));					
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
			return result;
		}else{
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound,CommonMsg.failMsgNotFound));
			return result;
		}
	}

}
