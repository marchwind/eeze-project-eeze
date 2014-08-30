package com.kobaco.smartad.service;

import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.ReserveInfo;
import com.kobaco.smartad.model.service.UserInfo;

public interface PmcUserService {

	CommonListResult<UserInfo> getPmcUserSearch(PmcMnagerInfo info,	CommonPage cp, PmcMnagerInfo sessUser, UserInfo user);

	CommonSingleResult<UserInfo> getPmcUserGet(PmcMnagerInfo info,PmcMnagerInfo sessUser, UserInfo user);

	CommonListResult<ReserveInfo> getPmcReserveList(PmcMnagerInfo info,CommonPage cp, PmcMnagerInfo sessUser, UserInfo user);

	CommonSingleResult<UserInfo> getPmcUserUpdate(PmcMnagerInfo info,PmcMnagerInfo sessUser, UserInfo user);

	CommonSingleResult<UserInfo> getPmcUserAdd(PmcMnagerInfo info,PmcMnagerInfo sessUser, UserInfo user);

	CommonSingleResult<UserInfo> getPmcUserUpdateStatus(PmcMnagerInfo info,PmcMnagerInfo sessUser, UserInfo user);

	

}
