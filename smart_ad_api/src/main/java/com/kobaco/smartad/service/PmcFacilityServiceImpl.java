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
import com.kobaco.smartad.model.data.SAFacility;
import com.kobaco.smartad.model.data.SAFacilityMain;
import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.EquipInfo;
import com.kobaco.smartad.model.service.FacilityInfo;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.utils.CommonCode;
import com.kobaco.smartad.utils.CommonMsg;
@Service
public class PmcFacilityServiceImpl implements PmcFacilityService {
	
	@Autowired
	CommonDao<SAFacility> facDao;

	@Autowired
	CommonDao<SAFacilityMain> facMainDao;
	

	@Override
	public CommonSingleResult<FacilityInfo> getFac(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, FacilityInfo facilityInfo) {
		// TODO Auto-generated method stub
		CommonSingleResult<FacilityInfo> result = new CommonSingleResult<FacilityInfo>();
		if(!sessUser.isLogin()||info.getManagerMode()==null ||info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT)
			||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}
		if(facilityInfo==null ||facilityInfo.getFacilityNo()==null || facilityInfo.getFacilityNo().equals("") ){
			CommonResult k = new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput);
			result.setResult(k);
			return result;
		}
		ParamsCommonNamespace param = new ParamsCommonNamespace();
		Map<String,Object> m = new HashMap<String, Object>();
		m.put("FCLT_MGMT_NO", facilityInfo.getFacilityNo());
		ArrayList<EquipInfo> list = new ArrayList<EquipInfo>();
		param.setColumns(m, "Get");
		List<SAFacility> infoResult = facDao.list(new SAFacility(),param);
		if(infoResult.size()>0){
			FacilityInfo i = new FacilityInfo();
			i.setFacilityExplain(infoResult.get(0).getFCLT_EXPL());
			i.setFacilityName(infoResult.get(0).getFCLT_NM());
			i.setFacilityNo(infoResult.get(0).getFCLT_MGMT_NO());
			i.setFacilityState(infoResult.get(0).getFCLT_STTS_CD());
			for(SAFacility sa : infoResult){
				list.add(new EquipInfo(sa));
			}
			i.setList(list);
			result.setInfo(i);
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
		}else {
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound,
					CommonMsg.failMsgNotFound));
		}
		
		return result;
	}



	@Override
	public CommonListResult<FacilityInfo> getFacList(PmcMnagerInfo info,
			 PmcMnagerInfo sessUser, FacilityInfo facilityInfo) {
		// TODO Auto-generated method stub
		CommonListResult<FacilityInfo> result = new CommonListResult<FacilityInfo>();
		if(!sessUser.isLogin()||info.getManagerMode()==null ||info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT)
			||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}

		
		ParamsCommonNamespace param = new ParamsCommonNamespace();	
		param.setColumns(new HashMap<String, Object>(), "Main");
		
		List<SAFacilityMain> infoResult = facMainDao.list(new SAFacilityMain(),param);
		List<FacilityInfo> returnList = new ArrayList<FacilityInfo>();
		
