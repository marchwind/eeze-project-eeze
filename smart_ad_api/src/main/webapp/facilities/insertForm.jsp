
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
<form id="form" action="${pageContext.request.contextPath}/facilities/insert" method="post">

 <table>

  <tr>
   <td>facilitytypeCode</td>
   <td><input type="text" id="toEmail" name="facilitytypeCode" size="30" value="eom"></td>
  </tr>
  <tr>
   <td>facilityName</td>
   <td><input type="text" id="subject" name="facilityName" size="30"  value="su"></td>
  </tr>
   <tr>
   <td>facilityExplation</td>
   <td><input type="text" id="sendPath" name="facilityExplation" size="30"  value="bs0000000"></td>
  </tr>
   <tr>
   <td>fcilityseatNum</td>
   <td><input type="text" id="toEmail" name="fcilityseatNum" size="30" value="02"></td>
  </tr>
 
   <tr>
   <td>RegisterId</td>
   <td><input type="text" id="subject" name="registerId" size="30"  value="HEEEEE"></td>
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
