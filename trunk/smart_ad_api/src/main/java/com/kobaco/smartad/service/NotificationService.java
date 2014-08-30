package com.kobaco.smartad.service;


import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.NotificationInfo;
import com.kobaco.smartad.model.service.UserInfo;

public interface NotificationService {	

	public CommonListResult<NotificationInfo> getNotificationList(NotificationInfo notiInfo, CommonPage page);
	public CommonSingleResult<NotificationInfo> getNoti(NotificationInfo notiInfo,UserInfo sessUser);
	
}
