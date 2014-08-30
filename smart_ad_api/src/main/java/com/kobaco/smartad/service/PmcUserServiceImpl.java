package com.kobaco.smartad.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kobaco.smartad.dao.CommonDao;
import com.kobaco.smartad.model.data.ParamsCommonAggregator;
import com.kobaco.smartad.model.data.ParamsCommonFilter;
import com.kobaco.smartad.model.data.ParamsCommonNamespace;
import com.kobaco.smartad.model.data.ParamsCommonPage;
import com.kobaco.smartad.model.data.ParamsForReserveMyPage;
import com.kobaco.smartad.model.data.SAEnterprise;
import com.kobaco.smartad.model.data.SAFacilityReserve;
import com.kobaco.smartad.model.data.SAUser;
import com.kobaco.smartad.model.data.SAUserEmailCertification;
import com.kobaco.smartad.model.data.SAUserLog;
import com.kobaco.smartad.model.data.SAUserTscsConsent;
import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.MailSend;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.ReserveInfo;
import com.kobaco.smartad.model.service.UserInfo;
import com.kobaco.smartad.utils.CommonCode;
import com.kobaco.smartad.utils.CommonMsg;
import com.kobaco.smartad.utils.email.MailSendService;
@Service
public class PmcUserServiceImpl implements PmcUserService{
	@Autowired
	private CommonDao<SAUser> userDao;
	@Autowired
	private CommonDao<SAFacilityReserve> revDao;
	@Autowired
	private CommonDao<SAEnterprise> enterpriseDao;	
	@Autowired
	CommonDao<SAUserEmailCertification> userEmailDao;	
	@Autowired
	CommonDao<SAUserTscsConsent> userTscsDao;
	@Autowired(required=false)
	MailSendService mail = new MailSendService() ;	
	@Autowired
	CommonDao<SAUserLog> userLogDao;
	
	@Autowired
	@Qualifier("props")
	private Properties props;
	
	@Override
	public CommonListResult<UserInfo> getPmcUserSearch(PmcMnagerInfo info,
			CommonPage cp, PmcMnagerInfo sessUser, UserInfo user) {
		// TODO Auto-generated method stub
		CommonListResult<UserInfo> result = new CommonListResult<UserInfo>();
		if(sessUser.getManagerNo()==null ||sessUser.getManagerNo().equals("") || info.getManagerMode()==null || 
			info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
			return result;
		}
		if(cp.getCurrentPage()<=0 ||cp.getUnitPerPage() <=0 ){	
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		
		ParamsCommonFilter count = new ParamsCommonFilter();
		Map<String,Object> countMap = new HashMap<String, Object>();
		if(user.getSearchKey()==1)countMap.put("userId", user.getSearchKeyword());
		else if(user.getSearchKey()==2) countMap.put("userName", user.getSearchKeyword());
		else if(user.getSearchKey()==3)countMap.put("enterpriseName", user.getSearchKeyword());
		 
		count.setColumns(countMap);
		int totalCount =   userDao.count(new SAUser(),count);
		int totalPage =   (int) Math.ceil((double)totalCount /(double) cp.getUnitPerPage());
		cp.setTotalPage(totalPage);
		cp.setTotalCount(totalCount);
		
		if(totalCount==0){
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound,CommonMsg.failMsgNotFound));
			return result;
		}
			
		ParamsCommonPage page = new ParamsCommonPage();		
		page.setUnitPerPage(cp.getUnitPerPage());
		page.setCurrentPage(cp.getCurrentPage());
		
		ParamsCommonAggregator agg = new ParamsCommonAggregator();
		agg.page(page);
		agg.filter(count);
		
