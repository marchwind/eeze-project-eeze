package com.kobaco.smartad.service;

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
import com.kobaco.smartad.model.data.SAPMCManager;
import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.MailSend;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.PmcSessionInfo;
import com.kobaco.smartad.utils.CommonCode;
import com.kobaco.smartad.utils.CommonMsg;
import com.kobaco.smartad.utils.email.MailSendService;

@Service
public class PmcManagerServiceImpl implements PmcManagerService {
	

	@Autowired
	CommonDao<SAPMCManager> managerDao;
	@Autowired(required=false)
	MailSendService mail = new MailSendService() ;	

	@Override
	public CommonSingleResult<PmcMnagerInfo> getPmcMangerList(PmcMnagerInfo info,PmcSessionInfo sessUser) {
		// TODO Auto-generated method stub
		CommonSingleResult<PmcMnagerInfo> result = new CommonSingleResult<PmcMnagerInfo>();
		if(!sessUser.isLogin() ||info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
			return result;
		}
		if(info.getManagerNo()==null || info.getManagerNo().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}	
		
		SAPMCManager saResult = managerDao.info(new SAPMCManager(info));
		
		if(saResult==null){			
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound,CommonMsg.failMsgNotFound));
			return result;
		}
		
		info = new PmcMnagerInfo(saResult);
		info.setPosition(positionChangeMsg(saResult.getPST_CD()));		
		result.setInfo(info);
		result.setResult(new CommonResult(CommonMsg.successCode, CommonMsg.successMsg));		
		return result;
	}

	@Override
	public CommonSingleResult<PmcMnagerInfo> login(PmcMnagerInfo info) {
		// TODO Auto-generated method stub
		CommonSingleResult<PmcMnagerInfo> result = new CommonSingleResult<PmcMnagerInfo>();
		if(info.getManagerMode()==null || info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
			return result;
		}
		if(info.getManagerId()==null || info.getManagerId().equals("")||
		   info.getManagerPassword()==null || info.getManagerPassword().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}	
		ParamsCommonNamespace login = new ParamsCommonNamespace();
		Map<String, Object> loginMap = new HashMap<String, Object>();
		loginMap.put("managerId", info.getManagerId());
		loginMap.put("managerPassword", info.getManagerPassword());
		login.setColumns(loginMap, "login");	
		SAPMCManager saResult = managerDao.info(new SAPMCManager(),login);
		
		if(saResult==null){			
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound,CommonMsg.failMsgNotFound));
			return result;
		}
		
		info = new PmcMnagerInfo(saResult);
		result.setInfo(info);
		result.setResult(new CommonResult(CommonMsg.successCode, CommonMsg.successMsg));		
		return result;
	}

	@Override
	public CommonSingleResult<PmcMnagerInfo>  logout(PmcSessionInfo session) {
		// TODO Auto-generated method stub
		if(session.isLogin()){
			return 	new CommonSingleResult<PmcMnagerInfo> (new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));			
		}else {
			return 	new CommonSingleResult<PmcMnagerInfo> (new CommonResult(CommonMsg.failCode,CommonMsg.failMsg)); 
		}
	}

	@Override
	public CommonListResult<PmcMnagerInfo> getPmcMnagerList(PmcMnagerInfo info,CommonPage cp, PmcSessionInfo sessUser) {
		// TODO Auto-generated method stub
		CommonListResult<PmcMnagerInfo> result = new CommonListResult<PmcMnagerInfo>();
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
		countMap.put("managerId", info.getManagerId());
		countMap.put("department", info.getDepartment());
		count.setColumns(countMap);
		int totalCount =   managerDao.count(new SAPMCManager(),count);
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
		
		List<SAPMCManager> listResult =   managerDao.list(new SAPMCManager(),agg);
		List<PmcMnagerInfo> pmclistResult = new ArrayList<PmcMnagerInfo>();
		for(SAPMCManager managerResult : listResult){
			PmcMnagerInfo infoResult = new PmcMnagerInfo(managerResult);
			infoResult.setPosition(positionChangeMsg(managerResult.getPST_CD()));	
			pmclistResult.add(infoResult);
		}
		result.setList(pmclistResult);
		result.setPage(cp);
		result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
		return result;
	}

	@Override
	public CommonSingleResult<PmcMnagerInfo> getPmcMangerRegister(PmcMnagerInfo info, PmcSessionInfo sessUser) {
		// TODO Auto-generated method stub
		CommonSingleResult<PmcMnagerInfo> result = new CommonSingleResult<PmcMnagerInfo>();
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null || 
				info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}
		
		if(info.getManagerId()==null || info.getManagerId().equals("") ||
				info.getManagerPassword()==null || info.getManagerPassword().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		
		SAPMCManager saResult = new SAPMCManager(info);
		saResult.setCNFM_YN(CommonCode.PmcManagerCode.PMC_YES);
		saResult.setCNFM_DTT(new Date());
		saResult.setCNFR_ID(sessUser.getManagerId());
		saResult.setBLT(info.getDepartment());
		saResult.setREG_DTT(new Date());
		saResult.setREG_ID(sessUser.getManagerId());
		saResult.setUPD_DTT(new Date());
		saResult.setUPD_ID(sessUser.getManagerId());
		saResult.setMNGR_STTS_CD(CommonCode.PmcManagerStateCd.Normal);
		
		SAPMCManager addResult = managerDao.insert(saResult);
		if(addResult!=null){
			result.setInfo(new PmcMnagerInfo(saResult));
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
			return result;
		}else{
			result.setResult(new CommonResult(CommonMsg.FailCodePmcMnagerService.II_PM_REGISTER,CommonMsg.FailMsgCRUDService.II_REGISTER));
			return result;
		}	
	}

	public String positionChangeMsg(String positionCd){

		if(positionCd == null) return null;
		
		if(positionCd.equals(CommonCode.PmcManagerCode.Position_U01)){
			return CommonCode.PmcManagerMsg.Position_U01;
		}else if(positionCd.equals(CommonCode.PmcManagerCode.Position_U02)){
			return CommonCode.PmcManagerMsg.Position_U02;
		}else if(positionCd.equals(CommonCode.PmcManagerCode.Position_U03)){
			return CommonCode.PmcManagerMsg.Position_U03;
		}else if(positionCd.equals(CommonCode.PmcManagerCode.Position_U04)){
			return CommonCode.PmcManagerMsg.Position_U04;
		}else if(positionCd.equals(CommonCode.PmcManagerCode.Position_U05)){
			return CommonCode.PmcManagerMsg.Position_U05;
		}else {
			return CommonCode.PmcManagerMsg.Position_U06;
		}
	}
	public String positionChangeCode(String positionCd){
		
		if(positionCd == null) return null;
		
		if(positionCd.equals(CommonCode.PmcManagerMsg.Position_U01)){
			return CommonCode.PmcManagerCode.Position_U01;
		}else if(positionCd.equals(CommonCode.PmcManagerMsg.Position_U02)){
			return CommonCode.PmcManagerCode.Position_U02;
		}else if(positionCd.equals(CommonCode.PmcManagerMsg.Position_U03)){
			return CommonCode.PmcManagerCode.Position_U03;
		}else if(positionCd.equals(CommonCode.PmcManagerMsg.Position_U04)){
			return CommonCode.PmcManagerCode.Position_U04;
		}else if(positionCd.equals(CommonCode.PmcManagerMsg.Position_U05)){
			return CommonCode.PmcManagerCode.Position_U05;
		}else {
			return CommonCode.PmcManagerCode.Position_U06;
		}
	}

	@Override
	public CommonSingleResult<PmcMnagerInfo> getPmcMangerSubscribe(
			PmcMnagerInfo info) {
		// TODO Auto-generated method stub
		CommonSingleResult<PmcMnagerInfo> result = new CommonSingleResult<PmcMnagerInfo>();		
		if(info.getManagerId()==null || info.getManagerId().equals("") ||
				info.getManagerPassword()==null || info.getManagerPassword().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		SAPMCManager saResult = new SAPMCManager(info);
		saResult.setCNFM_YN(CommonCode.PmcManagerCode.PMC_NOT);		
		saResult.setBLT(info.getDepartment());
		saResult.setREG_DTT(new Date());
		saResult.setREG_ID(info.getManagerId());
		saResult.setUPD_DTT(new Date());
		saResult.setUPD_ID(info.getManagerId());
		saResult.setMNGR_STTS_CD(CommonCode.PmcManagerStateCd.Waiting);
		
		SAPMCManager addResult = managerDao.insert(saResult);
		if(addResult!=null){
			result.setInfo(new PmcMnagerInfo(saResult));
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
			return result;
		}else{
			result.setResult(new CommonResult(CommonMsg.FailCodePmcMnagerService.II_PM_REGISTER,CommonMsg.FailMsgCRUDService.II_REGISTER));
			return result;
		}	
	}

	@Override
	public CommonSingleResult<PmcMnagerInfo> getPmcMangerUpdate(
			PmcMnagerInfo info, PmcSessionInfo sessUser) {
		// TODO Auto-generated method stub
		CommonSingleResult<PmcMnagerInfo> result = new CommonSingleResult<PmcMnagerInfo>();
		if(sessUser.getManagerNo()==null ||sessUser.getManagerNo().equals("") || info.getManagerMode()==null || 
				info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}
		
		//info.setManagerNo(sessUser.getManagerNo());		
		SAPMCManager saResult = managerDao.info(new SAPMCManager(info));
		if(info.getPosition()!=null && info.getPosition().equals("")){
			saResult.setPST_CD(positionChangeCode(info.getPosition()));
		}
		saResult.setMNGR_NM(info.getManagerName());
		saResult.setMNGR_EML(info.getManagerEmail());
		saResult.setPWD(info.getManagerPassword());
		saResult.setBLT(info.getDepartment());
		saResult.setUPD_ID(info.getManagerId());
		saResult.setCPHN(info.getCellPhone());
		saResult.setPHN(info.getPhone());
		int resultUpdate = managerDao.update(saResult);
		if(resultUpdate>0){
			result.setInfo(new PmcMnagerInfo(saResult));
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
			return result;
		}else{
			result.setResult(new CommonResult(CommonMsg.FailCodePmcMnagerService.II_PM_UPDATE,CommonMsg.FailMsgCRUDService.II_UPDATE));
			return result;
		}
	
	}

	@Override
	public CommonSingleResult<PmcMnagerInfo> getPmcMangerModify(
			PmcMnagerInfo info, PmcSessionInfo sessUser) {
		// TODO Auto-generated method stub
		CommonSingleResult<PmcMnagerInfo> result = new CommonSingleResult<PmcMnagerInfo>();
		
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null || 
				info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}
		if(info.getManagerNo()==null || info.getManagerNo().equals("") ||
				info.getManagerPassword()==null || info.getManagerPassword().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		
		ParamsCommonNamespace param = new ParamsCommonNamespace();
		Map<String,Object> columns = new HashMap<String, Object>();
		columns.put("managerNo", info.getManagerNo());
		columns.put("managerPw", info.getManagerPassword());
		param.setColumns(columns, "Password");
		int modifyResult = managerDao.update(new SAPMCManager(), param);
		if(modifyResult==1){
			result.setInfo(info);
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
			return result;
		}else {
			result.setResult(new CommonResult(CommonMsg.FailCodePmcMnagerService.II_PM_UPDATE,CommonMsg.FailMsgCRUDService.II_UPDATE));
			return result;
		}
	}

	@Override
	public CommonSingleResult<PmcMnagerInfo> getPmcMangerDelete(
			PmcMnagerInfo info, PmcSessionInfo sessUser) {
		// TODO Auto-generated method stub
		CommonSingleResult<PmcMnagerInfo> result = new CommonSingleResult<PmcMnagerInfo>();
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null || 
				info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}
		if(info.getManagerNo()==null || info.getManagerNo().equals("") ){	
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		ParamsCommonNamespace param = new ParamsCommonNamespace();
		Map<String,Object> columns = new HashMap<String, Object>();
		columns.put("managerNo", info.getManagerNo());
		param.setColumns(columns, "Delete");
		int modifyResult = managerDao.update(new SAPMCManager(), param);
		if(modifyResult==1){
			result.setInfo(info);
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
			return result;
		}else {
			result.setResult(new CommonResult(CommonMsg.FailCodePmcMnagerService.II_PM_UPDATE,CommonMsg.FailMsgCRUDService.II_UPDATE));
			return result;
		}
	}

	@Override
	public CommonSingleResult<PmcMnagerInfo> getPmcMangerAck(
			PmcMnagerInfo info, PmcSessionInfo sessUser) {
		// TODO Auto-generated method stub
		CommonSingleResult<PmcMnagerInfo> result = new CommonSingleResult<PmcMnagerInfo>();
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null || 
				info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}
		if(info.getManagerNo()==null || info.getManagerNo().equals("") ){	
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		ParamsCommonNamespace param = new ParamsCommonNamespace();
		Map<String,Object> columns = new HashMap<String, Object>();
		columns.put("CNFR_ID", sessUser.getManagerId());
		columns.put("CNFM_YN",CommonCode.PmcManagerCode.PMC_YES);
		columns.put("managerNo",info.getManagerNo());
		
		param.setColumns(columns, "Ack");
		int modifyResult = managerDao.update(new SAPMCManager(), param);
		if(modifyResult==1){
			result.setInfo(info);
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
			return result;
		}else {
			result.setResult(new CommonResult(CommonMsg.FailCodePmcMnagerService.II_PM_UPDATE,CommonMsg.FailMsgCRUDService.II_UPDATE));
			return result;
		}
	}

	@Override
	public CommonSingleResult<PmcMnagerInfo> getPmcMangerReset(PmcMnagerInfo info, PmcSessionInfo sessUser) {
		// TODO Auto-generated method stub
		CommonSingleResult<PmcMnagerInfo> result = new CommonSingleResult<PmcMnagerInfo>();
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null || 
				info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}
		if(info.getManagerNo()==null || info.getManagerNo().equals("") ){	
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		ParamsCommonNamespace param = new ParamsCommonNamespace();		
		Map<String,Object> columns = new HashMap<String, Object>();
		columns.put("managerNo", info.getManagerNo());
		columns.put("managerPw",randomPassword(10));
		param.setColumns(columns, "Password");
		int modifyResult = managerDao.update(new SAPMCManager(), param);
		if(modifyResult==1){
			
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
			SAPMCManager k = managerDao.info(new SAPMCManager(info));
			if(k!=null){				
				MailSend is = new MailSend(CommonMsg.EmailMsgService.ADD_FROM, k.getMNGR_EML(),columns 
						, CommonMsg.EmailMsgService.RESET_MSG
						,CommonMsg.EmailMsgService.RESET_EAMIL);
				mail.mailSend(is);
				info.setPwReset(CommonCode.PmcManagerCode.PMC_YES);
				result.setInfo(info);
			}
			
			return result;
		}else {
			info.setPwReset(CommonCode.PmcManagerCode.PMC_NOT);
			result.setInfo(info);
			result.setResult(new CommonResult(CommonMsg.FailCodePmcMnagerService.II_PM_UPDATE,CommonMsg.FailMsgCRUDService.II_UPDATE));
			return result;
		}
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
