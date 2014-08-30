
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
<form id="form" action="${pageContext.request.contextPath}/user/login" method="post">

 <table>
 <tr>
   <td>userId</td>
   <td><input type="text" id="toEmail" name="userId" size="30" value="sui"></td>
  </tr>
  <tr>
   <td>userPassword</td>
   <td><input type="text" id="toEmail" name="userPassword" size="30" value="0000"></td>
  </tr>
  
  <tr>
   <td colspan="2" style="text-align: center;"><input type="button" id="send" value="send"></td>
  </tr>
 </table>
</form>

</body>
</html>
