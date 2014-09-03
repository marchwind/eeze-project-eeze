/**
 * 
 */

var user = {
	login : false,
	managerMode : "Y",
	managerNo : "",
	managerId : "",
	managerName : "",
	managerEmail : ""
}

var reserve = {
	facName : "",
	facilityNo : "FC0000000852",
	reserveYear : "",
	reserveMonth : "",
	month : ""
}

var facility = {
	c1 : "FC0000000852",
	c2 : "FC0000000853",
	c3 : "FC0000000854",
	c4 : "FC0000000855",
	r1 : "FC0000000856",
	t1 : "FC0000000857",
	m1 : "FC0000000859"
}

var pageValue = {
	currentPage : 1,
	unitPerPage : 10,
	totalPage : 0
}


var uri = {
	serverUrl : window.location.protocol + "//" + window.location.host + "/smart_ad_api",
	//serverUrl : window.location.protocol + "//" + window.location.host + "/api",
	loginUrl : "/pmc/manager/login",
	logoutUrl : "/pmc/manager/logout",
	sessionCheck : "/pmc/manager/getSession",
	joinUrl : "/pmc/manager/subscribe",
	managerPwUpdateUrl : "/pmc/manager/updatePassword",
	userListUrl : "/pmc/user/search",
	userGetUrl : "/pmc/user/get",
	userAddUtl :"/pmc/user/add",
	userUpdateUrl : "/pmc/user/update",
	userReserveListUrl : "/pmc/user/reserve/list",
	userDeleteUrl :"/pmc/user/delete",
	userBlockUrl : "/pmc/user/setStatusBlock",
	userNormalUrl : "/pmc/user/setStatusNormal",
	reserveListUrl : "/pmc/reserve/list",
	reserveGetUrl : "/pmc/reserve/get",
	reserveAddUrl : "/pmc/reserve/add",
	reserveGetCheckInUrl : "/pmc/reserve/getCheckIn",
	reserveCancelUrl : "/pmc/reserve/cancle",
	reserveCheckInUrl : "/pmc/reserve/checkIn",
	reserveCheckOutUrl : "/pmc/reserve/checkOut",
	noticeListUrl : "/pmc/noti/list",
	noticeDetailUrl : "/pmc/noti/get",
	noticeAddUrl : "/pmc/noti/add",
	noticeUpdateUrl : "/pmc/noti/update",
	noticeDelUrl : "/pmc/noti/delete",
	dataListUrl : "/pmc/archive/list",
	dataDetailUrl : "/pmc/archive/get",
	dataAddUrl : "/pmc/archive/add",
	dataUpdateUrl : "/pmc/archive/update",
	dataDelUrl : "/pmc/archive/delete",
	fileDownloadUrl : "/files/download",
	faqListUrl : "/pmc/faq/list",
	faqAddUrl : "/pmc/faq/add",
	faqUpdateUrl : "/pmc/faq/update",
	faqDelUrl : "/pmc/faq/delete",
	qnaListUrl : "/pmc/qna/list",
	qnaDetailUrl : "/pmc/qna/get",
	qnaAnswerUrl : "/pmc/qna/answer",
	facilityListUrl : "/pmc/facility/main",
	facilityGetUrl :"/pmc/facility/get",
	facilityHistoryLsitUrl : "/pmc/facility/usedHistory",
	facilityUpdateUrl : "/pmc/facility/update",
	equipListUrl : "/pmc/equip/list",
	equipGetUrl : "/pmc/equip/get",
	equipCheckHistoryUrl : "/pmc/equip/checkHistory",
	equipUsedHistoryUrl : "/pmc/equip/usedHistory",
	equipCheckUrl : "/pmc/equip/check",
	equipUpdateUrl : "/pmc/equip/update",
	equipDelUrl : "/pmc/equip/delete",
	equipAddUrl : "/pmc/equip/add",
	equipRentUrl : "/pmc/equip/rent",
	equipRentInfoUrl : "/pmc/equip/rentInfo",
	equipReturnUrl : "/pmc/equip/return",
	equipSmartHistoryListUrl : "/pmc/equip/rentList",
	equipPowerStateUrl : "/pmc/equip/updatePowerState",
	nfcUrl : "/pmc/equip/getByTag",
	managerListUrl : "/pmc/manager/list",
	managerGetUrl : "/pmc/manager/get",
	managerAddUrl : "/pmc/manager/add",
	managerUpdateUrl : "/pmc/manager/update",
	managerAckUrl : "/pmc/manager/ack",
	managerPwdResetUrl : "/pmc/manager/passwordReset",
	statisticsFacilityUrl : "/pmc/statistics/facility",
	statisticsUserUrl : "/pmc/statistics/user",
	statisticsReserveUrl : "/pmc/statistics/reserve",
	equipUsedStatistics : "/pmc/statistics/equipment"
}

