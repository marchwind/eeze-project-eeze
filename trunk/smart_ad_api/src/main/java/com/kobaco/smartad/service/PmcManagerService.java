package com.kobaco.smartad.service;

import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.QnaInfo;

public interface PmcManagerService {

	public CommonSingleResult<PmcMnagerInfo> getPmcMangerList(PmcMnagerInfo info, PmcMnagerInfo sessUser);
	public CommonSingleResult<PmcMnagerInfo> login(PmcMnagerInfo info);
	public CommonSingleResult<PmcMnagerInfo> logout(PmcMnagerInfo sessUser);
	public CommonListResult<PmcMnagerInfo> getPmcMnagerList(PmcMnagerInfo info,CommonPage cp, PmcMnagerInfo sessUser);
	public CommonSingleResult<PmcMnagerInfo> getPmcMangerRegister(PmcMnagerInfo info, PmcMnagerInfo sessUser);
	public CommonSingleResult<PmcMnagerInfo> getPmcMangerSubscribe(PmcMnagerInfo info);
	public CommonSingleResult<PmcMnagerInfo> getPmcMangerUpdate(PmcMnagerInfo info, PmcMnagerInfo sessUser);
	public CommonSingleResult<PmcMnagerInfo> getPmcMangerModify(PmcMnagerInfo info, PmcMnagerInfo sessUser);
	public CommonSingleResult<PmcMnagerInfo> getPmcMangerDelete(PmcMnagerInfo info, PmcMnagerInfo sessUser);
	public CommonSingleResult<PmcMnagerInfo> getPmcMangerAck(PmcMnagerInfo info, PmcMnagerInfo sessUser);
	public CommonSingleResult<PmcMnagerInfo> getPmcMangerReset(PmcMnagerInfo info, PmcMnagerInfo sessUser);
}
