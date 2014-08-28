<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
</head>

<body>

<div id="wrap">
	<%@ include file="../common/header.jsp" %>
	<div id="content">
		<div class="title">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>장비관리 > 장비등록</h1>
		</div>
		<div class="mainTable">
			<div id="mainLeftLayer" class="mainBox equipment">
				<dl>
					<dt>
						<h2>장비 추가</h2>
					</dt>
					<dd class="tc">
						<input type="button" value="사진등록" class="btn_normal_red">
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
					</tbody>					
				</table>
				<div class="table_bottom">
					<div class="fr">
						<input type="button" value="등록완료" class="btn_normal_red" onclick="checkPopup()"/>
						<input type="button" value="취소" class="btn_normal_red" onclick="goPage('${contextPath}/equipment/usedList.do')"/>
					</div>
				</div>
				
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp" %>

</div>

</body>
</html> 