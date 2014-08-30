package com.kobaco.smartad.utils;

public class CommonCode {	
	
	public class ReserveCode {
		public static final String Time_Zone_Am = "R01001"; 
		public static final String Time_Zone_Pm = "R01002"; 
		public static final int Time_Zone_Am_Return = 1; 
		public static final int Time_Zone_Pm_Return = 2; 
		public static final String Reserve_State_Cancel = "R02001"; 
		public static final String Reserve_State_Start = "R02002"; 
		public static final String Reserve_State_Ing = "R02003"; 
		public static final String Reserve_State_End = "R02004"; 	
	}
	public class PmcManagerCode{
		public static final String Position_U01 = "U02001";
		public static final String Position_U02 = "U02002";
		public static final String Position_U03 = "U02003";
		public static final String Position_U04 = "U02004";
		public static final String Position_U05 = "U02005";		
		public static final String Position_U06 = "U02006";
		public static final String PMC_NOT= "N";
		public static final String PMC_YES= "Y";		
	}
	public class PmcManagerMsg{
		public static final String Position_U01 = "사원";
		public static final String Position_U02 = "대리";
		public static final String Position_U03 = "과장";
		public static final String Position_U04 = "차장";
		public static final String Position_U05 = "부장";		
		public static final String Position_U06 = "이사";
		public static final String Mode = "PMC";
	}
	public class PmcReserveCode{
		public static final String Check_R01 = "C01001";
		public static final String Check_R02 = "C01002";
		public static final String Check_R03 = "C01003";
	}
	public class PmcReserveMsg{
		public static final String Check_R01 = "정기점검";
		public static final String Check_R02 = "수시점검";
		public static final String Check_R03 = "퇴실점검";
	}
	
	public class PmcEquipmentCode{
		public static final String EquipType_01 = "E04001";
		public static final String EquipType_02 = "E04002";
		public static final String EquipType_03 = "E04003";
		public static final String EquipType_04 = "E04004";	
	}
	public class PmcEquipmentMsg{
		public static final String EquipType_01 = "PC";
		public static final String EquipType_02 = "서버";
		public static final String EquipType_03 = "스마트단말";
		public static final String EquipType_04 = "기타";
		
	}
	
	public class PmcManagerStateCd{
		public static final String Waiting  = "P02001";
		public static final String Normal   = "P02002";
		public static final String Block    = "P02003";
		public static final String Deleted  = "P02004";
		
	}
	
	public class PmcFacilityCode{
		public static final int VSH_DTT = 10;
		
	}
}
