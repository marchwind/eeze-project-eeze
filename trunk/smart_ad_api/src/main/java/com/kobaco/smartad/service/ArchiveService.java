package com.kobaco.smartad.service;

import com.kobaco.smartad.model.service.ArchiveInfo;
import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.UploadedFile;

public interface ArchiveService {

	public CommonListResult<ArchiveInfo> getList(
			ArchiveInfo info, CommonPage cp);

	public CommonSingleResult<ArchiveInfo> getInfo(
			ArchiveInfo info);

	public CommonSingleResult<ArchiveInfo> add(
			PmcMnagerInfo mng, ArchiveInfo info);

	public CommonSingleResult<ArchiveInfo> update(
			PmcMnagerInfo mng, ArchiveInfo info);

	public CommonSingleResult<ArchiveInfo> delete(
			PmcMnagerInfo mng, ArchiveInfo info);
}
