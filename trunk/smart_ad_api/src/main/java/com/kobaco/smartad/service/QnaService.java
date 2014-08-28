package com.kobaco.smartad.service;

import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.QnaInfo;
import com.kobaco.smartad.model.service.UserInfo;

public interface QnaService {
	public CommonSingleResult<QnaInfo> getQna(QnaInfo qnaInfo);
	public CommonSingleResult<QnaInfo> registeQna(QnaInfo qnaInfo);
	public CommonSingleResult<QnaInfo> unregisteQna(QnaInfo qnaInfo);
	public CommonSingleResult<QnaInfo> updateQna(QnaInfo qnaInfo);
	public CommonListResult<QnaInfo> getQnaList(CommonPage page,QnaInfo qnaInfo, UserInfo sessUser);

}
