package com.kobaco.smartad.model.data;

import java.util.HashMap;
import java.util.Map;

import com.kobaco.smartad.model.Params;

public class ParamsForAgentSubscribe implements Params {

	private String equipeNo;
	private String ip;
	private String os;
	
	public ParamsForAgentSubscribe(String equipeNo, String ip, String os) {
		this.equipeNo = equipeNo;
		this.ip = ip;
		this.os = os;
	}
	
	@Override
	public String getNamespace() {
		return "AgentSubscribe";
	}

	@Override
	public Map<String, Object> getColumns() {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("EQPM_IP",this.ip);
		hm.put("EQPM_OS",this.os);
		hm.put("UPD_ID" ,"AGENT");
		hm.put("EQPM_NO",this.equipeNo);
		return  hm;
	}

	
}
