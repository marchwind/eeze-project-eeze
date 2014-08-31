package com.kobaco.smartad.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

	
	
	public static boolean isFile(MultipartFile srcFile) {
		return (srcFile!=null && !srcFile.isEmpty());
	}
	
	public static String fileCopy(MultipartFile srcFile, String destPath) throws IOException {
		if (srcFile!=null && !srcFile.isEmpty()) {
			String fileName = srcFile.getOriginalFilename();
			File destFile = new File(destPath + fileName);
			if(!destFile.exists()) destFile.createNewFile();
			
			InputStream in = srcFile.getInputStream();
			OutputStream out = new FileOutputStream(destFile);
			
			byte[] buf = new byte[1024];
			int read = 0;
			
			while((read=in.read(buf))!=-1) {
				out.write(buf, 0, read);
			}
			
			return destPath + fileName;
		}
		
		return null;
	}
}
