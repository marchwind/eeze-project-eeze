
<html>
<head>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Enterprise</title>
<script type="text/javascript">
 
$(function() {
 $('#send').click(function() {
  $('#form').submit();
 });
});
</script>
</head>
<body>
<form id="form" action="${pageContext.request.contextPath}/famanager/update" method="post">

 <table>
 <tr>
   <td>notificationSubject</td>
   <td><input type="text" id="toEmail" name="managerpermissionNo" size="30" value="seoul"></td>
  </tr>
  <tr>
   <td>notificationSubject</td>
   <td><input type="text" id="toEmail" name="managerName" size="30" value="eoclehdrla@gmail.com"></td>
  </tr>
  
  <tr>
   <td>RegisterId</td>
   <td><input type="text" id="subject" name="password" size="30"  value="HEEEEE"></td>
  </tr>
   <tr>
   <td>UpdaterId</td>
   <td><input type="text" id="sendPath" name="cellphone" size="30"  value="welcome"></td>
  </tr>
     <tr>
   <td>UpdaterId</td>
   <td><input type="text" id="sendPath" name="phone" size="30"  value="welcome"></td>
  </tr>
     <tr>
   <td>UpdaterId</td>
   <td><input type="text" id="sendPath" name="confirmYn" size="30"  value="welcome"></td>
  </tr>
     <tr>
   <td>UpdaterId</td>
   <td><input type="text" id="sendPath" name="confirmId" size="30"  value="welcome"></td>
  </tr>
     <tr>
   <td>UpdaterId</td>
   <td><input type="text" id="sendPath" name="confirmDate" size="30"  value="welcome"></td>
  </tr>
     <tr>
   <td>UpdaterId</td>
   <td><input type="text" id="sendPath" name="registerId" size="30"  value="welcome"></td>
  </tr>
     <tr>
   <td>UpdaterId</td>
   <td><input type="text" id="sendPath" name="updaterId" size="30"  value="welcome"></td>
  </tr>
  <tr>
   <td colspan="2" style="text-align: center;"><input type="button" id="send" value="send"></td>
  </tr>
 </table>
</form>

</body>
</html>
