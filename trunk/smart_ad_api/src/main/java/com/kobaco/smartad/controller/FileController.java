package com.kobaco.smartad.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kobaco.smartad.service.FaqService;
import com.kobaco.smartad.utils.FileUtils;


@Controller
@RequestMapping(value="/files")
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	public FaqService faqService;
	
	@Autowired
	@Qualifier("paths")
	private Properties paths;
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void read(
			@RequestParam (value="fileName", defaultValue="" ) String fileName,
			@RequestParam (value="filePath", defaultValue="" ) String filePath, 
			HttpServletResponse response) throws FileNotFoundException{
		
		if (fileName==null || filePath==null || fileName.trim().equals("") || filePath.trim().equals("")) {
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		File file = FileUtils.fileRead(paths.getProperty("archive.file"), filePath);
		
		if (!file.exists()) {
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		response.setContentType("application/force-download");
		response.setHeader("Content-Disposition", "attachment; filename="+fileName);
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setContentLength((int)file.length());
		
		InputStream in = new FileInputStream(file);
		try {
			FileCopyUtils.copy(in, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			try {
				if (in != null) {
					in.close();
				}
				response.getOutputStream().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
	
}
