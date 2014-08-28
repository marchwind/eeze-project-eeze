<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div id="mainLeftLayer" class="mainBox equipment">
	<dl>
		<dt>
			<h2>아이폰 4S</h2>
		</dt>
		<dd class="tc">
			<img class="equipmentIamage" src="${contextPath}/resources/images/image_pc.png" />
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
				<td>스마트 장비</td>
			</tr>
			<tr>
				<td>장비명</td>
				<td><input type="text" value="" class="input_red" /></td>
			</tr>
			<tr>
				<td>모델명</td>
				<td><input type="text" value="" class="input_red" /></td>
			</tr>
			<tr>
				<td>MAC</td>
				<td><input type="text" value="" class="input_red" /></td>
			</tr>
			<tr>
				<td>장비설명</td>
				<td><input type="text" value="" class="input_red" /></td>
			</tr>
			<tr>
				<td>사용여부</td>
				<td>
					<select class="input_red">
						<option>사용가능</option>
						<option>사용불가</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>대여여부</td>
				<td>대여중</td>
			</tr>
		</tbody>					
	</table>
	<div class="table_bottom">
		<div class="fr">
			<input type="button" value="사진변경" class="btn_normal_red" onclick=""/>
			<input type="button" value="수정완료" class="btn_normal_red" onclick="checkPopup()"/>
			<input type="button" value="삭제" class="btn_normal_red" onclick="goPage('${contextPath}/equipment/usedList.do')"/>
		</div>
	</div>
	
</div>
		