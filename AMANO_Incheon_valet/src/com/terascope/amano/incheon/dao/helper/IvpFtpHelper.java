package com.terascope.amano.incheon.dao.helper;

import java.text.SimpleDateFormat;
import java.util.Date;


public class IvpFtpHelper extends FtpHelper {
	private SimpleDateFormat format = new SimpleDateFormat("yyyyMM");

	public boolean ftpImageUpload(String srcFilePath, String desFileName, String deviceNo ) {
		String destPath = "/Image/M"+deviceNo+"/";
		String dd = format.format(new Date());
		if (!super.ftpChangeDirectory(destPath+dd)) {
			super.ftpMakeDirectory(destPath);
			super.ftpMakeDirectory(destPath+dd);
		}
		return super.ftpUpload(srcFilePath, desFileName, destPath+dd);
	}
	
	public boolean ftpSignUpload(String srcFilePath, String desFileName, String deviceNo ) {
		//return super.ftpUpload(srcFilePath, desFileName, "Sign/M"+deviceNo+"/"+format.format(new Date()));
		//String destPath = "/Sign/M"+deviceNo+"/";
		String dd = format.format(new Date());
		String destPath = "/Sign/"+dd+"/";
		if (!super.ftpChangeDirectory(destPath)) {
			super.ftpMakeDirectory(destPath);
		}
		return super.ftpUpload(srcFilePath, desFileName, destPath);
		
	}
	
	public boolean ftpDamageUpload(String srcFilePath, String desFileName, String deviceNo ) {
		//return super.ftpUpload(srcFilePath, desFileName, "Sign/M"+deviceNo+"/"+format.format(new Date()));
		String dd = format.format(new Date());
		String destPath = "/Damg/"+dd+"/";
		if (!super.ftpChangeDirectory(destPath)) {
			super.ftpMakeDirectory(destPath);
		}
		return super.ftpUpload(srcFilePath, desFileName, destPath);
		
	}
	
	public boolean ftpVideoUpload(String srcFilePath, String desFileName, String deviceNo ) {
		String destPath = "/Video/M"+deviceNo+"/";
		String dd = format.format(new Date());
		if (!super.ftpChangeDirectory(destPath+dd)) {
			super.ftpMakeDirectory(destPath);
			super.ftpMakeDirectory(destPath+dd);
		}
		return super.ftpUpload(srcFilePath, desFileName, destPath+dd);
	}
}