		List<SAUser> listResult =   userDao.list(new SAUser(),agg);
		List<UserInfo> pmclistResult = new ArrayList<UserInfo>();
		for(SAUser userResult : listResult){
			UserInfo infoResult = new UserInfo();
			infoResult.setPmcUser(userResult);
			pmclistResult.add(infoResult);
		}
		result.setList(pmclistResult);
		result.setPage(cp);
		result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
		return result;
	}
	@Override
	public CommonSingleResult<UserInfo> getPmcUserGet(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, UserInfo user) {
		// TODO Auto-generated method stub
		CommonSingleResult<UserInfo> result = new CommonSingleResult<UserInfo>();
		System.out.println("sess "+sessUser.isLogin() );
		System.out.println("sess "+info.getManagerMode() );
		
		if(!sessUser.isLogin() ||info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
			return result;
		}
		if(user.getUserNo()==null || user.getUserNo().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		ParamsCommonNamespace name = new ParamsCommonNamespace();
		Map<String,Object> nameMap = new HashMap<String, Object>();
		nameMap.put("userNo", user.getUserNo());
		name.setColumns(nameMap, "Pmc");
		SAUser saResult = userDao.info(new SAUser(),name);
		if(saResult==null){			
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound,CommonMsg.failMsgNotFound));
			return result;
		}
		
		user = new UserInfo();
		user.setPmcUser(saResult);		
		result.setInfo(user);
		result.setResult(new CommonResult(CommonMsg.successCode, CommonMsg.successMsg));		
		return result;
	}
	@Override
	public CommonListResult<ReserveInfo> getPmcReserveList(PmcMnagerInfo info,
			CommonPage cp, PmcMnagerInfo sessUser, UserInfo user) {
		// TODO Auto-generated method stub
		CommonListResult<ReserveInfo> result = new CommonListResult<ReserveInfo>();
		if(sessUser.getManagerNo()==null ||sessUser.getManagerNo().equals("") || info.getManagerMode()==null || 
			info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
			return result;
		}
		if(cp.getCurrentPage()<=0 ||cp.getUnitPerPage() <=0 ){	
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		ParamsCommonFilter count = new ParamsCommonFilter();
		Map<String,Object> countMap = new HashMap<String, Object>();
		countMap.put("userNo", user.getUserNo());
		count.setColumns(countMap);
		int totalCount =   revDao.count(new SAFacilityReserve(),count);
		int totalPage =   (int) Math.ceil((double)totalCount /(double) cp.getUnitPerPage());
		cp.setTotalPage(totalPage);
		cp.setTotalCount(totalCount);
		
		if(totalCount==0){
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound,CommonMsg.failMsgNotFound));
			return result;
		}
		
		ParamsForReserveMyPage filter = new ParamsForReserveMyPage("",user.getUserNo());
		
		ParamsCommonPage page = new ParamsCommonPage();		
		page.setUnitPerPage(cp.getUnitPerPage());
		page.setCurrentPage(cp.getCurrentPage());
		
		ParamsCommonAggregator agg = new ParamsCommonAggregator();
		agg.page(page);
		agg.filter(filter);	
		
		List<SAFacilityReserve> list = revDao.list(new SAFacilityReserve(),agg);	
		
		List<ReserveInfo>  listResult = new ArrayList<ReserveInfo>();		
		
		if(list.size()>0){	
			for(int i= 0; i<list.size(); i++){
				ReserveInfo is = new ReserveInfo();
					is.setFacilityName(list.get(i).getFCLT_NM());
					is.setFacilityNo(list.get(i).getFCLT_MGMT_NO());
					is.setReserveNo(list.get(i).getRSRV_NO());
					is.setReserveStartDate(list.get(i).getRSRV_DT());
					is.setUserNo(list.get(i).getUSR_NO());
					is.setReserveDetailNo(list.get(i).getRSRV_DTL_NO());
					if(list.get(i).getTZ_TP_CD().equals(CommonCode.ReserveCode.Time_Zone_Am)){
						is.setReserveTimeZone(CommonCode.ReserveCode.Time_Zone_Am_Return);
					}else {
						is.setReserveTimeZone(CommonCode.ReserveCode.Time_Zone_Pm_Return);
					}
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					String beforeVst = formatter.format(list.get(i).getRSRV_DT());
					String beforeNow = formatter.format(new Date());
					if(list.get(i).getCNCL_YN().equals("N")){
					try {
						Date vstDtt = formatter.parse(beforeVst);
						Date nowDtt = formatter.parse(beforeNow);						
							if(vstDtt.after(nowDtt)){
								is.setReserveState(CommonCode.ReserveCode.Reserve_State_Start);
							}else if(vstDtt.before(nowDtt)){
								is.setReserveState(CommonCode.ReserveCode.Reserve_State_End);
							}else{
								is.setReserveState(CommonCode.ReserveCode.Reserve_State_Ing);
							}					
						}catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}				
					}else {
						is.setReserveState(CommonCode.ReserveCode.Reserve_State_Cancel);
					}							
						listResult.add(is);					
				}			
			page.setTotalCount(totalCount);
			page.setTotalPage(totalPage);
			CommonResult k = new CommonResult(CommonMsg.successCode,CommonMsg.successMsg);	
			result.setPage(page);
			result.setResult(k);
			result.setList(listResult);
		} 
		
		return result;
	}
	@Override
	public CommonSingleResult<UserInfo> getPmcUserUpdate(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, UserInfo user) {
		// TODO Auto-generated method stub
		CommonSingleResult<UserInfo> result = new CommonSingleResult<UserInfo>();
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null ||
			info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
			return result;
		}
		if(user.getUserNo()==null || user.getUserNo().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		ParamsCommonNamespace name = new ParamsCommonNamespace();
		Map<String,Object> nameMap = new HashMap<String, Object>();
		nameMap.put("userNo", user.getUserNo());		
		nameMap.put("ENTP_NM", user.getEnterpriseName());
		nameMap.put("ENTP_ADDR", user.getEnterpriseAddress());
		nameMap.put("UPD_ID", user.getUserId());
		name.setColumns(nameMap, "Pmc");
		SAUser saResult = userDao.info(new SAUser(),name);
		if(saResult==null){			
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound,CommonMsg.failMsgNotFound));
			return result;
		}
		nameMap.put("ENTP_NO", saResult.getENTP_NO());
		saResult.setUSR_EML(user.getUserEmail());
		saResult.setUSR_NM(user.getUserName());
		saResult.setENTP_NM(user.getEnterpriseName());
		saResult.setENTP_ADDR(user.getEnterpriseAddress());
		saResult.setCPHN(user.getUserCellPhone().trim());
		saResult.setPHN(user.getUserPhone().trim());
		
		int updateCount = userDao.update(saResult);
		int entCount = enterpriseDao.update(new SAEnterprise(),name);
		if(updateCount==1 && entCount==1){
			user = new UserInfo();
			user.setPmcUser(saResult);		
			result.setInfo(user);
			result.setResult(new CommonResult(CommonMsg.successCode, CommonMsg.successMsg));	
		}else{
			result.setResult(new CommonResult(CommonMsg.FailCodeUserService.II_US_UPDATE, CommonMsg.FailMsgUserService.II_US_UPDATE));	
		}
			
		return result;
	}
	@Override
	public CommonSingleResult<UserInfo> getPmcUserAdd(PmcMnagerInfo info,PmcMnagerInfo sessUser, UserInfo user) {
		// TODO Auto-generated method stub
		CommonSingleResult<UserInfo> result = new CommonSingleResult<UserInfo>();
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null ||
			info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
			return result;
		}
		if (userDao.info(user.getSAUser()) != null) {
			return new CommonSingleResult<UserInfo>(
					new CommonResult(
							CommonMsg.FailCodeUserService.ID_DUP_ID, 
							CommonMsg.FailMsgUserService.ID_DUP_ID),null);
		}

		if(!"Y".equals(user.getConsentTscsYn())) {
			return new CommonSingleResult<UserInfo>(
					new CommonResult(
							CommonMsg.FailCodeUserService.II_CSRI_N, 
							CommonMsg.FailMsgUserService.II_CSRI_N),null);
		} 
		
		SAEnterprise ent = user.getSAEnterprise();
		ent.setENTP_EML(user.getEnterpriseName());
		ent.setENTP_ADDR(user.getEnterpriseAddress());
		ent.setUPD_ID(CommonCode.PmcManagerMsg.Mode);
		ent.setREG_ID(CommonCode.PmcManagerMsg.Mode);
		ent = enterpriseDao.insert(ent);
		
		SAUser saUser = user.getSAUser();
		saUser.setENTP_NO(ent.getENTP_NO());
		saUser.setUSR_STTS_CD(props.getProperty("user.state.act.nml"));
		saUser.setCPHN(saUser.getCPHN().trim());
		saUser.setPHN(saUser.getPHN().trim());
		saUser = userDao.insert(saUser);
		
		addUserLog(saUser.getUSR_NO(), props.getProperty("user.log.sub"), "subscribe");
		
		SAUserEmailCertification saUserEmail = new SAUserEmailCertification();
		saUserEmail.setEML_CRTF_YN("N");
		saUserEmail.setUSR_NO(saUser.getUSR_NO());
		saUserEmail.setUPD_ID(saUser.getUSR_NO());
		saUserEmail.setREG_ID(saUser.getUSR_NO());
		saUserEmail = userEmailDao.insert(saUserEmail);
		
		// TSCS insert 
		SAUserTscsConsent tscs = new SAUserTscsConsent();
		//if("Y".equals(user.getConsentTscsYn())) {
			tscs.setTSCS_TP_CD(props.getProperty("tscs.tp.tscs"));
			tscs.setCNST_YN(user.getConsentTscsYn());
			tscs.setUSR_NO(saUser.getUSR_NO());
			userTscsDao.insert(tscs);	
		//}
		
		
		//if("Y".equals(user.getConsentReceiveInfoYn())) {
			tscs.setTSCS_TP_CD(props.getProperty("tscs.tp.reif"));
			tscs.setCNST_YN(user.getConsentReceiveInfoYn());
			tscs.setUSR_NO(saUser.getUSR_NO());
			userTscsDao.insert(tscs);	
		//}
		
		// Send Email Cert	
		
	
		if (saUser.getUSR_NO() != null &&
			saUserEmail.getEML_CRTK() != null) {
			result = new CommonSingleResult<UserInfo>(
					new CommonResult(CommonMsg.successCode, CommonMsg.successMsg),
					new UserInfo(saUser, ent));
			//SEND ADD EMAIL			
			MailSend mailDto = new MailSend(CommonMsg.EmailMsgService.ADD_FROM,
					saUser.getUSR_EML(), new HashMap<String, Object>(),
					CommonMsg.EmailMsgService.ADD_MSG , CommonMsg.EmailMsgService.ADD_EAMIL);
			mail.mailSend(mailDto);
			
//			MailSend mailDtoUrl = new MailSend(CommonMsg.EmailMsgService.ADD_FROM,
//					saUser.getUSR_EML(), new HashMap<String, Object>(),
//					CommonMsg.EmailMsgService.AUTH_MSG , CommonMsg.EmailMsgService.AUTH_EMAIL);
//			mail.mailSend(mailDtoUrl);
			
		} else {
			result = new CommonSingleResult<UserInfo>(
					new CommonResult(CommonMsg.failCode, CommonMsg.failMsg),
					null);			
		}
		
		return result;
	}
	private void addUserLog(final String userNo, final String logType, final String logContent) {
		SAUserLog saUserLog = new SAUserLog();
		userLogDao.insert(new SAUserLog(){
			{
				setUSR_NO(userNo);
				setUSR_LOG_TP(logType);
				setUSR_LOG_CNTT(logContent);
			}
		});
	}
	@Override
	public CommonSingleResult<UserInfo> getPmcUserUpdateStatus(PmcMnagerInfo info, PmcMnagerInfo sessUser, UserInfo user) {
		// TODO Auto-generated method stub
		CommonSingleResult<UserInfo> result = new CommonSingleResult<UserInfo>();
		if(sessUser==null ||!sessUser.isLogin() ||info.getManagerMode()==null ||
			info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
			return result;
		}
		if(user ==null ||user.getUserStatus()==null|| user.getUserStatus().equals("")||
				user.getUserNo()==null|| user.getUserNo().equals("")){
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		
		ParamsCommonNamespace param = new ParamsCommonNamespace();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("USR_NO", user.getUserNo());
		map.put("USR_STTS_CD", user.getUserStatus());
		param.setColumns(map, "Status");
		int resultUpdate = userDao.update(new SAUser(), param);
		
		if(resultUpdate==1){
			result.setInfo(user);
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
		}else {
			result.setResult(new CommonResult(CommonMsg.FailCodeUserService.II_US_UPDATE, CommonMsg.FailMsgUserService.II_US_UPDATE));		
		}
		return result;
	}
	
	@Override
	public CommonSingleResult<UserInfo> delete(UserInfo user) {

		SAUser u = new SAUser();
		u.setUSR_NO(user.getUserNo());
		
		CommonSingleResult<UserInfo> result = new CommonSingleResult<UserInfo>();
		if(userDao.delete(u)>0){
			result.setInfo(user);
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
		}else {
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound, CommonMsg.failMsgNotFound));		
		}
		return result;
	}	

}
