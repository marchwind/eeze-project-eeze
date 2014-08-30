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
import com.kobaco.smartad.model.data.ParamsCommonAggregator;
import com.kobaco.smartad.model.data.ParamsCommonFilter;
import com.kobaco.smartad.model.data.ParamsCommonPage;
import com.kobaco.smartad.model.data.ParamsForReserveMyPage;
import com.kobaco.smartad.model.data.SAFacilityReserve;
import com.kobaco.smartad.model.data.SAFacilityReserveDate;
import com.kobaco.smartad.model.data.SAFacilityVisitHistory;
import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.ReserveDetail;
import com.kobaco.smartad.model.service.ReserveInfo;
import com.kobaco.smartad.model.service.UserInfo;
import com.kobaco.smartad.utils.CommonCode;
import com.kobaco.smartad.utils.CommonMsg;


@Service
public class ReserveServiceImpl implements ReserveService{

	@Autowired
	private CommonDao<SAFacilityReserve> revDao;
	@Autowired
	private CommonDao<SAFacilityVisitHistory> revhDao;
	@Autowired
	private CommonDao<SAFacilityReserveDate> revdDao;
	@Autowired
	private DataSourceTransactionManager transManager;
	
	@Override
	public CommonListResult<ReserveInfo> getReserveList(ReserveInfo revInfo) {
		// TODO Auto-generated method stub
		CommonListResult<ReserveInfo> result = new CommonListResult<ReserveInfo>();	
		if(revInfo==null){
			CommonResult k = new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput);
			result.setResult(k);
			return result;
		}
		ParamsCommonFilter filter = new ParamsCommonFilter();
		Map<String, Object> pp = new HashMap<String, Object>();
		pp.put("facilityiesNo",revInfo.getFacilityNo());
		pp.put("date",revInfo.getReserveYear()+"-"+revInfo.getReserveMonth());
		filter.setColumns(pp);			
		List<SAFacilityReserve> info = revDao.list(new SAFacilityReserve(),filter);	
		List<ReserveInfo>  listResult = new ArrayList<ReserveInfo>();
	
