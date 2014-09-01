package com.kobaco.smartad.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kobaco.smartad.dao.CommonDao;
import com.kobaco.smartad.model.data.ParamsCommonFilter;
import com.kobaco.smartad.model.data.SAStatistics;
import com.kobaco.smartad.model.data.SAStatisticsEQS;
import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.EquipStatisticsInfo;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.StatisticsInfo;
import com.kobaco.smartad.utils.CommonMsg;

@Service
public class PmcStatisticsServiceImpl implements PmcStatisticsService {

	@Autowired
	CommonDao<SAStatistics> stttsDao;

	@Autowired
	CommonDao<SAStatisticsEQS> stttsEqsDao;
	
	@Override
	public CommonListResult<StatisticsInfo> getFacility(PmcMnagerInfo sessUser) {
		ParamsCommonFilter filter = new ParamsCommonFilter();
		filter.setColumns(new HashMap<String, Object>());
		filter.addNamespace("Facility");
		
		List<SAStatistics> list = stttsDao.list(new SAStatistics(), filter);
		
		CommonListResult<StatisticsInfo> result = new CommonListResult<StatisticsInfo>();
		if (list.size()>0) {
			
			List<StatisticsInfo> outList = new ArrayList<StatisticsInfo>();
			for(SAStatistics sa:list) {
				outList.add(new StatisticsInfo(sa));
			}
			result.setList(outList);
			result.setResult(new CommonResult(CommonMsg.successCode, CommonMsg.successMsg));
		} else {
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound, CommonMsg.failMsgNotFound));
		}
		
		return result;
	}


	@Override
	public CommonListResult<StatisticsInfo> getUser(PmcMnagerInfo sessUser) {
		ParamsCommonFilter filter = new ParamsCommonFilter();
		filter.setColumns(new HashMap<String, Object>());
		filter.addNamespace("User");
		
		List<SAStatistics> list = stttsDao.list(new SAStatistics(), filter);
		
		CommonListResult<StatisticsInfo> result = new CommonListResult<StatisticsInfo>();
		if (list.size()>0) {
			
			List<StatisticsInfo> outList = new ArrayList<StatisticsInfo>();
			for(SAStatistics sa:list) {
				outList.add(new StatisticsInfo(sa));
			}
			result.setList(outList);
			result.setResult(new CommonResult(CommonMsg.successCode, CommonMsg.successMsg));
		} else {
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound, CommonMsg.failMsgNotFound));
		}
		
		return result;
	}


	@Override
	public CommonListResult<StatisticsInfo> getReserve(PmcMnagerInfo sessUser) {
		ParamsCommonFilter filter = new ParamsCommonFilter();
		filter.setColumns(new HashMap<String, Object>());
		filter.addNamespace("Reserve");
		
		List<SAStatistics> list = stttsDao.list(new SAStatistics(), filter);
		
		CommonListResult<StatisticsInfo> result = new CommonListResult<StatisticsInfo>();
		if (list.size()>0) {
			
			List<StatisticsInfo> outList = new ArrayList<StatisticsInfo>();
			for(SAStatistics sa:list) {
				outList.add(new StatisticsInfo(sa));
			}
			result.setList(outList);
			result.setResult(new CommonResult(CommonMsg.successCode, CommonMsg.successMsg));
		} else {
			result.setResult(new CommonResult(CommonMsg.failCodeNotFound, CommonMsg.failMsgNotFound));
		}
		
		return result;
	}


	@Override
	public CommonSingleResult<EquipStatisticsInfo> getEquipStatus(String equipNo) {
		
		ParamsCommonFilter filter = new ParamsCommonFilter();
		filter.setColumns(new HashMap());
		filter.getColumns().put("EQPM_NO", equipNo);
		filter.addNamespace("EQStatus");
		List<SAStatisticsEQS> listOfEqs = stttsEqsDao.list(new SAStatisticsEQS(), filter);
		
		
		EquipStatisticsInfo info = new EquipStatisticsInfo();
		List<EquipStatisticsInfo.GValue> cpuUse = new ArrayList<EquipStatisticsInfo.GValue>();
		List<EquipStatisticsInfo.GValue> memUse = new ArrayList<EquipStatisticsInfo.GValue>();
		for(SAStatisticsEQS eqs : listOfEqs) {
			
			cpuUse.add(info.new GValue(eqs.getGTH_TM(), eqs.getCPU()));
			memUse.add(info.new GValue(eqs.getGTH_TM(), eqs.getMEM()));
		}
		
		filter = new ParamsCommonFilter();
		filter.setColumns(new HashMap());
		filter.getColumns().put("EQPM_NO", equipNo);
		filter.addNamespace("EQProcess");
		//List<SAStatisticsEQS> listOfEqp = stttsEqsDao.list(new SAStatisticsEQS(), filter);
		
		List<EquipStatisticsInfo.PValue> process = new ArrayList<EquipStatisticsInfo.PValue>();
		process.add(info.new PValue("Crome", 6));
		process.add(info.new PValue("Inter Explorer", 1));
		process.add(info.new PValue("Microsoft Excel", 1));
		process.add(info.new PValue("Microsoft Word", 2));
		process.add(info.new PValue("Adobe", 3));
		
		info.setCpuUse(cpuUse);
		info.setMemoryUse(memUse);
		info.setProcess(process);
		CommonSingleResult<EquipStatisticsInfo> result = 
				new CommonSingleResult<EquipStatisticsInfo> (
						new CommonResult(CommonMsg.successCode, CommonMsg.successMsg),
						info);
		return result;
	}
}
