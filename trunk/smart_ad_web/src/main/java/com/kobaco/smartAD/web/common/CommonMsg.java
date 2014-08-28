package com.kobaco.smartAD.web.common;

import org.apache.commons.configuration.PropertiesConfiguration;

public final class CommonMsg {
	
	public static final String RESULT_CODE = "result_Code";
	public static final String RESULT_MSG = "result_Msg";

	private static PropertiesConfiguration propertiesConfiguration = null;
	
	public PropertiesConfiguration getPropertiesConfiguration() {
		return propertiesConfiguration;
	}
	public void setPropertiesConfiguration(
			PropertiesConfiguration propertiesConfiguration) {
		CommonMsg.propertiesConfiguration = propertiesConfiguration;
	}
	
	public static String getMessage(String key) {
		return propertiesConfiguration.getString(key);
	}
	public static int getNum(String key) {
		return propertiesConfiguration.getInt(key);
	}
}
