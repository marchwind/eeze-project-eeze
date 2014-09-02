package com.kobaco.smartad.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kobaco.smartad.controller.PmcArchiveController;
import com.kobaco.smartad.dao.CommonDao;
import com.kobaco.smartad.model.data.ParamsCommonFilter;
import com.kobaco.smartad.model.data.ParamsCommonPage;
import com.kobaco.smartad.model.data.SAArchive;
import com.kobaco.smartad.model.service.ArchiveInfo;
import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.NotificationInfo;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.UploadedFile;
import com.kobaco.smartad.utils.CommonMsg;
import com.kobaco.smartad.utils.FileUtils;

@Service
public class ArchiveServiceImpl implements ArchiveService {

	@Autowired
	CommonDao<SAArchive> arcvDao;
	
	@Autowired
	@Qualifier("paths")
	private Properties paths;
	
	private static final Logger logger = LoggerFactory.getLogger(ArchiveServiceImpl.class);

	@Override
	public CommonListResult<ArchiveInfo> getList(CommonPage page) {		
		int totalCount = arcvDao.count(new SAArchive());
		//int totalPage  =  (int) Math.ceil((double)totalCount /(double) page.getUnitPerPage() );
		ParamsCommonPage pcp = new ParamsCommonPage();
		pcp.setCurrentPage(page.getCurrentPage());
		pcp.setUnitPerPage(page.getUnitPerPage());		
		pcp.setTotalCount(totalCount);
		//list.setTotalPage(totalPage);
		
		List<SAArchive> list = arcvDao.list(new SAArchive(), pcp);
		if(list.size()>0){
			List<ArchiveInfo> resultList = new ArrayList<ArchiveInfo>();
			for(SAArchive is : list){
				ArchiveInfo temp = new ArchiveInfo(is);		
				resultList.add(temp);
			}
			return new CommonListResult<ArchiveInfo>(new CommonResult(CommonMsg.successCode, CommonMsg.successMsg),
					resultList,
					pcp);
		}else{
			return new CommonListResult<ArchiveInfo>(new CommonResult(CommonMsg.failCodeNotFound, CommonMsg.failMsgNotFound),
					null,
					null);
		}
	}
	
	@Override
	public CommonSingleResult<ArchiveInfo> getInfo(ArchiveInfo info) {
		
		SAArchive sa = new SAArchive();
		sa.setARCV_NO(info.getArchiveNo());
				
		if((sa = arcvDao.info(sa)) != null){			
			return new CommonSingleResult<ArchiveInfo>(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg),
					new ArchiveInfo(sa));
			
		}else {
			return new CommonSingleResult<ArchiveInfo>(new CommonResult(CommonMsg.failCodeNotFound,CommonMsg.failMsgNotFound),
					null);
		}		
	
	}
	
	@Override
	public CommonSingleResult<ArchiveInfo> add(PmcMnagerInfo mng, ArchiveInfo info) {
		// TODO Auto-generated method stub
		CommonSingleResult<NotificationInfo> result = new CommonSingleResult<NotificationInfo>();	
		
		SAArchive sa =	new SAArchive();
		
		sa.setARCV_CNTT(info.getArchiveContent());
		sa.setARCV_SBJT(info.getArchiveSubject());
		sa.setREG_ID(mng.getManagerId());
		sa.setUPD_ID(mng.getManagerId());		
		sa = arcvDao.insert(sa);

		if(sa != null){
			if (FileUtils.isFile(info.getFile())) {
				try {
					sa.setATT_FL_PTH(FileUtils.fileCopy(info.getFile(), paths.getProperty("archive.file") + File.separator + sa.getARCV_NO() + "_" ));
					sa.setATT_FL_NM(info.getFile().getOriginalFilename());
					
					ParamsCommonFilter filter = new ParamsCommonFilter();
					filter.setColumns(new HashMap());
					filter.getColumns().put("ATT_FL_PATH", sa.getATT_FL_PTH());
					filter.getColumns().put("ATT_FL_NM",   sa.getATT_FL_NM());
					filter.getColumns().put("ARCV_NO",     sa.getARCV_NO());
					filter.addNamespace("File");
					
					arcvDao.update(new SAArchive(), filter);
					
					if(arcvDao.update(sa)>0){
						info.setFile(null);
						return new CommonSingleResult<ArchiveInfo>(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg),
								info);
					}else{
						return new CommonSingleResult<ArchiveInfo>(new CommonResult(CommonMsg.failCodeNoUpdateCount,CommonMsg.failMsgNoUpdateCount),
								null);
					}
				} catch (IOException e) {
					e.printStackTrace();
					return new CommonSingleResult<ArchiveInfo>(new CommonResult(CommonMsg.failCodeFileSystemErr, CommonMsg.failMsgFileSystemErr),
							null);
					
				}
			}
			return new CommonSingleResult<ArchiveInfo>(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg),
					new ArchiveInfo(sa));
		}else{
			result.setResult(new CommonResult(CommonMsg.FailCodeNotificationService.II_NT_REGISTER,
					CommonMsg.FailMsgCRUDService.II_REGISTER));
			return new CommonSingleResult<ArchiveInfo>(new CommonResult(CommonMsg.failCodeNoUpdateCount,CommonMsg.failMsgNoUpdateCount),
					null);
		}
	}
	
	@Override
	public CommonSingleResult<ArchiveInfo> update(PmcMnagerInfo mng, ArchiveInfo info) {
		SAArchive sa =	new SAArchive();
		sa.setARCV_NO(info.getArchiveNo());
		sa.setARCV_CNTT(info.getArchiveContent());
		sa.setARCV_SBJT(info.getArchiveSubject());
		if (FileUtils.isFile(info.getFile())) {
			try {
				sa.setATT_FL_PTH(FileUtils.fileCopy(info.getFile(), paths.getProperty("archive.file") + File.separator + sa.getARCV_NO() + "_" ));
				sa.setATT_FL_NM(info.getFile().getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
				return new CommonSingleResult<ArchiveInfo>(new CommonResult(CommonMsg.failCodeFileSystemErr, CommonMsg.failMsgFileSystemErr),
						null);
			}
		}
		sa.setUPD_ID(mng.getManagerId());
		
		if(arcvDao.update(sa)>0){
			return new CommonSingleResult<ArchiveInfo>(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg),
					info);
		}else{
			return new CommonSingleResult<ArchiveInfo>(new CommonResult(CommonMsg.failCodeNoUpdateCount,CommonMsg.failMsgNoUpdateCount),
					null);
		}
	}
	@Override
	public CommonSingleResult<ArchiveInfo> delete(PmcMnagerInfo mng, ArchiveInfo info) {

		SAArchive sa =	new SAArchive();
		sa.setARCV_NO(info.getArchiveNo());
		
		if(arcvDao.delete(sa)>0){
			return new CommonSingleResult<ArchiveInfo>(new CommonResult(CommonMsg.successCode,CommonMsg.successMsg),
					info);
		}else{
			return new CommonSingleResult<ArchiveInfo>(new CommonResult(CommonMsg.failCodeNoUpdateCount,CommonMsg.failMsgNoUpdateCount),
					null);
		}
	}
	
	

}
