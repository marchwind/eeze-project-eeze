/**
 * 
 */

var user = {
	login : false,
	userNo : "",
	userId : "",
	userName : "",
	userEmail : "",
	enterpriseName : ""
}

var reserve = {
	facName : "",
	fac : "FC0000000852",
	year : "",
	month : "",
	cd : ""
}

var facility = {
	c1 : "FC0000000852",
	c2 : "FC0000000853",
	c3 : "FC0000000854",
	c4 : "FC0000000855",
	r1 : "FC0000000856",
	t1 : "FC0000000857"
}

var uri = {
	//serverUrl : window.location.protocol + "//" + window.location.host + "/smart_ad_api",
	serverUrl : window.location.protocol + "//" + window.location.host + "/api",
	loginUrl : "/user/login",
	logoutUrl : "/user/logout",
	sessionCheck : "/user/session",
	idCheckUrl : "/user/idCheck",
	joinUrl : "/user/subscribe",
	emailCeriUrl : "/user/certEmail",
	noticeListUrl : "/noti/list",
	noticeDetailUrl : "/noti/get",
	faqListUrl : "/faq/list",
	qnaAddUrl : "/qna/add",
	qnaListUrl : "/qna/list",
	qnaViewUrl : "/qna/get",
	reserveListUrl : "/reserve/list",
	userIdSearchUrl : "/user/findUserId",
	userPwSearchUrl :"/user/findUserPassword",
	userPwChangeUrl : "/user/updatePassword",
	userGetUrl : "/user/get",
	userModifyUrl : "/user/update",
	userUnsubscribeUrl : "/user/unsubscribe",
	reserveAddUrl : "/reserve/add",
	myReserveListUrl : "/user/reserve/list",
	myReserveCancelUrl : "/reserve/cancel"	
}

var msg = {
	loginError : "로그인에 실패하였습니다.\n다시 시도해 주세요.",
	logoutFail : "로그아웃에 실패하였습니다.\n다시 시도해 주세요.",
	agreeCheck : "이용약관 동의에 체크해 주세요.",
	privateCheck : "개인정보수집에 체크해 주세요.",
	mostId : "아이디는 필수 입니다.",
	checkId : "아이디 중복체크를 하셔야 합니다.",
	idCheckFail : "아이디 중복체크에 실패하였습니다.\n다시 시도해 주세요.",
	mostPw : "비밀번호는 필수 입니다.",
	pwLength : "비밀번호는 8자리 이상이어야 합니다.",
	checkPw : "비밀번호 확인하고 맞지 않습니다.",
	mostName : "이름은 필수 입니다.",
	mostEmail : "이메일은 필수입니다.",
	checkEmail : "이메일 형식에 맞게 입력해주세요.",
	checkNum : "전화번호와 휴대전화는 숫자로 기입하셔야 합니다.",
	mostMobile : "휴대전화는 필수 입니다.",
	failJoin : "회원가입에 실패하였습니다.\n다시 시도해 주세요.",
	certiFail : "이메일 인증에 실패하였습니다.\n관리자에게 문의해 주세요.",
	noticeListFail : "공지사항을 로드하는데 실패하였습니다.\n페이지를 reload해 주세요.",
	mostSubject : "제목은 필수 입니다.",
	mostContent : "내용은 필수 입니다.",
	qnaFail : "1:1문의 작성에 실패하였습니다.\n다시 시도해 주세요.",
	qnaSuccess : "1:1문의를 발송하였습니다.\n작성하신 이메일로 답변을 받아 보실수 있습니다.",
	qnaListEmpty : "1:1문의 리스트가 없습니다.",
	noticeFail : "공지사항을 로드하는데 실패하였습니다.\n다시 시도해 주세요.",
	reserveListFail : "예약관련 통신에 실패하였습니다.\n다시 시도해 주세요.",
	userGetFail : "회원정보를 로드하는데 실패하였습니다.\n다시 시도해 주세요.",
	modifyFail : "회원정보 수정에 실패하였습니다.\n다시 시도해 주세요.",
	modifySuccess : "회원 정보 수정에 성공하였습니다.",
	needLogin : "로그인이 필요한 서비스입니다.\n로그인을 하시겠습니까?",
	overPeriod : "예약기간은 7일 이상 할 수 없습니다.",
	reserveSelect: "예약하실 날짜를 선택하세요.",
	mostEnterprise : "회사명은 필수 입니다.",
	mostWorkContent : "작업내용은 필수 입니다.",
	myReserveListFail : "예약목록을 로드하는데 실패하였습니다.\n다시 시도해 주세요.",
	myReserveCancelFail : "예약취소에 실패하였습니다.\n다시 시도해주세요.",
	myReserveEmpty : "예약목록이 없습니다.",
	qnaListFail : "1:1문의 목록를 로드하는데 실패하였습니다.\n다시 시도해 주세요.",
	qnaDetailFail : "1:1문의 답변을 로드하는데 실패하였습니다.\n다시 시도해 주세요.",
	dropConfirmQuestion : "정말로 탈퇴하시겠습니까?",
	dropSuccessMsg : "더나은 서비스가 될 수 있도록 최선을 다하겠습니다.\n감사합니다.",
	dropFail : "회원 탈퇴에 실패하였습니다.\n다시 시도해 주세요.",
	idSearchFail : "아이디 찾기에 실패하였습니다.\n확인 후 다시 시도해 주세요.",
	pwSearchFail : "비밀번호 찾기에 실패하였습니다.\n확인 후 다시 시도해 주세요.",
	reLogin : "새로운 비밀번호로 다시 로그인해 주세요.",
	pwChangeFail : "비밀번호 변경에 실패하였습니다.\n다시 시도해 주세요.",
	mostReason : "탈퇴사유는 필수 입니다.",
	reserveCancel : "예약을 취소하시겠습니까?"
		
}


