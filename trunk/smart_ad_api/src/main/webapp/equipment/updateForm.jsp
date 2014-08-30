
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
<form id="form" action="${pageContext.request.contextPath}/equipment/update" method="post">

 <table>
<tr>
   <td>equipmentName</td>
   <td><input type="text" id="toEmail" name="equipmentName" size="30" value="name"></td>
  </tr>
  <tr>
   <td>equipmentIp</td>
   <td><input type="text" id="subject" name="equipmentIp" size="30"  value="su"></td>
  </tr>
   <tr>
   <td>equipmentType</td>
   <td><input type="text" id="sendPath" name="equipmentType" size="30"  value="o"></td>
  </tr>
  
   <tr>
   <td>UpdaterId</td>
   <td><input type="text" id="sendPath" name="updaterId" size="30"  value="welcome"></td>
  </tr>
  <tr>
   <td>equipmentmgmtNo</td>
   <td><input type="text" id="toEmail" name="equipmentmgmtNo" size="30" value="EQ0000000010"></td>
  </tr>
  <tr>

  <tr>
   <td colspan="2" style="text-align: center;"><input type="button" id="send" value="send"></td>
  </tr>
 </table>
</form>

</body>
</html>
