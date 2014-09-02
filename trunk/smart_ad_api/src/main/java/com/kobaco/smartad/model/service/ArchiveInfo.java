package com.kobaco.smartad.model.service;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.kobaco.smartad.model.ValueObject;
import com.kobaco.smartad.model.data.SAArchive;

public class ArchiveInfo implements ValueObject {
	
	private String archiveNo;
	private String archiveSubject;
	private String archiveContent;
	private int viewCount;
	private String attachedFilePath;
	private String attachedFileName;
	private Date registeDate;
	private MultipartFile file;
	
	public ArchiveInfo(){
		
	}
	
	public ArchiveInfo(SAArchive arcv){
		this.archiveNo      = arcv.getARCV_NO();
		this.archiveSubject = arcv.getARCV_SBJT();
		this.archiveContent = arcv.getARCV_CNTT();
		this.viewCount      = arcv.getVW_CNT();
		this.attachedFilePath=arcv.getATT_FL_PTH();
		this.attachedFileName=arcv.getATT_FL_NM();
		this.registeDate     =arcv.getREG_DTT();
	}

	public String getArchiveNo() {
		return archiveNo;
	}

	public void setArchiveNo(String archiveNo) {
		this.archiveNo = archiveNo;
	}

	public String getArchiveSubject() {
		return archiveSubject;
	}

	public void setArchiveSubject(String archiveSubject) {
		this.archiveSubject = archiveSubject;
	}

	public String getArchiveContent() {
		return archiveContent;
	}

	public void setArchiveContent(String archiveContent) {
		this.archiveContent = archiveContent;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public String getAttachedFilePath() {
		return attachedFilePath;
	}

	public void setAttachedFilePath(String attachedFilePath) {
		this.attachedFilePath = attachedFilePath;
	}

	public String getAttachedFileName() {
		return attachedFileName;
	}

	public void setAttachedFileName(String attachedFileName) {
		this.attachedFileName = attachedFileName;
	}

	public Date getRegisteDate() {
		return registeDate;
	}

	public void setRegisteDate(Date registeDate) {
		this.registeDate = registeDate;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}	
}
