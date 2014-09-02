package com.kobaco.smartad.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kobaco.smartad.dao.CommonDao;
import com.kobaco.smartad.model.data.ParamsCommonFilter;
import com.kobaco.smartad.model.data.ParamsCommonNamespace;
import com.kobaco.smartad.model.data.ParamsCommonOrder;
import com.kobaco.smartad.model.data.ParamsCommonPage;
import com.kobaco.smartad.model.data.ParamsForLogin;
import com.kobaco.smartad.model.data.ParamsUserUpdateStatus;
import com.kobaco.smartad.model.data.SAEnterprise;
import com.kobaco.smartad.model.data.SAUser;
import com.kobaco.smartad.model.data.SAUserEmailCertification;
import com.kobaco.smartad.model.data.SAUserLog;
import com.kobaco.smartad.model.data.SAUserTscsConsent;
import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.MailSend;
import com.kobaco.smartad.model.service.UserEmailCert;
import com.kobaco.smartad.model.service.UserInfo;
import com.kobaco.smartad.utils.CommonMsg;
import com.kobaco.smartad.utils.CommonMsg.FailCodeUserService;
import com.kobaco.smartad.utils.CommonMsg.FailMsgUserService;
import com.kobaco.smartad.utils.email.MailSendService;

@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	CommonDao<SAUser> userDao;

	@Autowired
	CommonDao<SAUserLog> userLogDao;
	
	@Autowired
	CommonDao<SAEnterprise> enterpriseDao;
	
	@Autowired
	CommonDao<SAUserEmailCertification> userEmailDao;
	
	@Autowired
	CommonDao<SAUserTscsConsent> userTscsDao;
	
	@Autowired(required=false)
	MailSendService mail = new MailSendService() ;	
	
	@Autowired
	@Qualifier("props")
	private Properties props;
	
	@Override
	public CommonSingleResult<UserInfo> login(String userId, String userPassword) {
		SAUserLog infoLog = new SAUserLog(); 
		CommonSingleResult<UserInfo> result = new CommonSingleResult<UserInfo>();
		CommonResult k = new CommonResult();		
		
		SAUser info = userDao.info(new SAUser(), new ParamsForLogin(userId, userPassword));
		
		if(info != null){
			info.setUSR_PWD(null);
			
			infoLog.setUSR_NO(info.getUSR_NO());
			infoLog.setUSR_LOG_TP(props.getProperty("user.log.login"));
			infoLog.setUSR_LOG_CNTT("login");;
			userLogDao.insert(infoLog);			
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
			result.setInfo(new UserInfo(info));
		}else{
			result.setResult(new CommonResult(CommonMsg.failCode,CommonMsg.failMsg));
		}
				
		return result;
	}

	@Override
	public CommonResult logout(String userNo) {
		
		SAUserLog infoLog = new SAUserLog();
		infoLog.setUSR_NO(userNo);
		infoLog.setUSR_LOG_TP(props.getProperty("user.log.logout"));
		infoLog.setUSR_LOG_CNTT("logout");;
		userLogDao.insert(infoLog);

		return new CommonResult(CommonMsg.successCode, CommonMsg.successMsg);
	}

	@Override
	public CommonSingleResult<UserInfo> subscribe(UserInfo userInfo) {
		
		// if the userId already exists in database, return error
		if (userDao.info(userInfo.getSAUser()) != null) {
			return new CommonSingleResult<UserInfo>(
					new CommonResult(
							CommonMsg.FailCodeUserService.ID_DUP_ID, 
							CommonMsg.FailMsgUserService.ID_DUP_ID),null);
		}

		if(!"Y".equals(userInfo.getConsentTscsYn())) {
			return new CommonSingleResult<UserInfo>(
					new CommonResult(
							CommonMsg.FailCodeUserService.II_CSRI_N, 
							CommonMsg.FailMsgUserService.II_CSRI_N),null);
		} 
		
		SAEnterprise ent = userInfo.getSAEnterprise();
		ent = enterpriseDao.insert(ent);
		
		SAUser saUser = userInfo.getSAUser();
		saUser.setENTP_NO(ent.getENTP_NO());
		saUser.setUSR_STTS_CD(props.getProperty("user.state.act.nml"));
		saUser = userDao.insert(saUser);
		
		addUserLog(saUser.getUSR_NO(), props.getProperty("user.log.sub"), "subscribe");
		
		SAUserEmailCertification saUserEmail = new SAUserEmailCertification();
		saUserEmail.setEML_CRTF_YN("N");
		saUserEmail.setUSR_NO(saUser.getUSR_NO());
		saUserEmail.setUPD_ID(saUser.getUSR_NO());
		saUserEmail.setREG_ID(saUser.getUSR_NO());
		saUserEmail = userEmailDao.insert(saUserEmail);
		
		// TSCS insert 
		// 약관동의
		SAUserTscsConsent tscs = new SAUserTscsConsent();
		//if("Y".equals(userInfo.getConsentTscsYn())) {
			tscs.setTSCS_TP_CD(props.getProperty("tscs.tp.tscs"));
			tscs.setCNST_YN(userInfo.getConsentTscsYn());
			tscs.setUSR_NO(saUser.getUSR_NO());
			userTscsDao.insert(tscs);	
		//}
		
		// 장버수신동의
		//if("Y".equals(userInfo.getConsentReceiveInfoYn())) {
			tscs.setTSCS_TP_CD(props.getProperty("tscs.tp.reif"));
			tscs.setCNST_YN(userInfo.getConsentReceiveInfoYn());
			tscs.setUSR_NO(saUser.getUSR_NO());
			userTscsDao.insert(tscs);	
		//}
		
		// Send Email Cert
		
		
		CommonSingleResult<UserInfo> result;
		
		if (saUser.getUSR_NO() != null &&
			saUserEmail.getEML_CRTK() != null) {
			result = new CommonSingleResult<UserInfo>(
					new CommonResult(CommonMsg.successCode, CommonMsg.successMsg),
					new UserInfo(saUser, ent));
		
			//SEND ADD EMAIL			
			try{
				mail.mailSend(new MailSend(CommonMsg.EmailMsgService.ADD_FROM,
						saUser.getUSR_EML(), 
						new HashMap<String, Object>(),
						CommonMsg.EmailMsgService.ADD_MSG , 
						CommonMsg.EmailMsgService.ADD_EAMIL));
			
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("emailCertKey", saUserEmail.getEML_CRTK());
				//emailCertKey=${emailCertKey}
				mail.mailSend(new MailSend(CommonMsg.EmailMsgService.ADD_FROM,
						saUser.getUSR_EML(), 
						map,
						CommonMsg.EmailMsgService.AUTH_MSG, 
						CommonMsg.EmailMsgService.AUTH_EMAIL));
			}catch(Exception e){
				e.printStackTrace();
			}
			
		} else {
			result = new CommonSingleResult<UserInfo>(
					new CommonResult(CommonMsg.failCode, CommonMsg.failMsg),
					null);
			
		}
		
		return result;
	}

	@Override
	public CommonSingleResult<UserInfo> unsubscribe(UserInfo userInfo) {
		// add log
		// update status
		CommonSingleResult<UserInfo> result; 
		if (userDao.update(
				userInfo.getSAUser(), 
				new ParamsUserUpdateStatus(
						userInfo.getUserNo(), 
						props.getProperty("user.state.unsub.nml")))==1) {
			
			addUserLog(userInfo.getUserNo(), props.getProperty("user.log.unsub"),"unsubscribe user");
			result = new CommonSingleResult<UserInfo>(
					new CommonResult(CommonMsg.successCode, CommonMsg.successMsg),
					userInfo);
		} else {
			result = new CommonSingleResult<UserInfo>(
					new CommonResult(CommonMsg.failCode, CommonMsg.failMsg),
					null);
		}
		return null;
	}

	@Override
	public CommonSingleResult<UserEmailCert> emailCert(String certKey) {
		
		SAUserEmailCertification saUserEmailCert = new SAUserEmailCertification();
		saUserEmailCert.setEML_CRTK(certKey);
		saUserEmailCert = userEmailDao.info(saUserEmailCert);
		
		CommonSingleResult<UserEmailCert> result;
		if (saUserEmailCert != null) {
			saUserEmailCert.setEML_CRTF_YN("Y");
			saUserEmailCert.setEML_CRTF_DTT(new Date());
			
			if (userEmailDao.update(saUserEmailCert)>0) {
				UserEmailCert cert = new UserEmailCert(saUserEmailCert);
				result = new CommonSingleResult<UserEmailCert>(new CommonResult(CommonMsg.successCode, CommonMsg.successMsg),
						cert);
			} else {
				result = new CommonSingleResult<UserEmailCert>(new CommonResult(CommonMsg.failCodeNotFound, CommonMsg.failMsgNotFound),
						null);
			};
			addUserLog(saUserEmailCert.getUSR_NO(), props.getProperty("user.log.emailcert"),"user.log.emailcert");
		} else {
			result = new CommonSingleResult<UserEmailCert>(new CommonResult(CommonMsg.failCode, CommonMsg.failMsg), null);
		}
		return result;
	}

	@Override
	public CommonSingleResult<UserInfo> update(UserInfo userInfo) {
		
		CommonSingleResult<UserInfo> result; 

		enterpriseDao.update(userInfo.getSAEnterprise());		
		if (userDao.update(userInfo.getSAUser())==1) {
			
			// 정보수신동의 여부 
			SAUserTscsConsent tscs = new SAUserTscsConsent();
			tscs.setTSCS_TP_CD(props.getProperty("tscs.tp.reif"));
			tscs.setCNST_YN(userInfo.getConsentReceiveInfoYn());
			tscs.setUSR_NO(userInfo.getUserNo());
			userTscsDao.update(tscs);
			
			// 사용자정보변경 로그 
			addUserLog(userInfo.getUserNo(), props.getProperty("user.log.sub"),"update user info");
			
			result = new CommonSingleResult<UserInfo>(
					new CommonResult(CommonMsg.successCode, CommonMsg.successMsg),
					userInfo);
		} else {
			result = new CommonSingleResult<UserInfo>(
					new CommonResult(CommonMsg.failCodeNotFound, CommonMsg.failMsgNotFound),
					null);
		}
		return result;
	}

	@Override
	public CommonListResult<UserInfo> getList(ParamsCommonFilter test,
			ParamsCommonOrder order, ParamsCommonPage page) {
		return null;
	}

	@Override
	public CommonSingleResult<UserInfo> get(UserInfo userInfo) {
		
		CommonSingleResult<UserInfo> result; 
		
		SAUser saUser = userDao.info(userInfo.getSAUser());
		
		if (saUser != null) {
			UserInfo newUserInfo = new UserInfo(saUser);
			
			// 약관 및 정보동의 내역 조회 
			SAUserTscsConsent userConsent = new SAUserTscsConsent();
			userConsent.setUSR_NO(saUser.getUSR_NO());
			List<SAUserTscsConsent> lUserConsent = userTscsDao.list(userConsent);
			
			// 약관 및 정보동의 내역 설정
			for(SAUserTscsConsent uc : lUserConsent) {
				if (uc.getTSCS_TP_CD().equals(props.getProperty("tscs.tp.reif"))) {
					newUserInfo.setConsentReceiveInfoYn(uc.getCNST_YN());
				} else if (uc.getTSCS_TP_CD().equals(props.getProperty("tscs.tp.tscs"))) {
					newUserInfo.setConsentTscsYn(uc.getCNST_YN());
				}
			}
			
			result = new CommonSingleResult<UserInfo>(
					new CommonResult(CommonMsg.successCode, CommonMsg.successMsg),
					newUserInfo);
		} else {
			result = new CommonSingleResult<UserInfo>(
					new CommonResult(CommonMsg.failCodeNotFound, CommonMsg.failMsgNotFound),
					userInfo);
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
	public CommonSingleResult<UserInfo>  idCheck(UserInfo userInfo) {
		// TODO Auto-generated method stub
		ParamsCommonFilter is = new ParamsCommonFilter();
		Map<String, Object> k = new HashMap<String, Object>();
		k.put("userId", userInfo.getUserId());
		is.setColumns(k);
		SAUser count = new SAUser();
		count = userDao.info(new SAUser(), is);
		
		if(count==null ){
			userInfo.setUserIdYn("Y");
			CommonSingleResult<UserInfo> result = new CommonSingleResult<UserInfo>(
												new CommonResult(CommonMsg.successCode, CommonMsg.successMsg),
												userInfo);	
			return result;
		} else {
			userInfo.setUserIdYn("N");
			CommonSingleResult<UserInfo> result = new CommonSingleResult<UserInfo>(
												new CommonResult(CommonMsg.FailCodeUserService.ID_DUP_ID, 
												CommonMsg.FailMsgUserService.ID_DUP_ID),
												userInfo);
			return result;
		}
	}
	@Override
	public CommonSingleResult<UserInfo> findUser(UserInfo user) {
		// TODO Auto-generated method stub
		if(user.getUserEmail()==null || user.getUserEmail().equals("")){
			return new CommonSingleResult<UserInfo>(new CommonResult(CommonMsg.failCodeInvalidInput,
														CommonMsg.failMsgeInvalidInput));			
		}
		ParamsCommonFilter filter = new ParamsCommonFilter();
		Map<String, Object> k = new HashMap<String, Object>();
		k.put("userEmail", user.getUserEmail());
		filter.setColumns(k);
		SAUser resultUser = userDao.info(new SAUser(),filter);
		if(resultUser==null ){
			return new CommonSingleResult<UserInfo>(new CommonResult(CommonMsg.failCodeNotFound,
					CommonMsg.failMsgNotFound));
		}else {
			return new CommonSingleResult<UserInfo>(new CommonResult(CommonMsg.successCode,
					CommonMsg.successMsg),new UserInfo(resultUser));
		}
		
		
	}
	@Override
	public CommonSingleResult<UserInfo> findUserPassword(UserInfo user) {
		// TODO Auto-generated method stub
		if(user.getUserEmail()==null || user.getUserEmail().equals("")||
			user.getUserName()==null || user.getUserName().equals("") ||
			user.getUserId()==null || user.getUserId().equals("")	){
			return new CommonSingleResult<UserInfo>(new CommonResult(CommonMsg.failCodeInvalidInput,
														CommonMsg.failMsgeInvalidInput));			
		}
		ParamsCommonFilter filter = new ParamsCommonFilter();
		Map<String, Object> k = new HashMap<String, Object>();
		k.put("userEmail", user.getUserEmail());
		k.put("userName", user.getUserName());
		k.put("userId", user.getUserId());
		filter.setColumns(k);
		SAUser resultUser = userDao.info(new SAUser(),filter);
		
		if(resultUser==null ){
			return new CommonSingleResult<UserInfo>(new CommonResult(CommonMsg.failCodeNotFound,
					CommonMsg.failMsgNotFound));
		}else {
		
			String newPw = this.randomPassword(8);
			// 비밀번호 초기화
			ParamsCommonNamespace param = new ParamsCommonNamespace();
			Map<String, Object> cols = new HashMap<String, Object>();
			cols.put("USR_NO", resultUser.getUSR_NO());
			cols.put("USR_NPWD", newPw);
			param.setColumns(cols, "ForPassword");
			if (userDao.update(new SAUser(),param)==1) {				
				// 이메일 발송
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("managerPw", newPw);
				MailSend mailDto = new MailSend(CommonMsg.EmailMsgService.ADD_FROM,
						resultUser.getUSR_EML(), 
						map,
						CommonMsg.EmailMsgService.RESET_MSG , 
						CommonMsg.EmailMsgService.RESET_EAMIL);
				try{
					mail.mailSend(mailDto);
				}catch(Exception e){
					e.printStackTrace();
				}
				return new CommonSingleResult<UserInfo>(new CommonResult(CommonMsg.successCode,
						CommonMsg.successMsg),new UserInfo(resultUser));
			} else {
				return new CommonSingleResult<UserInfo>(
						new CommonResult(CommonMsg.failCodeNotFound, CommonMsg.failMsgNotFound),
						null);
			}
		}
	}

	@Override
	public CommonSingleResult<UserInfo> updatePassword(UserInfo user, String newPw) {
		CommonSingleResult<UserInfo> result; 
		
		if(user.getUserNo()==null || user.getUserNo().equals("") ||
				user.getUserPassword() == null || user.getUserPassword().equals("")){
			result =  new CommonSingleResult<UserInfo>(new CommonResult(CommonMsg.failCodeInvalidInput,
														CommonMsg.failMsgeInvalidInput));			
		}
		
		ParamsCommonNamespace param = new ParamsCommonNamespace();
		Map<String, Object> k = new HashMap<String, Object>();
		k.put("USR_NO", user.getUserNo());
		k.put("USR_PWD", user.getUserPassword());
		k.put("USR_NPWD", newPw);
		
		param.setColumns(k,"ForPasswordCollect");
		
		SAUser resultUser = userDao.info(new SAUser(),param);
		if(resultUser == null ){
			result = new CommonSingleResult<UserInfo>(new CommonResult(FailCodeUserService.II_NO_PWD,
					FailMsgUserService.II_NO_PWD));
		}else {
			param.setColumns(k, "ForPassword");
			if (userDao.update(new SAUser(),param)==1) {				
				result = new CommonSingleResult<UserInfo>(
						new CommonResult(CommonMsg.successCode, CommonMsg.successMsg),
						user);
			} else {
				result = new CommonSingleResult<UserInfo>(
						new CommonResult(CommonMsg.failCodeNotFound, CommonMsg.failMsgNotFound),
						null);
			}
		}
		return result;
	
	}
	
	public String randomPassword (int length){
		int index=0;
		char[] charSet = new char[] {
				'0','1','2','3','4','5','6','7','8','9'
				,'q','w','e','r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m'
				,'Q','W','E','R','T','Y','U','I','O','P','A','S','D','F','G','H','J','K','L','Z','X','C','V','B','N','M'
		};
		StringBuffer sb  = new StringBuffer();
		for(int i=0; i<length; i++){
			index = (int) (charSet.length * Math.random());
			sb.append(charSet[index]);
		}
		return sb.toString();
	}
}
