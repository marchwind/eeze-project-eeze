package com.terascope.amano.incheon.service;

import com.terascope.amano.incheon.dao.helper.DbHelper;
import com.terascope.amano.incheon.dao.helper.HttpHelper;
import com.terascope.amano.incheon.dao.helper.IvpFtpHelper;
import com.terascope.amano.incheon.dto.IBaseDto;

public class ResvCommand extends ACommand {


	public ResvCommand() {
		super();
	}
	
	public ResvCommand(String id) {
		super(id);
	}
	
	public ResvCommand(String id, IBaseDto dto) {
		super(id, dto);
	}
	
	@Override
	public CommandResult execute(DbHelper db, HttpHelper http, IvpFtpHelper ftp) {
		return callApi(http);
	}

}