var msg = {
	noData : "데이터가 없습니다.",
	pageLoadFail : "페이지를 로그하는데 실패하였습니다.\n다시 시도해 주세요.",
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
	mostTel : "전화번호는 필수 입니다.",
	mostPostion : "직급은 필수입니다.",
	successRequest : "계정요청을 하였습니다.\n승인 후 사용이 가능합니다.",
	failRequest : "계정요청에 실패하였습니다.\n다시 시도해 주세요.",
	failJoin : "회원가입에 실패하였습니다.\n다시 시도해 주세요.",	
	failUserList : "회원 리스트 로드에 실패하였습니다.\n다시 시도해 주세요.",
	failUserDetail : "회원 상세 로드에 실패하였습니다.\n다시 시도해 주세요.",	
	updateSuccess : "수정을 완료했습니다.",
	updateFail : "수정에 실패하였습니다.\n다시 시도해 주세요.",
	successAdd : "회원가입에 성공하였습니다.",
	userDelConfirm : "해당 회원을 삭제하시겠습니까?",
	userDel : "해당 회원을 삭제하였습니다.",
	userBlockConfirm : "해당 회원을 차단하시겠습니까?",
	userBlock : "해당 회원을 차단하였습니다.",
	userNormalConfirm : "해당 회원을 차단해제하시겠습니까?",
	userNormal : "해당 회원을 차단해제하였습니다.",
	reserveSelect: "예약하실 날짜를 선택하세요.",
	mostMemberInfo : "회원 검색을 해주세요.",
	mostWorkContent : "작업내용은 필수 입니다.",
	reserveAddSuccess : "예약을 등록하였습니다.",
	reserveCancel : "예약이 취소 되었습니다.", 
	reserveListFail : "예약관련 통신에 실패하였습니다.\n다시 시도해 주세요.",
	mostSubject : "제목은 필수 입니다.",
	mostContent : "내용은 필수 입니다.",
	noticeAddFail : "공지사항을 등록하는데 실패하였습니다.\n다시 시도해 주세요.",
	noticeDelSuccess : "공지사항을 삭제하였습니다.",
	noticeDelFail : "공지사항 삭제에 실패하였습니다.\n다시 시도해 주세요.",
	noticeUpdateSuccess : "공지사항을 수정하였습니다.",
	noticeUpdateFail : "공지사항 수정에 실패하였습니다.\n다시 시도해 주세요.",
	dataAddFail : "자료실을 등록하는데 실패하였습니다.\n다시 시도해 주세요.",
	dataDelSuccess : "자료를 삭제하였습니다.",
	dataDelFail : "자료 삭제에 실패하였습니다.\n다시 시도해 주세요.",
	dataUpdateSuccess : "자료을 수정하였습니다.",
	dataUpdateFail : "자료 수정에 실패하였습니다.\n다시 시도해 주세요.",
	faqDelete : "FAQ를 삭제하였습니다.",
	faqDeleteFail : "FAQ삭제에 실패하였습니다.\n다시 시도해 주세요.",
	faqUpdate : "FAQ를 수정하였습니다.",
	faqUpdateFail : "FAQ 수정에 실패하였습니다.\n다시 시도해 주세요.",
	mostfaqQustion : "질문은 필수 입니다.",
	mostfaqAnswer :"답변은 필수 입니다.",
	faqAddFail : "FAQ등록에 실패하였습니다.\n다시 시도해 주세요.",
	mostQnaAnswer : "답변은 필수 입니다.",
	qnaAnswerSend : "답변메일을 전송했습니다.",
	qnaDetailFail : "1:1문의 관련 통신에 실패하였습니다.\n다시 시도해 주세요.",
	failityUpdate : "시설정보가 수정되었습니다.",
	rentAgree : "스마트 장비를 대여하시겠습니까?",
	rent : "스마트 장비를 대여하였습니다.",
	rentReturn : "스마트 장비를 반환하였습니다.",
	equipDelConfirm : "장비를 삭제하시겠습니까?",
	equipDel : "장비를 삭제 하였습니다.",
	equipAdd : "장비를 추가하였습니다.",
	equipCheck : "점검을 완료하였습니다.",
	equipUpdate : "장비정보가 수정되었습니다.",
	equipPowerChange : "장비 전원 상태가 변경되었습니다.",
	equipDetailFail : "장비 상세 관련 오류가 발생하였습니다.\n다시 시도해 주세요.",
	managerFail : "계정관련 통신에 실패하였습니다.\n다시 시도해 주세요.",
	managerAdd : "계정을 등록하였습니다.",
	managerModify : "계정 수정을 하였습니다.",
	ackSuccess : "계정상태를 수정하였습니다.",
	
	mainError : "통계관련 통신에 실패하였습니다.\n다시 시도해 주세요.",
	facilityError :"시설관련 통신에 실패하였습니다.\n다시 시도해 주세요.",
	equipmentError : "장비관련 통신에 실패하였습니다.\n다시 시도해 주세요.",
	cmsError : "CMS관련 통신에 실패하였습니다.\n다시 시도해 주세요.",
	
	qnaFail : "1:1문의 작성에 실패하였습니다.\n다시 시도해 주세요.",
	qnaSuccess : "1:1문의를 발송하였습니다.\n작성하신 이메일로 답변을 받아 보실수 있습니다.",
	qnaListEmpty : "1:1문의 리스트가 없습니다.",
	needLogin : "로그인이 필요한 서비스입니다.\n로그인을 하시겠습니까?",
	overPeriod : "예약기간은 7일 이상 할 수 없습니다.",
	mostEnterprise : "회사명은 필수 입니다.",
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
	mostReason : "탈퇴사유는 필수 입니다."
		
}


