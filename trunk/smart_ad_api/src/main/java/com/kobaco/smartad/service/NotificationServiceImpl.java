package com.kobaco.smartad.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kobaco.smartad.dao.CommonDao;
import com.kobaco.smartad.model.data.ParamsCommonAggregator;
import com.kobaco.smartad.model.data.ParamsCommonFilter;
import com.kobaco.smartad.model.data.ParamsCommonPage;
import com.kobaco.smartad.model.data.SANotification;
import com.kobaco.smartad.model.data.SANotificationConfirm;
import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.NotificationInfo;
import com.kobaco.smartad.model.service.UserInfo;
import com.kobaco.smartad.utils.CommonMsg;
@Service
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
	CommonDao<SANotification> notiDao;
	@Autowired
	CommonDao<SANotificationConfirm> noticService;

	
	@Override
	public CommonListResult<NotificationInfo> getNotificationList(NotificationInfo notiInfo, CommonPage page) {
		
		CommonListResult<NotificationInfo> result = new CommonListResult<NotificationInfo>();
		if(page.getCurrentPage()<=0 ||page.getUnitPerPage() <=0){
			CommonResult k = new CommonResult();			
			k.setResultCode(CommonMsg.failCodeInvalidInput);
			k.setResultMsg(CommonMsg.failMsgeInvalidInput);	
			result.setResult(k);
			return result;
		}	

		
		ParamsCommonFilter filter = new ParamsCommonFilter();
		Map<String, Object> cols = new HashMap<String, Object>();
		cols.put("NTFC_SBJT", notiInfo.getNotiSubject());
		cols.put("NTFC_CNTT", notiInfo.getNotiContent());
		filter.setColumns(cols);
		
		int totalCount = notiDao.count(new SANotification(), filter);
		int totalPage =   (int) Math.ceil((double)totalCount /(double) page.getUnitPerPage() );		
		
		ParamsCommonPage list = new ParamsCommonPage();
		list.setCurrentPage(page.getCurrentPage());
		list.setTotalCount(totalCount);
		list.setTotalPage(totalPage);
		list.setUnitPerPage(page.getUnitPerPage());		
		
		ParamsCommonAggregator agg = new ParamsCommonAggregator().filter(filter).page(list);
		
		List<SANotification> info = notiDao.list(new SANotification(), agg);		

		if(info.size()>0){
			List<NotificationInfo> resultList = new ArrayList<NotificationInfo>();
			for(SANotification is : info){
				NotificationInfo temp = new NotificationInfo();
				temp.setNotiNo(is.getNTFC_NO());
				temp.setNotiSubject(is.getNTFC_SBJT());
				temp.setNotiConfirmCount(is.getNTFC_COUNT());
				temp.setNotiRegisteDate(is.getREG_DTT());		
				resultList.add(temp);
			}
			
			CommonResult k = new CommonResult();
			page.setTotalCount(totalCount);
			page.setTotalPage(totalPage);
			k.setResultCode(CommonMsg.successCode);
			k.setResultMsg(CommonMsg.successMsg);
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
	public CommonSingleResult<NotificationInfo> getNoti(final NotificationInfo notiInfo,UserInfo sessUser) {
		// TODO Auto-generated method stub
		CommonSingleResult<NotificationInfo> result = new CommonSingleResult<NotificationInfo>();	
		
		if(notiInfo.getNotiNo()==null){
			CommonResult k = new CommonResult();			
			k.setResultCode(CommonMsg.failCodeInvalidInput);
			k.setResultMsg(CommonMsg.failMsgeInvalidInput);	
			result.setResult(k);
			return result;
		}

		final SANotification noti = notiDao.info(new SANotification() {
			{
				setNTFC_NO(notiInfo.getNotiNo());				
			}
		});
				
		if(noti.getNTFC_NO() != null){
			
			CommonResult k = new CommonResult();			
			k.setResultCode(CommonMsg.successCode);
			k.setResultMsg(CommonMsg.successMsg);	
			result.setResult(k);
			NotificationInfo resultInfo = new NotificationInfo(){
				{
						setNotiNo(noti.getNTFC_NO());
						setNotiContent(noti.getNTFC_CNTT());
						setNotiRegisteDate(noti.getREG_DTT());
						setNotiSubject(noti.getNTFC_SBJT());
						setNotiConfirmCount(noti.getNTFC_COUNT());
						
				}
			};			
			result.setInfo(resultInfo);
			
			ParamsCommonFilter filter = new ParamsCommonFilter();
			Map<String, Object> cols = new HashMap<String, Object>();
			cols.put("NTFC_NO", noti.getNTFC_NO());
			filter.addNamespace("Count");
			filter.setColumns(cols);
			
			notiDao.update(new SANotification(), filter);
			
//			if(sessUser.isLogin()){
//				SANotificationConfirm count = new SANotificationConfirm();
//				count.setNTFC_NO(noti.getNTFC_NO());
//				count.setUSR_NO(sessUser.getUserNo());
//				count.setREG_ID(sessUser.getUserId());
//				count.setUPD_ID(sessUser.getUserId());
//				
//				SANotificationConfirm check = noticService.info(count);
//				if(check==null){
//					SANotificationConfirm is = 	noticService.insert(count);	
////					if(is.getNTFC_NO()==null){
////						CommonResult j = new CommonResult();			
////						j.setResultCode(CommonMsg.FailCodeNotificationService.II_NT_COUNT);
////						j.setResultMsg(CommonMsg.FailMsgNotificationService.II_NT_COUNT);	
////						result.setResult(j);				
////					}		
//				}						
//			}			
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
