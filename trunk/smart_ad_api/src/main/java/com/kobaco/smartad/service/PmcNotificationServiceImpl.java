package com.kobaco.smartad.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kobaco.smartad.dao.CommonDao;
import com.kobaco.smartad.model.data.ParamsCommonPage;
import com.kobaco.smartad.model.data.SANotification;
import com.kobaco.smartad.model.data.SANotificationConfirm;
import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.NotificationInfo;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.utils.CommonCode;
import com.kobaco.smartad.utils.CommonMsg;

@Service
public class PmcNotificationServiceImpl implements PmcNotificationService {

	@Autowired
	CommonDao<SANotification> notiDao;
	@Autowired
	CommonDao<SANotificationConfirm> noticService;
	@Override
	public CommonListResult<NotificationInfo> getPmcNotificationList(
			PmcMnagerInfo info, CommonPage page, PmcMnagerInfo sessUser) {
		// TODO Auto-generated method stub
		CommonListResult<NotificationInfo> result = new CommonListResult<NotificationInfo>();
		if(page.getCurrentPage()<=0 ||page.getUnitPerPage() <=0){
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}	
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null ||
				info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}
		int totalCount = notiDao.count(new SANotification());
		int totalPage =   (int) Math.ceil((double)totalCount /(double) page.getUnitPerPage() );		
		
		ParamsCommonPage list = new ParamsCommonPage();
		list.setCurrentPage(page.getCurrentPage());
		list.setTotalCount(totalCount);
		list.setTotalPage(totalPage);
		list.setUnitPerPage(page.getUnitPerPage());		
		
		List<SANotification> infoResult = notiDao.list(new SANotification(), list);		

		
		if(infoResult.size()>0){
			List<NotificationInfo> resultList = new ArrayList<NotificationInfo>();
			for(SANotification is : infoResult){
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
	public CommonSingleResult<NotificationInfo> getPmcNotification(
			PmcMnagerInfo info, PmcMnagerInfo sessUser,
			NotificationInfo notiInfo) {
		// TODO Auto-generated method stub
		CommonSingleResult<NotificationInfo> result = new CommonSingleResult<NotificationInfo>();	
		
		if(notiInfo.getNotiNo()==null){
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null ||
				info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}

		 SANotification noti = notiDao.info(new SANotification(notiInfo)); 
				
		if(noti.getNTFC_NO() != null){
			
			CommonResult k = new CommonResult();			
			k.setResultCode(CommonMsg.successCode);
			k.setResultMsg(CommonMsg.successMsg);	
			result.setResult(k);
			NotificationInfo resultInfo = new NotificationInfo(noti);
			result.setInfo(resultInfo);					
			return result;
			
		}else {
			CommonResult k = new CommonResult();			
			k.setResultCode(CommonMsg.failCodeNotFound);
			k.setResultMsg(CommonMsg.failMsgNotFound);	
			result.setResult(k);
			return result;
		}		
	
	}
	@Override
	public CommonSingleResult<NotificationInfo> getPmcNotificationAdd(
			PmcMnagerInfo info, PmcMnagerInfo sessUser,
			NotificationInfo notiInfo) {
		// TODO Auto-generated method stub
		CommonSingleResult<NotificationInfo> result = new CommonSingleResult<NotificationInfo>();	
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null ||
				info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}
		if(notiInfo==null){
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		SANotification k =	new SANotification(notiInfo);
		k.setREG_ID(sessUser.getManagerId());
		k.setUPD_ID("PMC");
		SANotification notiResult = notiDao.insert(k);
		if(notiResult!=null){
			result.setInfo(new NotificationInfo(notiResult));					
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
			return result;
		}else{
			result.setResult(new CommonResult(CommonMsg.FailCodeNotificationService.II_NT_REGISTER,
					CommonMsg.FailMsgCRUDService.II_REGISTER));
			return result;
		}
	}
	@Override
	public CommonSingleResult<NotificationInfo> getPmcNotificationUpdate(
			PmcMnagerInfo info, PmcMnagerInfo sessUser,
			NotificationInfo notiInfo) {
		// TODO Auto-generated method stub
		CommonSingleResult<NotificationInfo> result = new CommonSingleResult<NotificationInfo>();	
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null ||
				info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}
		if(notiInfo==null){
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		SANotification k =	new SANotification(notiInfo);
		k.setUPD_ID(sessUser.getManagerId());
		int notiResult = notiDao.update(k);
		if(notiResult==1){
			result.setInfo(notiInfo);					
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
			return result;
		}else{
			result.setResult(new CommonResult(CommonMsg.FailCodeNotificationService.II_NT_UPDATE,
					CommonMsg.FailMsgCRUDService.II_UPDATE));
			return result;
		}
	}
	@Override
	public CommonSingleResult<NotificationInfo> getPmcNotificationDelete(
			PmcMnagerInfo info, PmcMnagerInfo sessUser,
			NotificationInfo notiInfo) {
		// TODO Auto-generated method stub
		CommonSingleResult<NotificationInfo> result = new CommonSingleResult<NotificationInfo>();	
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null ||
				info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}
		if(notiInfo==null){
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		int notiResult = notiDao.delete(new SANotification(notiInfo));
		
		if(notiResult>0){
			result.setInfo(notiInfo);					
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
			return result;
		}else{
			result.setResult(new CommonResult(CommonMsg.FailCodeNotificationService.II_NT_DELETE,
					CommonMsg.FailMsgCRUDService.II_DELETE));
			return result;
		}
	}
	
	

}
