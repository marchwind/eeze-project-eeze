package com.kobaco.smartad.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.kobaco.smartad.dao.CommonDao;
import com.kobaco.smartad.model.data.ParamsCommonAggregator;
import com.kobaco.smartad.model.data.ParamsCommonFilter;
import com.kobaco.smartad.model.data.ParamsCommonNamespace;
import com.kobaco.smartad.model.data.ParamsCommonPage;
import com.kobaco.smartad.model.data.ParamsForAgentSubscribe;
import com.kobaco.smartad.model.data.SADeviceRental;
import com.kobaco.smartad.model.data.SAEquipement;
import com.kobaco.smartad.model.data.SAEquipementCheck;
import com.kobaco.smartad.model.data.SAEquipementProcessHistory;
import com.kobaco.smartad.model.data.SAEquipementStateHistory;
import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.EquipCheckInfo;
import com.kobaco.smartad.model.service.EquipCheckRefData;
import com.kobaco.smartad.model.service.EquipInfo;
import com.kobaco.smartad.model.service.EquipLogInfo;
import com.kobaco.smartad.model.service.EquipUsedRefData;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.UserInfo;
import com.kobaco.smartad.utils.CommonCode;
import com.kobaco.smartad.utils.CommonMsg;

@Service
public class PmcEquipmentServiceImpl implements PmcEquipmentService {
	
	@Autowired
	CommonDao<SAEquipement> equDao;
	@Autowired
	CommonDao<SADeviceRental> renDao;
	@Autowired
	CommonDao<SAEquipementCheck> eqcDao;
	@Autowired
	private DataSourceTransactionManager transManager;
	
	@Autowired
	CommonDao<SAEquipementStateHistory> equStateDao; 
	
	@Autowired
	CommonDao<SAEquipementProcessHistory> equProcessDao; 
	
	private static final Logger logger = LoggerFactory.getLogger(PmcEquipmentServiceImpl.class);
	
