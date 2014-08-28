
<html>
<head>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user</title>
<script type="text/javascript">
 
$(function() {
 $('#send').click(function() {
  $('#form').submit();
 });
});
</script>
</head>
<body>
<form id="form" action="${pageContext.request.contextPath}/user/subscribe" method="post">

 <table>
 <tr>
	<td>
	userId:<input type="text" id="userId" name="userId" size="30" value="seoul"><br/>
	userEmail:<input type="text" id="userEmail" name="userEmail" size="30" value="123456"></br>
	userPassword: <input type="password" id="userPassword" name="userPassword" size="30" value=""><br/>
	userName:<input type="text" id="userName" name="userName" size="30" value=""><br/>
	enterpriseName:<input type="text" id="enterpriseName" name="enterpriseName" size="30" value=""><br/>
	enterpriseAddress:<input type="text" id="enterpriseAddress" name="enterpriseAddress" size="30" value=""><br/>
	전화번호:<input type="text" id="userPhone" name="userPhone" size="30" value=""><br/>
	휴대전화번호:<input type="text" id="userCellPhone" name="userCellPhone" size="30" value=""><br/>
	약관동의(Y/N):<input type="text" id="consentTscsYn" name="consentTscsYn" size="30" value=""><br/>
	정보수신동의(Y/N):<input type="text" id="consentReceiveInfoYn" name="consentReceiveInfoYn" size="30" value=""><br/>
	</td>
  </tr>  
  <tr>
   <td style="text-align: center;"><input type="button" id="send" value="send"></td>
  </tr>
 </table>
</form>

</body>
</html>

