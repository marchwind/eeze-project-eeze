package com.kobaco.smartad.service;

import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.NotificationInfo;
import com.kobaco.smartad.model.service.PmcMnagerInfo;

public interface PmcNotificationService {

	CommonListResult<NotificationInfo> getPmcNotificationList(
			PmcMnagerInfo info, CommonPage cp, PmcMnagerInfo sessUser);

	CommonSingleResult<NotificationInfo> getPmcNotification(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, NotificationInfo notiInfo);

	CommonSingleResult<NotificationInfo> getPmcNotificationAdd(
			PmcMnagerInfo info, PmcMnagerInfo sessUser,
			NotificationInfo notiInfo);

	CommonSingleResult<NotificationInfo> getPmcNotificationUpdate(
			PmcMnagerInfo info, PmcMnagerInfo sessUser,
			NotificationInfo notiInfo);

	CommonSingleResult<NotificationInfo> getPmcNotificationDelete(
			PmcMnagerInfo info, PmcMnagerInfo sessUser,
			NotificationInfo notiInfo);

}
