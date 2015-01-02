package com.kobaco.smartad.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.kobaco.smartad.dao.CommonDao;
import com.kobaco.smartad.model.data.ParamsCommonFilter;
import com.kobaco.smartad.model.data.ParamsCommonNamespace;
import com.kobaco.smartad.model.data.SAFacilityCheck;
import com.kobaco.smartad.model.data.SAFacilityReserve;
import com.kobaco.smartad.model.data.SAFacilityReserveDate;
import com.kobaco.smartad.model.data.SAFacilityVisitHistory;
import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.ReserveDetail;
import com.kobaco.smartad.model.service.ReserveInfo;
import com.kobaco.smartad.model.service.UserInfo;
import com.kobaco.smartad.utils.CommonCode;
import com.kobaco.smartad.utils.CommonCode.PmcManagerCode;
import com.kobaco.smartad.utils.CommonMsg;
@Service
public class PmcReserveServiceImpl implements PmcReserveService{
	@Autowired
	private CommonDao<SAFacilityReserveDate> revdDao;
	@Autowired
	private DataSourceTransactionManager transManager;
	@Autowired
	private CommonDao<SAFacilityReserve> revDao;
	@Autowired
	private CommonDao<SAFacilityVisitHistory> revhDao;
	@Autowired
	private CommonDao<SAFacilityCheck> revcDao;

	
	@Override
	public CommonSingleResult<ReserveInfo> getPmcReserveAdd(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, UserInfo user, ReserveInfo rev,
			List<String> k) {
		CommonSingleResult<ReserveInfo> result = new CommonSingleResult<ReserveInfo>();
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null ||
			info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
			return result;
		}
		if(user.getUserNo()==null || user.getUserNo().equals("")||rev.getFacilityNo()==null
			||rev.getFacilityNo().equals("") ||	k.size()==0 ){	
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		ArrayList<ReserveDetail> resultArray = new ArrayList<ReserveDetail>();
		for(int ii=0; ii<k.size(); ii++){
			String[] half = k.get(ii).split("/");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			ReserveDetail detailArray = new ReserveDetail();	
			Date dateNew;
			try {
				dateNew = formatter.parse(half[0]);
				detailArray.setReserveDate(dateNew);
				detailArray.setReserveTimeZone(Integer.parseInt(half[1]));
				resultArray.add(detailArray);								
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		rev.setReserveDetail(resultArray);
		SAFacilityReserve set  = new SAFacilityReserve();
		set.setFCLT_MGMT_NO(rev.getFacilityNo());
		set.setUSR_NO(user.getUserNo());
		set.setRSRV_VSTN(rev.getVisitCount());
		set.setREG_ID(user.getUserId());
		set.setUPD_ID(user.getUserId());
		set.setCNCL_YN("N");
		set.setENTP_NM(rev.getEnterpriseName());
		set.setWRKR_NM(rev.getWorkerName());
		set.setWRK_CNTT(rev.getWorkContent());		
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("transaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);		
		TransactionStatus status = transManager.getTransaction(def);
		
		try{			
			SAFacilityReserve setResult =	revDao.insert(set);
			if(setResult!=null){
				
				for(int i=0; i<rev.getReserveDetail().size(); i++){
					SAFacilityReserveDate date = new SAFacilityReserveDate();
					date.setRSRV_NO(setResult.getRSRV_NO());
					date.setREG_ID(user.getUserId());
					date.setUPD_ID("PMC");
					date.setRSRV_DT(rev.getReserveDetail().get(i).getReserveDate());
					
					if(rev.getReserveDetail().get(i).getReserveTimeZone()==CommonCode.ReserveCode.Time_Zone_Am_Return){
						date.setTZ_TP_CD(CommonCode.ReserveCode.Time_Zone_Am);
					}else {
						date.setTZ_TP_CD(CommonCode.ReserveCode.Time_Zone_Pm);
					}
					
					SAFacilityReserveDate resultDate = revdDao.insert(date);
					
					if(resultDate!=null){					
						ReserveInfo infoResult = new ReserveInfo();
						infoResult.setReserveNo(setResult.getRSRV_NO());
						result.setInfo(infoResult);					
						result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
					}else {													
						result.setResult(new CommonResult(CommonMsg.FailCodeReserveService.II_RV_REGISTER,
								CommonMsg.FailMsgCRUDService.II_REGISTER));
						return result;
					}				
				}
				transManager.commit(status);
				return result;
			
			}else {
				transManager.rollback(status);				 				
				result.setResult(new CommonResult(CommonMsg.FailCodeReserveService.II_RV_REGISTER
						,CommonMsg.FailMsgCRUDService.II_REGISTER));
				return result;
			}		
			
			
		}catch (Exception e){
			transManager.rollback(status);	
			result.setResult(new CommonResult(CommonMsg.FailCodeReserveService.II_RV_REGISTER,CommonMsg.FailMsgCRUDService.II_REGISTER));
			return result;
		}
	}
	@Override
	public CommonListResult<ReserveInfo> getPmcReserveList(PmcMnagerInfo info,	CommonPage cp, PmcMnagerInfo sessUser, ReserveInfo revInfo) {
		CommonListResult<ReserveInfo> result = new CommonListResult<ReserveInfo>();	
		if(revInfo==null){
			CommonResult k = new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput);
			result.setResult(k);
			return result;
		}
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null ||
				info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}
		if(revInfo.getFacilityNo()==null||revInfo.getFacilityNo().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
				return result;
		}
		
		ParamsCommonFilter filter = new ParamsCommonFilter();
		Map<String, Object> pp = new HashMap<String, Object>();
		pp.put("facilityiesNo",revInfo.getFacilityNo());
		pp.put("date",revInfo.getReserveYear()+"-"+revInfo.getReserveMonth());
		filter.setColumns(pp);			

		/*List<SAFacilityReserve> checkVisit = revDao.list(new SAFacilityReserve(),filter);	
		
		if(checkVisit.size()>0){
			for(SAFacilityReserve check :checkVisit){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String vst = formatter.format(check.getRSRV_DT());
				String now = formatter.format(new Date());
				SimpleDateFormat formatter1 = new SimpleDateFormat("HH");
				int beforeVst =0;
				int beforeNow = Integer.parseInt(formatter1.format(new Date()));
				if(check.getTZ_TP_CD()==CommonCode.ReserveCode.Time_Zone_Am){
					beforeVst =12;
				}else {
					beforeVst =18;
				}				
				if(beforeVst<=beforeNow && vst.equals(now)){
					if(check.getVST_NO()==null || check.getVST_NO().equals("")){
						SAFacilityVisitHistory in = new SAFacilityVisitHistory();
						in.setVST_DTT(new Date());
						in.setRSRV_NO(check.getRSRV_NO());
						in.setRSRV_DTL_NO(check.getRSRV_DTL_NO());
						in.setCHKIN_CNFMR(sessUser.getManagerId());
						in.setCHKIN_DTT(new Date());
						in.setREG_ID(sessUser.getManagerId());
						in.setUPD_ID("PMC");
						SAFacilityVisitHistory v = revhDao.insert(in);
					}
				}				
			}		
		}	*/
		
		
		List<SAFacilityReserve> infoResult = revDao.list(new SAFacilityReserve(),filter);	
		List<ReserveInfo>  listResult = new ArrayList<ReserveInfo>();
	
		if(infoResult.size()>0){	
			for(int i= 0; i<infoResult.size(); i++){
				ReserveInfo is = new ReserveInfo();
				if(i!=infoResult.size()-1){							
					if(!infoResult.get(i).getRSRV_NO().equals(infoResult.get(i+1).getRSRV_NO())){					
						is.setFacilityName(infoResult.get(i).getFCLT_NM());
						is.setFacilityNo(infoResult.get(i).getFCLT_MGMT_NO());
						is.setReserveNo(infoResult.get(i).getRSRV_NO());
						listResult.add(is);
					}
				}else{					
						is.setFacilityName(infoResult.get(i).getFCLT_NM());
						is.setFacilityNo(infoResult.get(i).getFCLT_MGMT_NO());
						is.setReserveNo(infoResult.get(i).getRSRV_NO());
						listResult.add(is);
										
				}
			}
	
			for(int i=0; i<listResult.size();i++){
				ArrayList<ReserveDetail>  get = new ArrayList<ReserveDetail>();
				for(int k=0; k<infoResult.size();k++){					
					if(listResult.get(i).getReserveNo().equals(infoResult.get(k).getRSRV_NO())){
						ReserveDetail detail = new ReserveDetail();
						detail.setUserNo(infoResult.get(k).getUSR_NO());
						detail.setReserveDate(infoResult.get(k).getRSRV_DT());
						detail.setReserveDetailNo(infoResult.get(k).getRSRV_DTL_NO());
						detail.setEnterpriseName(infoResult.get(k).getENTP_NM());
						if(infoResult.get(k).getCHKIN_CNFMR()!=null && infoResult.get(k).getCHKOUT_CNFMR()!=null  ){
							detail.setCheckInYn(CommonCode.PmcManagerCode.PMC_YES);
							detail.setCheckOutYn(CommonCode.PmcManagerCode.PMC_YES);
						}else if(infoResult.get(k).getCHKIN_CNFMR()!=null && infoResult.get(k).getCHKOUT_CNFMR()==null) {
							detail.setCheckInYn(CommonCode.PmcManagerCode.PMC_YES);
							detail.setCheckOutYn(CommonCode.PmcManagerCode.PMC_NOT);
						}
						else if(infoResult.get(k).getCHKIN_CNFMR()==null && infoResult.get(k).getCHKOUT_CNFMR()!=null ){
							detail.setCheckInYn(CommonCode.PmcManagerCode.PMC_NOT);
							detail.setCheckOutYn(CommonCode.PmcManagerCode.PMC_YES);
						}else{
							detail.setCheckInYn(CommonCode.PmcManagerCode.PMC_NOT);
							detail.setCheckOutYn(CommonCode.PmcManagerCode.PMC_NOT);
						}
						if(infoResult.get(k).getTZ_TP_CD().equals(CommonCode.ReserveCode.Time_Zone_Am)){
							detail.setReserveTimeZone(1);
						}else {
							detail.setReserveTimeZone(2);
						}
						get.add(detail);
					}
				}			
				listResult.get(i).setReserveDetail(get);			
			}
			CommonResult k = new CommonResult(CommonMsg.successCode,CommonMsg.successMsg);	
			result.setResult(k);
			result.setList(listResult);
		} else {
			CommonResult k = new CommonResult(CommonMsg.failCodeNotFound,CommonMsg.failMsgNotFound);
			result.setResult(k);
		}
		return result;
	}
	@Override
	public CommonSingleResult<ReserveInfo> getPmcReserveGet(PmcMnagerInfo info,PmcMnagerInfo sessUser, ReserveInfo rev) {
		CommonSingleResult<ReserveInfo> result = new CommonSingleResult<ReserveInfo>();
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null ||
			info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
			return result;
		}
		if(rev==null){	
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		ParamsCommonNamespace param = new ParamsCommonNamespace();
		Map<String,Object> columns = new HashMap<String, Object>();
		columns.put("reserveDetailNo",rev.getReserveDetailNo() );
		columns.put("reserveNo", rev.getReserveNo());
		param.setColumns(columns, "Get");
		
		SAFacilityReserve revResult  = revDao.info(new SAFacilityReserve(), param);
	
		if(revResult!=null){	
			ReserveInfo i = new ReserveInfo(revResult);
			if(revResult.getTZ_TP_CD().equals(CommonCode.ReserveCode.Time_Zone_Am)){
				i.setReserveTimeZone(1);
			}else {
				i.setReserveTimeZone(2);
			}
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
			result.setInfo(i);
		}else{
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound,CommonMsg.failMsgNotFound));
		}
		return result;
	}
	@Override
	public CommonSingleResult<ReserveInfo> setPmcReserveCheckIn(	PmcMnagerInfo info, PmcMnagerInfo sessUser, ReserveInfo rev) {
		CommonSingleResult<ReserveInfo> result = new CommonSingleResult<ReserveInfo>();
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null ||
			info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
			return result;
		}
		if(rev==null ||  rev.getReserveDetailNo()==null || rev.getReserveDetailNo().equals("") 
			|| rev.getReserveNo()==null || rev.getReserveNo().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		SAFacilityVisitHistory t = new SAFacilityVisitHistory();
		t.setCHKIN_DTT(new Date());
		t.setCHKIN_CNFMR(sessUser.getManagerId());
		t.setREG_ID(sessUser.getManagerId());
		t.setUPD_ID(sessUser.getManagerId());
		t.setRSRV_NO(rev.getReserveNo());
		t.setRSRV_DTL_NO(rev.getReserveDetailNo());
		SAFacilityVisitHistory visit = revhDao.insert(t);
		if(visit!=null){
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));			
			result.setInfo(rev);
		}else {
			result.setResult(new CommonResult(CommonMsg.FailCodeReserveService.II_RV_REGISTER,
					CommonMsg.FailMsgCRUDService.II_REGISTER));
		}		
		return result;
	}
	@Override
	public CommonSingleResult<ReserveInfo> getPmcReserveGetCheckIn(PmcMnagerInfo info, PmcMnagerInfo sessUser, ReserveInfo rev) {
		CommonSingleResult<ReserveInfo> result = new CommonSingleResult<ReserveInfo>();
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null ||
			info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
			return result;
		}
		if(rev==null){	
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		ParamsCommonNamespace param = new ParamsCommonNamespace();
		Map<String,Object> columns = new HashMap<String, Object>();
		columns.put("reserveDetailNo",rev.getReserveDetailNo() );
		columns.put("reserveNo", rev.getReserveNo());
		param.setColumns(columns, "Get");
		
		SAFacilityReserve revResult  = revDao.info(new SAFacilityReserve(), param);
		if(revResult!=null){	
			ReserveInfo in = new ReserveInfo(revResult);
			if(revResult.getCHKIN_CNFMR()!=null ){
				in.setCheckInYn(CommonCode.PmcManagerCode.PMC_YES);
			}else{
				in.setCheckInYn(CommonCode.PmcManagerCode.PMC_NOT);
			}
			if(revResult.getCHKOUT_CNFMR()!=null ){
				in.setCheckOutYn(CommonCode.PmcManagerCode.PMC_YES);
			}else{
				in.setCheckOutYn(CommonCode.PmcManagerCode.PMC_NOT);
			}
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
			result.setInfo(in);
		}else{
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound,CommonMsg.failMsgNotFound));
		}
		return result;
	}
	@Override
	public CommonSingleResult<ReserveInfo> pmcReserveCancle(PmcMnagerInfo info,	PmcMnagerInfo sessUser, ReserveInfo rev) {
		CommonSingleResult<ReserveInfo> result = new CommonSingleResult<ReserveInfo>();
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null ||
			info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
			return result;
		}
		if(rev==null ||  rev.getReserveDetailNo()==null || rev.getReserveDetailNo().equals("") 
			|| rev.getReserveNo()==null || rev.getReserveNo().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		
			ParamsCommonFilter filter = new ParamsCommonFilter();
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("RSRV_DTL_NO", rev.getReserveDetailNo());
			filter.setColumns(map);
			int revdResult = revdDao.update(new SAFacilityReserveDate(), filter);
			if(revdResult>0){
				result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));			
				result.setInfo(rev);
				return result;
			}else{
				result.setResult(new CommonResult(CommonMsg.FailCodeReserveService.II_RV_DELETE,
						CommonMsg.FailMsgCRUDService.II_DELETE));
				return result;
			}
		}
	@Override
	public CommonSingleResult<ReserveInfo> pmcReserveCheckOut(
			PmcMnagerInfo info, PmcMnagerInfo sessUser, ReserveInfo rev) {
		CommonSingleResult<ReserveInfo> result = new CommonSingleResult<ReserveInfo>();
		if(sessUser.getManagerNo()==null || sessUser.getManagerNo().equals("") ||info.getManagerMode()==null ||
			info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
			return result;
		}
		if(rev==null ||  rev.getFacilityCheck()==null || rev.getFacilityCheck().equals("") 
			|| rev.getReserveNo()==null || rev.getReserveNo().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("transaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);		
		TransactionStatus status = transManager.getTransaction(def);
		
		ParamsCommonNamespace param = new ParamsCommonNamespace();
		Map<String,Object> columns = new HashMap<String, Object>();
		columns.put("reserveDetailNo",rev.getReserveDetailNo() );
		columns.put("reserveNo", rev.getReserveNo());
		param.setColumns(columns, "Check");
		
		SAFacilityReserve revResult  = revDao.info(new SAFacilityReserve(), param);
		
		if(revResult!=null){
		
			if((revResult.getCVST_NO()==null || revResult.getCVST_NO().equals("")) &&
				revResult.getVST_NO()==null || revResult.getVST_NO().equals("")){
				SAFacilityVisitHistory t = new SAFacilityVisitHistory();
				t.setCHKOUT_DTT(new Date());
				t.setCHKOUT_CNFMR(sessUser.getManagerId());
				t.setREG_ID(sessUser.getManagerId());
				t.setUPD_ID("PMC");
				t.setRSRV_NO(rev.getReserveNo());
				t.setRSRV_DTL_NO(rev.getReserveDetailNo());
				SAFacilityVisitHistory his = revhDao.insert(t);
					if(his==null){
						result.setResult(new CommonResult(CommonMsg.FailCodeReserveService.II_RV_REGISTER,
								CommonMsg.FailMsgCRUDService.II_REGISTER));
						return result;
					}else{
						SAFacilityCheck k = new SAFacilityCheck();
						k.setFCLT_MGMT_NO(revResult.getFCLT_MGMT_NO());
						k.setCVST_NO(his.getVST_NO());
						k.setCHK_TP(CommonCode.PmcReserveCode.Check_R03);
						k.setCHK_DTT(new Date());
						k.setCHKR(sessUser.getManagerId());
						k.setCHK_CNTT(rev.getFacilityCheck());
						k.setREG_ID(sessUser.getManagerId());
						k.setUPD_ID("PMC");					
						SAFacilityCheck c = revcDao.insert(k);
						if(c==null){
							result.setResult(new CommonResult(CommonMsg.FailCodeReserveService.II_RV_REGISTER,
									CommonMsg.FailMsgCRUDService.II_REGISTER));
							return result;
						}
					}
			}else if(revResult.getCVST_NO() !=null && (!revResult.getCVST_NO().equals(revResult.getVST_NO()) ) &&
					(revResult.getVST_NO()!=null || !revResult.getVST_NO().equals(""))){
				SAFacilityCheck k = new SAFacilityCheck();
				k.setFCLT_MGMT_NO(revResult.getFCLT_MGMT_NO());
				k.setCVST_NO(revResult.getVST_NO());
				k.setCHK_TP(CommonCode.PmcReserveCode.Check_R03);
				k.setCHK_DTT(new Date());
				k.setCHKR(sessUser.getManagerId());
				k.setCHK_CNTT(rev.getFacilityCheck());
				k.setREG_ID(sessUser.getManagerId());
				k.setUPD_ID("PMC");					
				SAFacilityCheck c = revcDao.insert(k);
					if(c==null){
						result.setResult(new CommonResult(CommonMsg.FailCodeReserveService.II_RV_REGISTER,
								CommonMsg.FailMsgCRUDService.II_REGISTER));
						return result;
					}
			}else if(revResult.getCVST_NO()!=null && revResult.getVST_NO()==null) {
				SAFacilityVisitHistory t = new SAFacilityVisitHistory();
				t.setCHKOUT_DTT(new Date());
				t.setCHKOUT_CNFMR(sessUser.getManagerId());
				t.setREG_ID(sessUser.getManagerId());
				t.setUPD_ID("PMC");
				t.setRSRV_NO(rev.getReserveNo());
				t.setRSRV_DTL_NO(rev.getReserveDetailNo());
				SAFacilityVisitHistory his = revhDao.insert(t);
				if(his==null){
					result.setResult(new CommonResult(CommonMsg.FailCodeReserveService.II_RV_REGISTER,
							CommonMsg.FailMsgCRUDService.II_REGISTER));
					return result;
				}
			}else {
				
				SAFacilityVisitHistory t = new SAFacilityVisitHistory();
				t.setCHKOUT_DTT(new Date());
				t.setCHKOUT_CNFMR(sessUser.getManagerId());
				t.setVST_NO(revResult.getVST_NO());
				int his = revhDao.update(t);			
				
				SAFacilityCheck k = new SAFacilityCheck();			
				k.setFCLT_CHK_NO(revResult.getFCLT_CHK_NO());
				k.setCHK_TP(CommonCode.PmcReserveCode.Check_R03);
				k.setCHK_DTT(new Date());
				k.setCHK_CNTT(rev.getFacilityCheck());
				k.setCHKR(sessUser.getManagerId());						
				int c = revcDao.update(k);
				if(c==0){
					result.setResult(new CommonResult(CommonMsg.FailCodeReserveService.II_RV_UPDATE,
					CommonMsg.FailMsgCRUDService.II_UPDATE));
				return result;
				}
			}
		
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));			
			result.setInfo(rev);
			transManager.commit(status);		
		}
		
		return result;
	}
	
	

}
