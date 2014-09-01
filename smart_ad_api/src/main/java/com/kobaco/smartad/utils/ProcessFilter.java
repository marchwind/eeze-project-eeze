package com.kobaco.smartad.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ProcessFilter {

	private static List<String> pList = new ArrayList();
	private static String pString = "";
	static {
		pList.add("Finder");
		pList.add("Safari");
		pList.add("Adobe Photoshop CC 2014");
		pList.add("Google Chrome");
		pList.add("Final Cut Pro");
		pList.add("Keynote");
		pList.add("iTunes");
		pList.add("Pages");
		pList.add("Numbers");
		pList.add("Terminal");
		pList.add("Pro Tools");
		pList.add("sshd");
		pList.add("node");
		pList.add("java");
	}
	
	public static Map<String, Integer> filter(String processes) {
		
		Map<String, Integer> pCountMap = new HashMap<String, Integer>();
		
		for(String p: pList) { 
			int cnt = StringUtils.countMatches(processes, p);
			if (cnt>0) {
				pCountMap.put(p,cnt);
			}
		}
//		System.out.println(pString);
//		Pattern pPattern = Pattern.compile(pString);
//		Matcher m = pPattern.matcher(processes);
//		while(m.find()) {
//			for(int i=1; i<=m.groupCount(); i++) {
//				System.out.println(m.group(i));
//			}
//		}
		return pCountMap;
	}
}
