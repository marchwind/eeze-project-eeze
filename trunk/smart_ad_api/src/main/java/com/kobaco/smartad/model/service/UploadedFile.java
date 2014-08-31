package com.kobaco.smartad.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kobaco.smartad.model.ValueObject;
import com.kobaco.smartad.model.data.SAEquipementStateLog;

public class UploadedFile implements ValueObject{
	
	private MultipartFile file;
	
	public UploadedFile() {
		;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
}
