<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<table class="facility_detail">
	<colgroup>
		<col width="15%" />
		<col width="*" />
		<col width="40%" />
	</colgroup>
	<tr>
		<td>시설 이름</td>
		<td class="tl">녹음스튜디오</td>
		<td class="tl" rowspan="3">시설위치 
			<div id="map"></div>
		</td>
	</tr>
	<tr>
		<td>시설 설명</td>
		<td class="tl"><input type="text" class="input_red" value="간단한 녹음, 더빙 작업을 통해" /></td>
	</tr>
	<tr>
		<td>상태</td>
		<td class="tl">
			<select class="input_red">
				<option>사용가능</option>
				<option>사용불가</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>장비현황</td>
		<td colspan="2">
			<table>
				<colgroup>
					<col width="10%" />
					<col width="20%" />
					<col width="*" />
					<col width="20%" />
					<col width="10%" />
					<col width="10%" />
				</colgroup>
				<thead>
					<tr>
						<th>장비유형</th>
						<th>장비명</th>
						<th>모델명</th>
						<th>IP주소</th>
						<th>가능여부</th>
						<th>사용이력</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>일반기기</td>
						<td>녹음 마이크</td>
						<td>Sontronics stc-20</td>
						<td> - </td>
						<td>가능</td>
						<td></td>
					</tr>
					<tr>
						<td>일반PC</td>
						<td>애믈 맥 프로</td>
						<td>Mac pro</td>
						<td>192.168.10.54</td>
						<td>가능</td>
						<td><input type="button" value="확인" class="btn_normal_red" /></td>
					</tr>
				</tbody>
			</table>
		</td>
	</tr>
</table>
<div class="table_bottom">
	<p class="fl">* 장비 추가 및 삭제는 장비 관리에서 진행하세요</p>
	<div class="fr">
		<input type="button" value="수정완료" class="btn_normal_red" />
		<input type="button" value="취소" class="btn_normal_red" />
	</div>
</div>
				