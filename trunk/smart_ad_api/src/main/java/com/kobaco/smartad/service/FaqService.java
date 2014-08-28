package com.kobaco.smartad.service;



import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.FaqInfo;

public interface FaqService {	

	public CommonListResult<FaqInfo> getFaqList(CommonPage page);

	public CommonListResult<FaqInfo> insertFaq(FaqInfo faq);
}
