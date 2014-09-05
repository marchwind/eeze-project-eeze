package com.terascope.amano.incheon.dto;

public class TestDto {

	public static CarInfoDto dto1(){
		 CarInfoDto cardto = new CarInfoDto();
		 cardto.setCAR_SERS_CD("000111");
		 cardto.setCAR_SERS_NM("제네시스");
		return cardto;
	}
	
	public static CarInfoDto dto2(){
		 CarInfoDto cardto = new CarInfoDto();
		 cardto.setCAR_SERS_CD("000112");
		 cardto.setCAR_SERS_NM("세단");
		return cardto;
	}
	public static CarInfoDto dto3(){
		 CarInfoDto cardto = new CarInfoDto();
		 cardto.setCAR_SERS_CD("000113");
		 cardto.setCAR_SERS_NM("쿠페");
		return cardto;
	}
	public static CarInfoDto dto4(){
		 CarInfoDto cardto = new CarInfoDto();
		 cardto.setCAR_SERS_CD("000114");
		 cardto.setCAR_SERS_NM("컨버터블");
		return cardto;
	}
	public static AuthDto auth1(){
		AuthDto auth = new AuthDto();
		auth.setDEVC_NO("1111111");
		auth.setPRG_VER("1.0");
		auth.setAUTH_NO("0101");
		return auth;
	}
	public static AuthDto auth2(){
		AuthDto auth = new AuthDto();
		auth.setDEVC_NO("1111112");
		auth.setPRG_VER("1.0");
		auth.setAUTH_NO("0102");
		return auth;
	}
	public static AuthDto auth3(){
		AuthDto auth = new AuthDto();
		auth.setDEVC_NO("1111113");
		auth.setPRG_VER("1.0");
		auth.setAUTH_NO("0103");
		return auth;
	}
	public static AuthDto auth4(){
		AuthDto auth = new AuthDto();
		auth.setDEVC_NO("1111114");
		auth.setPRG_VER("1.0");
		auth.setAUTH_NO("0104");
		return auth;
	}
	
	public static LoginDto login1(){
		LoginDto login = new LoginDto();
		login.setUSR_NM("김태희");
		login.setUSR_TY_CD("01");
		login.setUSR_ID("kkkkdd");
		login.setUSR_PWD("1234");
		return login;
	}
	public static LoginDto login2(){
		LoginDto login = new LoginDto();
		login.setUSR_NM("김도진");
		login.setUSR_TY_CD("02");
		login.setUSR_ID("ddddddd");
		login.setUSR_PWD("ivpsys_00!@");
		return login;
	}
	public static LoginDto login3(){
		LoginDto login = new LoginDto();
		login.setUSR_NM("이민호");
		login.setUSR_TY_CD("05");
		login.setUSR_ID("rarararara");
		login.setUSR_PWD("4321");
		return login;
	}
	
	
	
}