	@Override
	public CommonSingleResult<EquipInfo> equipAdd(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, EquipInfo equipInfo) {
		CommonSingleResult<EquipInfo> result = new CommonSingleResult<EquipInfo>();
		if(!sessUser.isLogin()||info.getManagerMode()==null ||info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT)
			||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}
		if(equipInfo==null ){
			CommonResult k = new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput);
			result.setResult(k);
			return result;
		}
		
		SAEquipement eqResult = equDao.insert(new SAEquipement(equipInfo,info));
		
		if(eqResult!=null){
			result.setInfo(new EquipInfo(eqResult));					
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
			return result;
		}else {
			result.setResult(new CommonResult(CommonMsg.FailCodePmcEquipService.II_EQ_REGISTER,
					CommonMsg.FailMsgCRUDService.II_REGISTER));
			return result;
		}
	}

	@Override
	public CommonSingleResult<EquipInfo> equipDelete(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, EquipInfo equipInfo) {
		CommonSingleResult<EquipInfo> result = new CommonSingleResult<EquipInfo>();
		if(!sessUser.isLogin()||info.getManagerMode()==null ||info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT)
			||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}
		if(equipInfo==null ){
			CommonResult k = new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput);
			result.setResult(k);
			return result;
		}
		int eqResult = equDao.delete(new SAEquipement(equipInfo,info));
		
		if(eqResult==1){
			result.setInfo(equipInfo);					
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
			return result;
		}else {
			result.setResult(new CommonResult(CommonMsg.FailCodePmcEquipService.II_EQ_DELETE,
					CommonMsg.FailMsgCRUDService.II_DELETE));
			return result;
		}
	}

	@Override
	public CommonSingleResult<EquipInfo> equipRent(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, EquipInfo equipInfo, UserInfo user) {
		CommonSingleResult<EquipInfo> result = new CommonSingleResult<EquipInfo>();
		if(!sessUser.isLogin()||info.getManagerMode()==null ||info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT)
			||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}
		if(equipInfo==null ){
			CommonResult k = new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput);
			result.setResult(k);
			return result;
		}
		SADeviceRental renResult = renDao.insert(new SADeviceRental(equipInfo,sessUser,user));
		
		if(renResult!=null){
			result.setInfo(new EquipInfo(renResult));					
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
			return result;
		}else {
			result.setResult(new CommonResult(CommonMsg.FailCodePmcRentService.II_RN_REGISTER,
					CommonMsg.FailMsgCRUDService.II_REGISTER));
			return result;
		}
	}

	@Override
	public CommonSingleResult<EquipInfo> equipRentInfo(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, EquipInfo equipInfo) {
		CommonSingleResult<EquipInfo> result = new CommonSingleResult<EquipInfo>();
		if(!sessUser.isLogin()||info.getManagerMode()==null ||info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT)
			||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}
		if(equipInfo==null ){
			CommonResult k = new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput);
			result.setResult(k);
			return result;
		}
		ParamsCommonNamespace params = new ParamsCommonNamespace();
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("equipNo", equipInfo.getEquipNo());
		m.put("equipRentalNo",equipInfo.getEquipRentalNo());		
		params.setColumns(m, "Rent");
		
		SADeviceRental renResult = renDao.info(new SADeviceRental(), params);
		
		if(renResult!=null){
			result.setInfo(new EquipInfo(renResult));					
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
			return result;
		}else {
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound,
					CommonMsg.failMsgNotFound));
			return result;
		}
	}

	@Override
	public CommonSingleResult<EquipInfo> equipRentReturn(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, EquipInfo equipInfo) {
		
		CommonSingleResult<EquipInfo> result = new CommonSingleResult<EquipInfo>();
		if(!sessUser.isLogin()||info.getManagerMode()==null ||info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT)
			||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}
		if(equipInfo==null ){
			CommonResult k = new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput);
			result.setResult(k);
			return result;
		}
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("transaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);		
		TransactionStatus status = transManager.getTransaction(def);
		
		if( equipInfo.getCheckContent()!= null && !"".equals(equipInfo.getCheckContent())) {
			eqcDao.insert(new SAEquipementCheck(equipInfo,sessUser));
		}
		
		int returnUpdate = renDao.update(new SADeviceRental(equipInfo,sessUser,new UserInfo()));
		
		if(returnUpdate==1){
			result.setResult(new CommonResult(CommonMsg.successCode, CommonMsg.successMsg));
			result.setInfo(equipInfo);				
			transManager.commit(status);
			return result;
		}else {
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound, CommonMsg.failMsgNotFound));
			transManager.rollback(status);		
			return result;
		}
	}

	@Override
	public CommonListResult<EquipInfo> getRentList(EquipInfo equipInfo,
			CommonPage page, PmcMnagerInfo sessUser,PmcMnagerInfo info) {
		CommonListResult<EquipInfo> result = new CommonListResult<EquipInfo>();
		
		if(page.getCurrentPage()<=0 ||page.getUnitPerPage() <=0){
			CommonResult k = new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput);
			result.setResult(k);
			return result;
		}
		if(!sessUser.isLogin() ||info.getManagerMode()==null ||	info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT)
			||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}
		ParamsCommonFilter filter = new ParamsCommonFilter();
		Map<String, Object> pp = new HashMap<String, Object>();	
		pp.put("equipNo",equipInfo.getEquipNo());
		filter.setColumns(pp);
		
		int totalCount = renDao.count(new SADeviceRental(),filter);
		int totalPage =   (int) Math.ceil((double)totalCount /(double) page.getUnitPerPage() );		
		page.setTotalCount(totalCount);
		page.setTotalPage(totalPage);
		ParamsCommonPage p = new ParamsCommonPage();
		p.setCurrentPage(page.getCurrentPage());
		p.setUnitPerPage(page.getUnitPerPage());		
		
		ParamsCommonAggregator list = new ParamsCommonAggregator();
		list.filter(filter);
		list.page(p);
		List<EquipInfo> eqResult = new ArrayList<EquipInfo>();
		List<SADeviceRental> infoResult = renDao.list(new SADeviceRental(),list);	
		if(infoResult.size()>0){
			for(SADeviceRental dr : infoResult){
				eqResult.add(new EquipInfo(dr));
			}
			result.setList(eqResult);
			result.setPage(page);
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
		}else {
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound,
					CommonMsg.failMsgNotFound));
		}
		
		return result;
	}

	@Override
	public CommonListResult<EquipInfo> getList(EquipInfo equipInfo,
			CommonPage page, PmcMnagerInfo sessUser, PmcMnagerInfo info) {
		CommonListResult<EquipInfo> result = new CommonListResult<EquipInfo>();
		
		if(page.getCurrentPage()<=0 ||page.getUnitPerPage() <=0){
			CommonResult k = new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput);
			result.setResult(k);
			return result;
		}
		if(!sessUser.isLogin() ||info.getManagerMode()==null ||	info.getManagerMode().equals(CommonCode.PmcManagerCode.PMC_NOT)
			||info.getManagerMode().equals("")){	
				result.setResult(new CommonResult(CommonMsg.failCodeUnAuthrized,CommonMsg.failMsgUnAuthrized));
				return result;
		}
		
		ParamsCommonFilter filter = new ParamsCommonFilter();
		Map<String, Object> pp = new HashMap<String, Object>();
		pp.put("grpTp", equipInfo.getEquipType());
		pp.put("FCLT_MGMT_NO", equipInfo.getFacilityNo());
		pp.put("EQPM_STTS_CD", equipInfo.getEquipState());
		
		filter.setColumns(pp);
		
		int totalCount = equDao.count(new SAEquipement(),filter);
		int totalPage =   (int) Math.ceil((double)totalCount /(double) page.getUnitPerPage() );		
		page.setTotalCount(totalCount);
		page.setTotalPage(totalPage);
		ParamsCommonPage p = new ParamsCommonPage();
		p.setCurrentPage(page.getCurrentPage());
		p.setUnitPerPage(page.getUnitPerPage());		
		
		ParamsCommonAggregator list = new ParamsCommonAggregator();
		list.filter(filter);
		list.page(p);
		List<EquipInfo> eqResult = new ArrayList<EquipInfo>();
		List<SAEquipement> infoResult = equDao.list(new SAEquipement(),list);	
		
		if(infoResult.size()>0){
			for(SAEquipement dr : infoResult){
				eqResult.add(new EquipInfo(dr));
			}
			result.setList(eqResult);
			result.setPage(page);
			result.setResult(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg));
		}else {
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound,
					CommonMsg.failMsgNotFound));
		}
		
		return result;
	}
	
	@Override
	public CommonResult agentSubscribe(EquipInfo eq) {
		equDao.update(new SAEquipement(), 
				new ParamsForAgentSubscribe( eq.getEquipNo(), eq.getEquipIp(), eq.getEquipOs()));
		return new CommonResult(CommonMsg.successCode, CommonMsg.successMsg);
	}

	@Override
	public CommonResult addLog(EquipLogInfo log) {
		SAEquipementStateHistory saSh = new SAEquipementStateHistory();
		SAEquipementProcessHistory saPh = new SAEquipementProcessHistory();
		
		saSh.setEQPM_NO(log.getEquipNo());
		saSh.setMEM(log.getEquipFreeMemory());
		saSh.setCPU(log.getEquipCpuLoad());
		saSh.setGTH_DTT(new Date());
		saSh.setEQPM_CNST_NO("000000000000");
		saSh = equStateDao.insert(saSh); 
		
		saPh.setEQPM_NO(log.getEquipNo());
		saPh.setHIST_CNTT(log.getEquipProcessesToString());
		saPh.setGTH_DTT(new Date());
		saPh.setSW_MGMT_NO("000000000000");
		saPh = equProcessDao.insert(saPh);
		
		if (saPh==null || saSh==null) {
			return new CommonResult(CommonMsg.failCodeNoUpdateCount, CommonMsg.failMsgNoUpdateCount);	
		}
		return new CommonResult(CommonMsg.successCode, CommonMsg.successMsg);
	}

	@Override
	public CommonSingleResult<EquipInfo> getInfo(String equipNo) {
		
		SAEquipement saEq = new SAEquipement();
		saEq.setEQPM_NO(equipNo);
		saEq = equDao.info(saEq);
		
		if (saEq == null) {
			return new CommonSingleResult<EquipInfo> (new CommonResult(CommonMsg.failCodeNotFound, CommonMsg.failMsgNotFound), 
					null);
		} else {
			return new CommonSingleResult<EquipInfo> (new CommonResult(CommonMsg.successCode, CommonMsg.successMsg),
					new EquipInfo(saEq));
		}
	}

	@Override
	public CommonSingleResult<EquipCheckInfo> check(EquipCheckInfo checkInfo, PmcMnagerInfo manager) {
		
		SAEquipementCheck check = eqcDao.insert(new SAEquipementCheck(checkInfo, manager));
		if (check == null) {
			return new CommonSingleResult<EquipCheckInfo> (new CommonResult(CommonMsg.failCodeUnknown, CommonMsg.failMsgUnknown), 
					null);
		} else {
			return new CommonSingleResult<EquipCheckInfo> (new CommonResult(CommonMsg.successCode, CommonMsg.successMsg),
					checkInfo);
		}
	}

	@Override
	public CommonSingleResult<EquipInfo> update(EquipInfo equipInfo, PmcMnagerInfo manager) {
		if(equipInfo == null ) {
			return new CommonSingleResult<EquipInfo> (new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput),
					null);
		}
		if(equDao.update(new SAEquipement(equipInfo, manager)) > 0){
			return new CommonSingleResult<EquipInfo> (new CommonResult(CommonMsg.successCode, CommonMsg.successMsg), 
					equipInfo);
		}else {
			return new CommonSingleResult<EquipInfo> (new CommonResult(CommonMsg.failCodeUnknown, CommonMsg.failMsgUnknown), 
					null);
		}
	}

	@Override
	public CommonListResult<EquipCheckRefData> getCheckHistory(String equipNo, CommonPage page) {
		
		if(page.getCurrentPage()<=0 ||page.getUnitPerPage() <=0){
			return new CommonListResult<EquipCheckRefData>(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput),
					null,
					null);
		}
		
		ParamsCommonFilter filter = new ParamsCommonFilter();
		ParamsCommonPage ppage = new ParamsCommonPage();
		
		Map<String, Object> cols = new HashMap<String, Object>();
		cols.put("EQPM_NO", equipNo);
		filter.addNamespace("Equip");
		filter.setColumns(cols);
		
		ppage.setCurrentPage(page.getCurrentPage());
		ppage.setUnitPerPage(page.getUnitPerPage());
		
		ParamsCommonAggregator agg = new ParamsCommonAggregator().filter(filter).page(ppage);
		
		int tc = eqcDao.count(new SAEquipementCheck(), agg);
		ppage.setTotalCount(tc);
		List<SAEquipementCheck> list = eqcDao.list(new SAEquipementCheck(), agg);
		
		
		if (list.size() == 0) {
			return new CommonListResult<EquipCheckRefData>(new CommonResult(CommonMsg.failCodeNotFound,CommonMsg.failMsgNotFound),
					null,
					null);
		}
		
		List<EquipCheckRefData> outList = new ArrayList<EquipCheckRefData>();
		
		for(SAEquipementCheck sa : list) {
			outList.add(new EquipCheckRefData(sa));
		}
		
		return new CommonListResult<EquipCheckRefData>(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg),
				outList,
				ppage);
	}

	@Override
	public CommonListResult<EquipUsedRefData> getUsedHistory(String equipNo, CommonPage page) {
		if(page.getCurrentPage()<=0 ||page.getUnitPerPage() <=0){
			return new CommonListResult<EquipUsedRefData>(new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput),
					null,
					null);
		}
		
		ParamsCommonFilter filter = new ParamsCommonFilter();
		ParamsCommonPage ppage = new ParamsCommonPage();
		
		Map<String, Object> cols = new HashMap<String, Object>();
		cols.put("EQPM_NO", equipNo);
		filter.addNamespace("Equip");
		filter.setColumns(cols);
		
		ppage.setCurrentPage(page.getCurrentPage());
		ppage.setUnitPerPage(page.getUnitPerPage());
		
		ParamsCommonAggregator agg = new ParamsCommonAggregator().filter(filter).page(ppage);
		
		int tc = equProcessDao.count(new SAEquipementProcessHistory(), agg);
		ppage.setTotalCount(tc);
		List<SAEquipementProcessHistory> list = equProcessDao.list(new SAEquipementProcessHistory(), agg);
		
		
		if (list.size() == 0) {
			return new CommonListResult<EquipUsedRefData>(new CommonResult(CommonMsg.failCodeNotFound,CommonMsg.failMsgNotFound),
					null,
					null);
		}
		
		List<EquipUsedRefData> outList = new ArrayList<EquipUsedRefData>();
		
		for(SAEquipementProcessHistory sa : list) {
			outList.add(new EquipUsedRefData(sa));
		}
		
		return new CommonListResult<EquipUsedRefData>(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg),
				outList,
				ppage);
	}

	@Override
	public CommonSingleResult<EquipInfo> getInfoByTag(String tagId) {
		SAEquipement saEq = new SAEquipement();
		saEq.setEQPM_NO("");
		saEq.setNFC_TAG_ID(tagId);
		saEq = equDao.info(saEq);
		
		if (saEq == null) {
			return new CommonSingleResult<EquipInfo> (new CommonResult(CommonMsg.failCodeNotFound, CommonMsg.failMsgNotFound), 
					null);
		} else {
			return new CommonSingleResult<EquipInfo> (new CommonResult(CommonMsg.successCode, CommonMsg.successMsg),
					new EquipInfo(saEq));
		}
	}

	@Override
	public CommonSingleResult<EquipInfo> updatePowerState(String equipNo,
			String powerStateCd, PmcMnagerInfo manager) {
		if( equipNo == null || "".equals(equipNo) ||
			powerStateCd == null || "".equals(powerStateCd) ) {
			return new CommonSingleResult<EquipInfo> (new CommonResult(CommonMsg.failCodeInvalidInput,CommonMsg.failMsgeInvalidInput),
					null);
		}
		
		ParamsCommonFilter filter = new ParamsCommonFilter();
		
		Map<String, Object> cols = new HashMap<String, Object>();
		cols.put("EQPM_NO", equipNo);
		cols.put("EQPM_PWR_STTS_CD", powerStateCd);
		cols.put("UPD_ID", manager.getManagerId());
		filter.setColumns(cols);
		filter.addNamespace("Power");

		if(equDao.update(new SAEquipement(), filter) > 0){
			return new CommonSingleResult<EquipInfo> (new CommonResult(CommonMsg.successCode, CommonMsg.successMsg), 
				   new EquipInfo(equipNo));
		}else {
			return new CommonSingleResult<EquipInfo> (new CommonResult(CommonMsg.failCodeNotFound, CommonMsg.failMsgNotFound), 
				   null);
		}
	}
}
