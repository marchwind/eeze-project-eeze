package com.kobaco.smartad.service;

import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.EquipCheckInfo;
import com.kobaco.smartad.model.service.EquipCheckRefData;
import com.kobaco.smartad.model.service.EquipInfo;
import com.kobaco.smartad.model.service.EquipLogInfo;
import com.kobaco.smartad.model.service.EquipUsedRefData;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.UserInfo;

public interface PmcEquipmentService {

	/**
	 * 장비추가
	 * @param info
	 * @param sessUser
	 * @param equipInfo
	 * @return
	 */
	CommonSingleResult<EquipInfo> equipAdd(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, EquipInfo equipInfo);

	/**
	 * 장비삭제
	 * @param info
	 * @param sessUser
	 * @param equipInfo
	 * @return
	 */
	CommonSingleResult<EquipInfo> equipDelete(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, EquipInfo equipInfo);

	/**
	 * 장비대여(스마트장비)
	 * @param info
	 * @param sessUser
	 * @param equipInfo
	 * @param userInfo
	 * @return
	 */
	CommonSingleResult<EquipInfo> equipRent(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, EquipInfo equipInfo, UserInfo userInfo);

	/**
	 * 장비대여 정보
	 * @param info
	 * @param sessUser
	 * @param equipInfo
	 * @return
	 */
	CommonSingleResult<EquipInfo> equipRentInfo(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, EquipInfo equipInfo);

	/**
	 * 장비반납(스마트장비)
	 * @param info
	 * @param sessUser
	 * @param equipInfo
	 * @return
	 */
	CommonSingleResult<EquipInfo> equipRentReturn(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, EquipInfo equipInfo);

	/**
	 * 장비대여 목록
	 * @param equipInfo
	 * @param cp
	 * @param sessUser
	 * @param info
	 * @return
	 */
	CommonListResult<EquipInfo> getRentList(EquipInfo equipInfo, CommonPage cp,
			PmcMnagerInfo sessUser, PmcMnagerInfo info);

	/**
	 * 장비목록
	 * @param equipInfo
	 * @param cp
	 * @param sessUser
	 * @param info
	 * @return
	 */
	CommonListResult<EquipInfo> getList(EquipInfo equipInfo, CommonPage cp,
			PmcMnagerInfo sessUser, PmcMnagerInfo info);

	/**
	 * 장비상세
	 * @param equipInfo
	 * @return
	 */
	CommonSingleResult<EquipInfo> getInfo(String equipNo);
	
	/**
	 * 장비상세
	 * @param equipInfo
	 * @return
	 */
	CommonSingleResult<EquipInfo> getInfoByTag(String tagId);
	
	/**
	 * 장비점검
	 * @param equipInfo
	 * @return
	 */
	CommonSingleResult<EquipCheckInfo> check(EquipCheckInfo checkInfo, PmcMnagerInfo manager);
	
	/**
	 * 장비수정
	 * @param equipInfo
	 * @return
	 */
	CommonSingleResult<EquipInfo> update(EquipInfo equipInfo, PmcMnagerInfo manager);

	/**
	 * 장비 파워상태 수정
	 * @param equipInfo
	 * @return
	 */
	CommonSingleResult<EquipInfo> updatePowerState(String equipNo, String powerStateCd, PmcMnagerInfo manager);
	
	/**
	 * 장비점검 이력
	 * @param equipInfo
	 * @param cp
	 * @return
	 */
	CommonListResult<EquipCheckRefData> getCheckHistory(String equipNo, CommonPage cp);
	
	/**
	 * 장비사용 이력(프로세스 이력)
	 * @param equipInfo
	 * @return
	 */
	CommonListResult<EquipUsedRefData> getUsedHistory(String equipNo, CommonPage cp);
	

	/**
	 * 장비 모니터링 에이전트 등록 (에이전트 연동)
	 * @param equipInfo
	 * @return
	 */
	CommonResult agentSubscribe(EquipInfo equipInfo);
	
	/**
	 * 장비 모니터링 로그 추가 (에이전트 연동)
	 * @param log
	 * @return
	 */
	CommonResult addLog(EquipLogInfo log);
}
