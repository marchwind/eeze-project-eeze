package com.kobaco.smartad.service;

import java.util.List;

import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.ReserveInfo;
import com.kobaco.smartad.model.service.UserInfo;

public interface PmcReserveService {

	CommonSingleResult<ReserveInfo> getPmcReserveAdd(PmcMnagerInfo info,PmcMnagerInfo sessUser, UserInfo user, ReserveInfo rev,	List<String> k);
	CommonListResult<ReserveInfo> getPmcReserveList(PmcMnagerInfo info,CommonPage cp, PmcMnagerInfo sessUser, ReserveInfo rev);
	CommonSingleResult<ReserveInfo> getPmcReserveGet(PmcMnagerInfo info,PmcMnagerInfo sessUser, ReserveInfo rev);
	CommonSingleResult<ReserveInfo> getPmcReserveGetCheckIn(PmcMnagerInfo info,	PmcMnagerInfo sessUser, ReserveInfo rev);
	CommonSingleResult<ReserveInfo> setPmcReserveCheckIn(PmcMnagerInfo info,PmcMnagerInfo sessUser, ReserveInfo rev);
	CommonSingleResult<ReserveInfo> pmcReserveCancle(PmcMnagerInfo info,PmcMnagerInfo sessUser, ReserveInfo rev);
	CommonSingleResult<ReserveInfo> pmcReserveCheckOut(PmcMnagerInfo info,PmcMnagerInfo sessUser, ReserveInfo rev);
	

}
