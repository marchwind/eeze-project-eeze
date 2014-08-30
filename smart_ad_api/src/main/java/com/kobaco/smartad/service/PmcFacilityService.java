package com.kobaco.smartad.service;

import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.FacilityInfo;
import com.kobaco.smartad.model.service.PmcMnagerInfo;

public interface PmcFacilityService {

	CommonListResult<FacilityInfo> getFacList(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, FacilityInfo facilityInfo);

	CommonSingleResult<FacilityInfo> getFac(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, FacilityInfo facilityInfo);

	CommonListResult<FacilityInfo> getFacUsedHistory(PmcMnagerInfo info,
			CommonPage cp, PmcMnagerInfo sessUser, FacilityInfo facilityInfo);

	CommonSingleResult<FacilityInfo> getUpdate(PmcMnagerInfo info,
			PmcMnagerInfo sessUser, FacilityInfo facilityInfo);

}
