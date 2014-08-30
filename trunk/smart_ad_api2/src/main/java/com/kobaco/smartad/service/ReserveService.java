package com.kobaco.smartad.service;

import java.util.List;

import com.kobaco.smartad.model.data.ParamsCommonOrder;
import com.kobaco.smartad.model.data.ParamsCommonPage;
import com.kobaco.smartad.model.data.ParamsCommonFilter;
import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.FaqInfo;
import com.kobaco.smartad.model.service.QnaInfo;
import com.kobaco.smartad.model.service.ReserveInfo;
import com.kobaco.smartad.model.service.UserInfo;

public interface ReserveService {
	
	public CommonListResult<ReserveInfo> getReserveList(ReserveInfo revInfo);
	public CommonListResult<ReserveInfo> getUserReserveList(ReserveInfo rev, UserInfo sessUser,CommonPage cp);
	public CommonSingleResult<ReserveInfo> unregisteReserve(ReserveInfo rev,UserInfo sessUser);
	public CommonSingleResult<ReserveInfo> registeReserve(ReserveInfo rev,UserInfo sessUser,List<String> k);
}
