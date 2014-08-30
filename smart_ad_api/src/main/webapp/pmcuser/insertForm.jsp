
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
<form id="form" action="${pageContext.request.contextPath}/pmc/user/add" method="post">

 <table>
      <tr>
   <td>managerMode</td>
   <td><input type="text" id="sendPath" name="managerMode" size="30"  value="Y"></td>
  </tr>
 <tr>
	<td>
	userId:<input type="text" id="userId" name="userId" size="30" value="firehouse"><br/>
	userEmail:<input type="text" id="userEmail" name="userEmail" size="30" value="eoclehdrla@gmail.com"></br>
	userPassword: <input type="password" id="userPassword" name="userPassword" size="30" value="firehouse00"><br/>
	userName:<input type="text" id="userName" name="userName" size="30" value="강민수"><br/>
	enterpriseName:<input type="text" id="enterpriseName" name="enterpriseName" size="30" value="테라스코프"><br/>
	enterpriseAddress:<input type="text" id="enterpriseAddress" name="enterpriseAddress" size="30" value="성동구"><br/>
	userPhone:<input type="text" id="userPhone" name="userPhone" size="30" value="02-1818-1818"><br/>
	userCellPhone:<input type="text" id="userCellPhone" name="userCellPhone" size="30" value="010-5157-6433"><br/>
	consentTscsYn(Y/N):<input type="text" id="consentTscsYn" name="consentTscsYn" size="30" value="Y"><br/>
	consentReceiveInfoYn(Y/N):<input type="text" id="consentReceiveInfoYn" name="consentReceiveInfoYn" size="30" value="Y"><br/>
	</td>
  </tr>  
  <tr>
   <td style="text-align: center;"><input type="button" id="send" value="send"></td>
  </tr>
 </table>
</form>

</body>
</html>