		if(info.size()>0){	
			for(int i= 0; i<info.size(); i++){
				ReserveInfo is = new ReserveInfo();
				if(i!=info.size()-1){							
					if(!info.get(i).getRSRV_NO().equals(info.get(i+1).getRSRV_NO())){					
						is.setFacilityName(info.get(i).getFCLT_NM());
						is.setFacilityNo(info.get(i).getFCLT_MGMT_NO());
						is.setReserveNo(info.get(i).getRSRV_NO());
						listResult.add(is);
					}
				}else{					
						is.setFacilityName(info.get(i).getFCLT_NM());
						is.setFacilityNo(info.get(i).getFCLT_MGMT_NO());
						is.setReserveNo(info.get(i).getRSRV_NO());
						listResult.add(is);
										
				}
			}
	
			for(int i=0; i<listResult.size();i++){
				ArrayList<ReserveDetail>  get = new ArrayList<ReserveDetail>();
				for(int k=0; k<info.size();k++){					
					if(listResult.get(i).getReserveNo().equals(info.get(k).getRSRV_NO())){
						ReserveDetail detail = new ReserveDetail();
						detail.setUserNo(info.get(k).getUSR_NO());
						detail.setReserveDate(info.get(k).getRSRV_DT());
						detail.setReserveDetailNo(info.get(k).getRSRV_DTL_NO());
						if(info.get(k).getTZ_TP_CD().equals(CommonCode.ReserveCode.Time_Zone_Am)){
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
	public CommonListResult<ReserveInfo> getUserReserveList(ReserveInfo revInfo,UserInfo sessUser, CommonPage page) {
		// TODO Auto-generated method stub
		CommonListResult<ReserveInfo> result = new CommonListResult<ReserveInfo>();
		if (sessUser.getUserNo() == null) {
			CommonResult k = new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized);
			result.setResult(k);
			return result;
		}
		
		if(page.getCurrentPage()<=0 ||page.getUnitPerPage() <=0 ||revInfo==null){
			CommonResult k = new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput);
			result.setResult(k);
			return result;
		}
		
		ParamsForReserveMyPage filter = new ParamsForReserveMyPage(revInfo.getFacilityNo(),sessUser.getUserNo());	

		ParamsCommonPage p = new ParamsCommonPage();
		p.setCurrentPage(page.getCurrentPage());
		p.setUnitPerPage(page.getUnitPerPage());		
	
		ParamsCommonAggregator list = new ParamsCommonAggregator();
		list.filter(filter);
		list.page(p);
		
		ParamsCommonFilter count = new ParamsCommonFilter();
		Map<String,Object> countMap = new HashMap<String, Object>();
		countMap.put("userNo", sessUser.getUserNo());
		count.setColumns(countMap);
		
		int totalCount = revDao.count(new SAFacilityReserve(), count);
		
		List<SAFacilityReserve> info = revDao.list(new SAFacilityReserve(),list);	
		int totalPage =   (int) Math.ceil((double)totalCount /(double) page.getUnitPerPage() );		
		List<ReserveInfo>  listResult = new ArrayList<ReserveInfo>();
		
	
		if(info.size()>0){	
			for(int i= 0; i<info.size(); i++){
				ReserveInfo is = new ReserveInfo();
					is.setFacilityName(info.get(i).getFCLT_NM());
					is.setFacilityNo(info.get(i).getFCLT_MGMT_NO());
					is.setReserveNo(info.get(i).getRSRV_NO());
					is.setReserveStartDate(info.get(i).getRSRV_DT());
					is.setUserNo(info.get(i).getUSR_NO());
					is.setReserveDetailNo(info.get(i).getRSRV_DTL_NO());
					is.setFacilityCheck(info.get(i).getCHK_CNTT());
					if(info.get(i).getTZ_TP_CD().equals(CommonCode.ReserveCode.Time_Zone_Am)){
						is.setReserveTimeZone(CommonCode.ReserveCode.Time_Zone_Am_Return);
					}else {
						is.setReserveTimeZone(CommonCode.ReserveCode.Time_Zone_Pm_Return);
					}
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					String beforeVst = formatter.format(info.get(i).getRSRV_DT());
					String beforeNow = formatter.format(new Date());
					if(info.get(i).getCNCL_YN().equals("N")){
					try {
						Date	vstDtt = formatter.parse(beforeVst);
						Date	nowDtt = formatter.parse(beforeNow);
						
							if(vstDtt.after(nowDtt)){
								is.setReserveState(CommonCode.ReserveCode.Reserve_State_Start);
							}else if(vstDtt.before(nowDtt)){
								is.setReserveState(CommonCode.ReserveCode.Reserve_State_End);
							}else{
								is.setReserveState(CommonCode.ReserveCode.Reserve_State_Ing);
							}
					
					} catch (ParseException e) {
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
		} else {
			CommonResult k = new CommonResult(CommonMsg.failCodeNotFound,CommonMsg.failMsgNotFound);
			result.setResult(k);
		}
		
		return result;
	}

	@Override
	public CommonSingleResult<ReserveInfo> unregisteReserve(ReserveInfo rev,UserInfo sessUser) {
		// TODO Auto-generated method stub
		CommonSingleResult<ReserveInfo> result = new CommonSingleResult<ReserveInfo>();
		if (sessUser.getUserNo() == null) {
			CommonResult k = new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized);
			result.setResult(k);
			return result;
		}
		if(rev.getReserveDetailNo()==null ||rev.getReserveDetailNo().equals("") ){
			CommonResult k = new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput);
			result.setResult(k);
			return result;
		}
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("transaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);		
		TransactionStatus status = transManager.getTransaction(def);
		ParamsCommonFilter filter = new ParamsCommonFilter();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("RSRV_DTL_NO", rev.getReserveDetailNo());
		filter.setColumns(map);
		try{
			
			
				int date = revdDao.update(new SAFacilityReserveDate(),filter);
				if(date>0){				
						CommonResult k = new CommonResult(CommonMsg.successCode,CommonMsg.successMsg);
						result.setInfo(rev);					
						result.setResult(k);
						transManager.commit(status);
						return result;
						
				}else {
					transManager.rollback(status);
					CommonResult k = new CommonResult(CommonMsg.FailCodeReserveService.II_RV_DELETE,CommonMsg.FailMsgCRUDService.II_DELETE);				
					result.setResult(k);
					return result;
				}	
			
			
			
		}catch(Exception e){
			transManager.rollback(status);
			CommonResult k = new CommonResult(CommonMsg.FailCodeReserveService.II_RV_DELETE,CommonMsg.FailMsgCRUDService.II_DELETE);				
			result.setResult(k);			
			return result;
		}
	
		
	}

	@Override
	public CommonSingleResult<ReserveInfo> registeReserve(ReserveInfo rev,UserInfo sessUser,List<String> array) {
		// TODO Auto-generated method stub
		
		CommonSingleResult<ReserveInfo> result = new CommonSingleResult<ReserveInfo>();
		if (sessUser.getUserNo() == null) {
			CommonResult k = new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized);
			result.setResult(k);
			return result;
		}
		
		if(rev.getFacilityNo()==null ||rev.getFacilityNo().equals("") ||
				array.size()==0 ){
			CommonResult k = new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput);
			result.setResult(k);
			return result;
		}
		ArrayList<ReserveDetail> resultArray = new ArrayList<ReserveDetail>();
		for(int ii=0; ii<array.size(); ii++){
			String[] half = array.get(ii).split("/");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			ReserveDetail detailArray = new ReserveDetail();	
			Date dateNew;
			try {
				dateNew = formatter.parse(half[0]);
				detailArray.setReserveDate(dateNew);
				detailArray.setReserveTimeZone(Integer.parseInt(half[1]));
				resultArray.add(detailArray);								
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		rev.setReserveDetail(resultArray);
		SAFacilityReserve set  = new SAFacilityReserve();
		set.setFCLT_MGMT_NO(rev.getFacilityNo());
		set.setUSR_NO(sessUser.getUserNo());
		set.setRSRV_VSTN(rev.getVisitCount());
		set.setREG_ID(sessUser.getUserId());
		set.setUPD_ID(sessUser.getUserId());
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
					date.setREG_ID(sessUser.getUserId());
					date.setUPD_ID("WEB");
					date.setRSRV_DT(rev.getReserveDetail().get(i).getReserveDate());
					
					if(rev.getReserveDetail().get(i).getReserveTimeZone()==CommonCode.ReserveCode.Time_Zone_Am_Return){
						date.setTZ_TP_CD(CommonCode.ReserveCode.Time_Zone_Am);
					}else {
						date.setTZ_TP_CD(CommonCode.ReserveCode.Time_Zone_Pm);
					}
					
					SAFacilityReserveDate resultDate = revdDao.insert(date);
					
					if(resultDate!=null){
				
							CommonResult k = new CommonResult(CommonMsg.successCode,CommonMsg.successMsg);
							rev.setReserveNo(setResult.getRSRV_NO());
							rev.setUserNo(setResult.getUSR_NO());
							ReserveInfo infoResult = new ReserveInfo();
							infoResult.setUserNo(rev.getUserNo());
							infoResult.setReserveNo(rev.getReserveNo());
							result.setInfo(infoResult);					
							result.setResult(k);						
						
					}else {					
						CommonResult k = new CommonResult(CommonMsg.FailCodeReserveService.II_RV_REGISTER,CommonMsg.FailMsgCRUDService.II_REGISTER);				
						result.setResult(k);
						return result;
					}
							
				
				}
				transManager.commit(status);
				return result;
			
			}else {
				transManager.rollback(status);
				CommonResult k = new CommonResult(CommonMsg.FailCodeReserveService.II_RV_REGISTER,CommonMsg.FailMsgCRUDService.II_REGISTER);				
				result.setResult(k);
				return result;
			}
			
			
			
		}catch (Exception e){
			transManager.rollback(status);
			CommonResult k = new CommonResult(CommonMsg.FailCodeReserveService.II_RV_REGISTER,CommonMsg.FailMsgCRUDService.II_REGISTER);				
			result.setResult(k);
			return result;
		}
	
		

	}

	
	

}
