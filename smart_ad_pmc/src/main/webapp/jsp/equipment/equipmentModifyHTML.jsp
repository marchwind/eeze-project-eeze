<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<div id="mainLeftLayer" class="mainBox equipment">
	<dl>
		<dt>
			<h2>애플 맥 프로</h2>
		</dt>
		<dd class="tc">
			<img class="equipmentIamage" src="${contextPath}/resources/images/image_pc.png" />
			<div class="equipment_position">
				<p>설치위치 : 
					<select class="input_red">
						<option>녹음스튜디오</option>
						<option>창작지원1실</option>
						<option>창작지원2실</option>
						<option>창작지원3실</option>
						<option>창작지원4실</option>
						<option>서버실</option>
						<option>매체적합성테스트실</option>
						<option>회의실</option>
						<option>지원실</option>
					</select>
				</p>
			</div>
		</dd>
	</dl>
</div>
<div id="mainRightLayer" class="equipment" >
	<table id="memberReserveList">
		<colgroup>
			<col width="30%" />
			<col width="*" />
		</colgroup>
		<tbody>
			<tr>
				<td>장비유형</td>
				<td>
					<select class="input_red">
						<option>일반PC</option>
						<option>서버</option>
						<option>일반기기</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>장비명</td>
				<td><input type="text" value="애플 맥 프로" class="input_red" /></td>
			</tr>
			<tr>
				<td>모델명</td>
				<td><input type="text" value="Mac Pro" class="input_red" /></td>
			</tr>
			<tr>
				<td>IP 주소</td>
				<td><input type="text" value="192.168.10.12" class="input_red" /></td>
			</tr>
			<tr>
				<td>MAC</td>
				<td><input type="text" value="00:db:14:db:02" class="input_red" /></td>
			</tr>
			<tr>
				<td>메모리 사용량</td>
				<td>12,458KB</td>
			</tr>
			<tr>
				<td>장비설명</td>
				<td><input type="text" value="3D작업이 가능한 PC" class="input_red" /></td>
			</tr>
			<tr>
				<td>사용여부</td>
				<td>가능</td>
			</tr>
			<tr>
				<td>최종점검일</td>
				<td>2014/06/24 15:00</td>
			</tr>
		</tbody>					
	</table>
	<div class="table_bottom">
		<div class="fr">
			<input type="button" value="수정 완료" class="btn_normal_red" onclick="passwordReset()"/>
			<input type="button" value="수정 취소" class="btn_normal_red" onclick="checkPopup()"/>
			<input type="button" value="삭제" class="btn_normal_red" onclick="goPage('${contextPath}/equipment/usedList.do')"/>
		</div>
	</div>
	
</div>
		