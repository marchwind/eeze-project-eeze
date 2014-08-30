package com.kobaco.smartad.model.service;

import java.util.List;

import com.kobaco.smartad.model.ValueObject;

public class EquipStatisticsInfo implements ValueObject {

	private List<GValue> memoryUse;
	private List<GValue> cpuUse;
	private List<PValue> process;
	
	public class GValue {
		public String gTime;
		public String gValue;
		public GValue(String t, String v) {
			this.gTime = t;
			this.gValue = v;
		}
	}
	
	public class PValue {
		public String pName;
		public int cRate;
		public PValue(String p, int c) {
			this.pName = p;
			this.cRate = c;
		}
	}
	
	public EquipStatisticsInfo(){
		
	}

	public List<GValue> getMemoryUse() {
		return memoryUse;
	}

	public void setMemoryUse(List<GValue> memoryUse) {
		this.memoryUse = memoryUse;
	}

	public List<GValue> getCpuUse() {
		return cpuUse;
	}

	public void setCpuUse(List<GValue> cpuUse) {
		this.cpuUse = cpuUse;
	}

	public List<PValue> getProcess() {
		return process;
	}

	public void setProcess(List<PValue> process) {
		this.process = process;
	}
	
}
