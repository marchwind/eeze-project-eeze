<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User</title>
<script type="text/javascript">
 
$(function() {
 $('#send').click(function() {
  $('#form').submit();
 });
});
</script>
</head>
<body>
<form id="form" action="${pageContext.request.contextPath}/user/update" method="post">

 <table>
 <tr>
   <td>
   userNo(회원검색):<input type="text" id="userNo" name="userNo" size="30" ><br/>
   userEmail:<input type="text" id="userEmail" name="userEmail" size="30" ><br/>
   userPassword:<input type="text" id="userPassword" name="userPassword" size="30" ><br/>
   userName:<input type="text" id="userName" name="userName" size="30"><br/>
   enterpriseName:<input type="text" id="enterpriseName" name="enterpriseName" size="30"><br/>
   enterpriseAddress:<input type="text" id="enterpriseAddress" name="enterpriseAddress" size="30"><br/>
   userPhone:<input type="text" id="userPhone" name="userPhone" size="30"><br/>
   userCellPhone:<input type="text" id="userCellPhone" name="userCellPhone" size="30"><br/>
   consentReceiveInfoYn:<input type="text" id="consentReceiveInfoYn" name="consentReceiveInfoYn" value="N" size="30"><br/>
  </tr>
   <tr>
   <td colspan="2" style="text-align: center;"><input type="button" id="send" value="send"></td>
  </tr>
  </table>
</form>

</body>
</html>
