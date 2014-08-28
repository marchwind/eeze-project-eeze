package com.kobaco.smartad.service;

import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.QnaInfo;

public interface PmcQnaService {

	CommonSingleResult<com.kobaco.smartad.model.service.QnaInfo> getPmcAnswer(
			PmcMnagerInfo info, PmcMnagerInfo sessUser, QnaInfo qnaInfo);

	CommonListResult<QnaInfo> getPmcList(PmcMnagerInfo info, CommonPage cp,
			PmcMnagerInfo sessUser);

	CommonSingleResult<com.kobaco.smartad.model.service.QnaInfo> getPmcQna(
			PmcMnagerInfo info, PmcMnagerInfo sessUser, QnaInfo qnaInfo);

}
