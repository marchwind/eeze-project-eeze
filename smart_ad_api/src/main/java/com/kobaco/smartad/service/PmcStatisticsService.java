package com.kobaco.smartad.service;

import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.EquipStatisticsInfo;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.StatisticsInfo;

public interface PmcStatisticsService {

	CommonListResult<StatisticsInfo> getFacility(PmcMnagerInfo sessUser);

	CommonListResult<StatisticsInfo> getUser(PmcMnagerInfo sessUser);

	CommonListResult<StatisticsInfo> getReserve(PmcMnagerInfo sessUser);
	
	CommonSingleResult<EquipStatisticsInfo> getEquipStatus(String equipNo);

}
