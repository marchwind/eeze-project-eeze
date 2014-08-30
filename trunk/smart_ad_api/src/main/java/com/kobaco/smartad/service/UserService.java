package com.kobaco.smartad.service;

import com.kobaco.smartad.model.data.ParamsCommonOrder;
import com.kobaco.smartad.model.data.ParamsCommonPage;
import com.kobaco.smartad.model.data.ParamsCommonFilter;
import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.QnaInfo;
import com.kobaco.smartad.model.service.UserEmailCert;
import com.kobaco.smartad.model.service.UserInfo;

public interface UserService {
	public CommonSingleResult<UserInfo> login(String userId, String userPassword);
	public CommonResult logout(String userNo);
	public CommonSingleResult<UserInfo> subscribe(UserInfo userInfo);
	public CommonSingleResult<UserInfo> unsubscribe(UserInfo userInfo);
	public CommonResult emailCert(String certKey);
	public CommonSingleResult<UserInfo> update(UserInfo userInfo);
	public CommonListResult<UserInfo> getList(ParamsCommonFilter test, ParamsCommonOrder order, ParamsCommonPage page);
	public CommonSingleResult<UserInfo> get(UserInfo userInfo);
	public CommonSingleResult<UserInfo>  idCheck (UserInfo userInfo);
	public CommonSingleResult<UserInfo> findUser(UserInfo user);
	public CommonSingleResult<UserInfo> findUserPassword(UserInfo user);
	public CommonSingleResult<UserInfo> updatePassword(UserInfo user, String newPw);
}
