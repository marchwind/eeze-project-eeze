package com.kobaco.smartad.model.service;

import java.util.List;

import com.kobaco.smartad.model.ValueObject;
import com.kobaco.smartad.model.data.SAEquipementStateLog;

public class EquipLogInfo implements ValueObject{
	
	private String equipNo;
	private String equipLogType;
	private String equipLog;
	private String equipOs;
	private String equipIp;
	private String equipFreeMemory;
	private List<String> equipProcesses;
	
	public EquipLogInfo() {
		;
	}
	
	public EquipLogInfo(SAEquipementStateLog log) {
		this.equipNo = log.getEQPM_NO();
		this.equipLogType = log.getLOG_TP_CD();	
	}
	
	public String getEquipNo() {
		return equipNo;
	}

	public void setEquipNo(String equipNo) {
		this.equipNo = equipNo;
	}

	public String getEquipLogType() {
		return equipLogType;
	}

	public void setEquipLogType(String equipLogType) {
		this.equipLogType = equipLogType;
	}

	public String getEquipLog() {
		return equipLog;
	}

	public void setEquipLog(String equipLog) {
		this.equipLog = equipLog;
	}

	public String getEquipOs() {
		return equipOs;
	}

	public void setEquipOs(String equipOs) {
		this.equipOs = equipOs;
	}

	public String getEquipFreeMemory() {
		return equipFreeMemory;
	}

	public void setEquipFreeMemory(String equipFreeMemory) {
		this.equipFreeMemory = equipFreeMemory;
	}

	public List<String> getEquipProcesses() {
		return equipProcesses;
	}
	
	public String getEquipProcessesToString() {
		StringBuffer sb = new StringBuffer();
		for(String p : equipProcesses) {
			sb.append(p + ",");
		}
		return sb.toString();
	}
	
	public void setEquipProcesses(List<String> equipProcesses) {
		this.equipProcesses = equipProcesses;
	}

	public String getEquipIp() {
		return equipIp;
	}

	public void setEquipIp(String equipIp) {
		this.equipIp = equipIp;
	}
	
}
