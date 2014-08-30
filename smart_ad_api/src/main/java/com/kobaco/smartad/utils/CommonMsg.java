package com.kobaco.smartad.utils;

public class CommonMsg {

	public static final String successCode="0000";
	public static final String failCodeInvalidInput="0100";
	public static final String failCodeInvalidData="0200";
	public static final String failCodeUnAuthrized="0300";
	public static final String failCodeNotFound="0400";
	public static final String failCodeNoUpdateCount="0500";
	public static final String failCodeUnknown="0900";
	public static final String failCode="0900";
	
	
	public class FailCodeUserService {
		public static final String II_NO_ID  = "0101"; // userId isn't
		public static final String II_NO_PWD = "0102"; // userPassword isn't
		public static final String II_CSTC_N = "0103"; // don't consent TSCS (�쎄�)
		public static final String II_CSRI_N = "0104"; // don't consent Receive Info
		public static final String ID_DUP_ID = "0201"; // userId Duplicated
		public static final String II_US_UPDATE ="0105";
	}
	
	public class FailMsgUserService {
		public static final String II_NO_ID  = "userId isn't";
		public static final String II_NO_PWD = "userPassword isn't";
		public static final String II_CSTC_N = "don't consent TSCS (�쎄�)";
		public static final String II_CSRI_N = "don't consent Receive Info";
		public static final String ID_DUP_ID = "userId Duplicated";
		public static final String II_US_UPDATE = "don't update UserInfo";
	}
	
	public class EmailMsgService{
		public static final String ADD_MSG = "회원 가입을 축하 드립니다.";
		public static final String ADD_EAMIL="email_join.html";
		public static final String AUTH_MSG = "본인인증 URL 입니다.";
		public static final String AUTH_EMAIL="email_emailAuth.html";
		public static final String RESET_MSG = "비밀번호가 초기화 되었습니다.";
		public static final String RESET_EAMIL="email_password.html";
		public static final String QNA_MSG = "문의하신 질문에 대한 답변 입니다.";
		public static final String QNA_EAMIL="email_inquery.html";
		
		public static final String ADD_FROM = "smartad@smartad.or.kr";
		
	}
	public class FailCodeNotificationService {
		public static final String II_NT_COUNT  = "0401"; // don't increment Notification's count
		public static final String II_NT_REGISTER  = "0402"; // don't increment Notification's count
		public static final String II_NT_UPDATE = "0403";
		public static final String II_NT_DELETE = "0404";
		
	}
	public class FailMsgNotificationService {
		public static final String II_NT_COUNT  = "don't increment Notification's count";		
	}
	public class FailCodeQnaService {
		public static final String II_QA_REGISTER  = "0501"; // don't register Info
		public static final String II_QA_SELECT  = "0502";
		public static final String II_QA_UPDATE  = "0503";
		public static final String II_QA_DELETE  = "0504";
	}

	public class FailCodeFaqService {
		public static final String II_FAQ_REGISTER  = "8001"; // don't register Info
		public static final String II_FAQ_SELECT  = "0802";
		public static final String II_FAQ_UPDATE  = "0803";
		public static final String II_FAQ_DELETE  = "0804";
	}


	public class FailCodeReserveService {
		public static final String II_RV_REGISTER  = "0601"; // don't register Info
		public static final String II_RV_SELECT  = "0602";
		public static final String II_RV_UPDATE  = "0603";
		public static final String II_RV_DELETE  = "0604";
	}

	public class FailCodePmcMnagerService {
		public static final String II_PM_REGISTER	= "0701"; // don't register Info
		public static final String II_PM_SELECT  	= "0702";
		public static final String II_PM_UPDATE  	= "0703";
		public static final String II_PM_DELETE  	= "0704";
	}

	public class FailCodePmcEquipService {
		public static final String II_EQ_REGISTER	= "0901"; // don't register Info
		public static final String II_EQ_SELECT  	= "0902";
		public static final String II_EQ_UPDATE  	= "0903";
		public static final String II_EQ_DELETE  	= "0904";
	}
	public class FailCodePmcRentService {
		public static final String II_RN_REGISTER	= "1001"; // don't register Info
		public static final String II_RN_SELECT  	= "1002";
		public static final String II_RN_UPDATE  	= "1003";
		public static final String II_RN_DELETE  	= "1004";
	}
	public class FailMsgCRUDService {
		public static final String II_REGISTER   = "don't register Info";
		public static final String II_SELECT  = "don't exist Info ";
		public static final String II_UPDATE  = "don't update Info";
		public static final String II_DELETE  = "don't delete Info";
	}


	public static final String successMsg="success";
	public static final String failMsgeInvalidInput="Invalid Input";
	public static final String failMsgInvalidData="Invalid Data";
	public static final String failMsgUnAuthrized="failCodeUnAuthrized";
	public static final String failMsgNotFound="Data Not Found";
	public static final String failMsgUnknown="Unknown Error";
	public static final String failMsgNoUpdateCount="Update Fail";
	public static final String failMsg="Unknown Error";
}
