package com.kobaco.smartad.service;

import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.PmcSessionInfo;

public interface PmcManagerService {

	public CommonSingleResult<PmcMnagerInfo> getPmcMangerList(PmcMnagerInfo info, PmcSessionInfo sessUser);
	public CommonSingleResult<PmcMnagerInfo> login(PmcMnagerInfo info);
	public CommonSingleResult<PmcMnagerInfo> logout(PmcSessionInfo sessUser);
	public CommonListResult<PmcMnagerInfo> getPmcMnagerList(PmcMnagerInfo info,CommonPage cp, PmcSessionInfo sessUser);
	public CommonSingleResult<PmcMnagerInfo> getPmcMangerRegister(PmcMnagerInfo info, PmcSessionInfo sessUser);
	public CommonSingleResult<PmcMnagerInfo> getPmcMangerSubscribe(PmcMnagerInfo info);
	public CommonSingleResult<PmcMnagerInfo> getPmcMangerUpdate(PmcMnagerInfo info, PmcSessionInfo sessUser);
	public CommonSingleResult<PmcMnagerInfo> getPmcMangerModify(PmcMnagerInfo info, PmcSessionInfo sessUser);
	public CommonSingleResult<PmcMnagerInfo> getPmcMangerDelete(PmcMnagerInfo info, PmcSessionInfo sessUser);
	public CommonSingleResult<PmcMnagerInfo> getPmcMangerAck(PmcMnagerInfo info, PmcSessionInfo sessUser);
	public CommonSingleResult<PmcMnagerInfo> getPmcMangerReset(PmcMnagerInfo info, PmcSessionInfo sessUser);
}