//		SimpleDateFormat formatter = new SimpleDateFormat("mm");
//		SimpleDateFormat formatter1 = new SimpleDateFormat("HH");
//		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-mm-dd hh");
//		int now = Integer.parseInt(formatter.format(new Date()));
//		int beforeVst =0;
		
		String fcltMgmtNo = "";
		List<EquipInfo> eListResult = null;
		FacilityInfo fListResult    = null;
		
		if(infoResult.size()>0){
			for(int i=0; i<infoResult.size(); i++) {
				if(!fcltMgmtNo.equals(infoResult.get(i).getFCLT_MGMT_NO())) {
					if ( fListResult != null ) {
						fListResult.setList(eListResult);
						returnList.add(fListResult);
					}
					fcltMgmtNo = infoResult.get(i).getFCLT_MGMT_NO();
					eListResult = new ArrayList<EquipInfo>();
					fListResult = new FacilityInfo();
				}
						
				fListResult.setFacilityName(infoResult.get(i).getFCLT_NM());
				fListResult.setFacilityNo(infoResult.get(i).getFCLT_MGMT_NO());

				if(infoResult.get(i).getFCLT_CHKIN_CNT()>0) {
					fListResult.setFacilityCheckInYn(CommonCode.PmcManagerCode.PMC_YES);
				}else{
					fListResult.setFacilityCheckInYn(CommonCode.PmcManagerCode.PMC_NOT);
				}
					
				if( infoResult.get(i).getEQPM_NO()!=null &&
					!"".equals(infoResult.get(i).getEQPM_NO()) ) {  
					EquipInfo ei = new EquipInfo();
					ei.setEquipNo   (infoResult.get(i).getEQPM_NO());
					ei.setEquipName (infoResult.get(i).getEQPM_NM());
					ei.setEquipState(infoResult.get(i).getEQPM_STTS_CD());
					if(infoResult.get(i).getEQPM_ALV_CNT()>0){
						ei.setEquipWorkingYn(CommonCode.PmcManagerCode.PMC_YES);
					}else{
						ei.setEquipWorkingYn(CommonCode.PmcManagerCode.PMC_NOT);
					}
					eListResult.add(ei);
				}
				if ( fListResult != null ) {
					fListResult.setList(eListResult);
					returnList.add(fListResult);
				}
			}
			result.setList(returnList);
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
		} else {
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound,
					CommonMsg.failMsgNotFound));
		}
		return result;
	}

	@Override
	public CommonListResult<FacilityInfo> getFacUsedHistory(PmcMnagerInfo info,
			CommonPage cp, PmcMnagerInfo sessUser, FacilityInfo facilityInfo) {
		// TODO Auto-generated method stub
		CommonListResult<FacilityInfo> result = new CommonListResult<FacilityInfo>();
		if(!sessUser.isLogin()|| info.getManagerMode()==null || 
			info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT) ||info.getManagerMode().equals("")){	
			result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
			return result;
		}
		if(cp.getCurrentPage()<=0 ||cp.getUnitPerPage() <=0 ){	
			result.setResult(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput));
			return result;
		}
		
		ParamsCommonFilter filter = new ParamsCommonFilter();
		Map<String, Object> pp = new HashMap<String, Object>();		
		pp.put("FCLT_MGMT_NO", facilityInfo.getFacilityNo());
		filter.setColumns(pp);
		
		int totalCount = facDao.count(new SAFacility(),filter);
		int totalPage =   (int) Math.ceil((double)totalCount /(double) cp.getUnitPerPage() );		
		cp.setTotalCount(totalCount);
		cp.setTotalPage(totalPage);
		ParamsCommonPage p = new ParamsCommonPage();
		p.setCurrentPage(cp.getCurrentPage());
		p.setUnitPerPage(cp.getUnitPerPage());		
		p.setTotalCount(cp.getTotalCount());
		p.setTotalPage(cp.getTotalPage());
		
		ParamsCommonAggregator list = new ParamsCommonAggregator();
		list.filter(filter);
		list.page(p);
		List<FacilityInfo> eqResult = new ArrayList<FacilityInfo>();
		List<SAFacility> infoResult = facDao.list(new SAFacility(),list);	
		if(infoResult.size()>0){
			for(SAFacility dr : infoResult){
				eqResult.add(new FacilityInfo(dr));
			}
			result.setList(eqResult);
			result.setPage(p);
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
		}else {
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound,
					CommonMsg.failMsgNotFound));
		}
		
		return result;
	}



	@Override
	public CommonSingleResult<FacilityInfo> getUpdate(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, FacilityInfo facilityInfo) {
		// TODO Auto-generated method stub
		CommonSingleResult<FacilityInfo> result = new CommonSingleResult<FacilityInfo>();
		if(!sessUser.isLogin()||info.getManagerMode()==null ||info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT)
			||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}
		if(facilityInfo==null ||facilityInfo.getFacilityNo()==null || facilityInfo.getFacilityNo().equals("") ||
		   facilityInfo.getFacilityExplain()==null || facilityInfo.getFacilityExplain().equals("") ||
		   facilityInfo.getFacilityName()==null || facilityInfo.getFacilityName().equals("")||
		   facilityInfo.getFacilityState()==null || facilityInfo.getFacilityState().equals("")){
			CommonResult k = new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput);
			result.setResult(k);
			return result;
		}
		
		int infoResult = facDao.update(new SAFacility(facilityInfo,info));
		if(infoResult>0){			
			result.setInfo(facilityInfo);
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
		}else {
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound,
					CommonMsg.failMsgNotFound));
		}
		
		return result;
	}

}


