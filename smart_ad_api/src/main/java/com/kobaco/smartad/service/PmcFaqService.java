package com.kobaco.smartad.service;

import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.FaqInfo;
import com.kobaco.smartad.model.service.PmcMnagerInfo;

public interface PmcFaqService {

	CommonListResult<FaqInfo> getPmcFaqList(PmcMnagerInfo info, CommonPage cp,
			PmcMnagerInfo sessUser);

	CommonSingleResult<FaqInfo> getPmcAdd(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, FaqInfo faqInfo);

	CommonSingleResult<FaqInfo> getPmcUpdate(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, FaqInfo faqInfo);

	CommonSingleResult<FaqInfo> getPmcDelete(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, FaqInfo faqInfo);

	
}
