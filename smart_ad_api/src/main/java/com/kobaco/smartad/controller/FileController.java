package com.kobaco.smartad.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kobaco.smartad.service.FaqService;


@Controller
@RequestMapping(value="/files")
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	public FaqService faqService;
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(@RequestParam (value="id",defaultValue="" )String id){	
      
        return "faq/"+id+"Form";
   }
	
	@RequestMapping(value = "/download/{fileName}", method = RequestMethod.GET)
	public void read(
			@PathVariable("fileName") String fileName,
			@RequestParam (value="filePath",defaultValue="" ) String path, 
			HttpServletResponse response) throws FileNotFoundException{
		
		File file = new File(path);
		InputStream in = new FileInputStream(file);
		response.setContentType("application/force-download");
		response.setHeader("Content-Disposition", "attachment; filename="+fileName);
	}
	
}
