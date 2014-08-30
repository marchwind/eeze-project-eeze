package com.kobaco.smartad.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.kobaco.smartad.model.data.SAQna;
import com.kobaco.smartad.model.data.SAStatistics;
import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.MailSend;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.QnaInfo;
import com.kobaco.smartad.model.service.StatisticsInfo;
import com.kobaco.smartad.utils.CommonCode;
import com.kobaco.smartad.utils.CommonMsg;

@Service
public class PmcStatisticsServiceImpl implements PmcStatisticsService {

	@Autowired
	CommonDao<SAStatistics> stttsDao;

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
}
