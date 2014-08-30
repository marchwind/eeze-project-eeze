
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
<form id="form" action="${pageContext.request.contextPath}/facilities/update" method="post">

 <table>
 <tr>
   <td>facilitymgmntNo</td>
   <td><input type="text" id="toEmail" name="facilitymgmntNo" size="30" value="seoul"></td>
  </tr>
  <tr>
   <td>facilitytypeCode</td>
   <td><input type="text" id="toEmail" name="facilitytypeCode" size="30" value="eoclehdrla@gmail.com"></td>
  </tr>
  <tr>
   <td>facilityName</td>
   <td><input type="text" id="subject" name="facilityName" size="30"  value="change"></td>
  </tr>
   <tr>
   <td>facilityExplation</td>
   <td><input type="text" id="sendPath" name="facilityExplation" size="30"  value="cs0000000"></td>
  </tr>
   <tr>
   <td>fcilityseatNum</td>
   <td><input type="text" id="toEmail" name="fcilityseatNum" size="30" value="02787878"></td>
  </tr>

    <tr>
   <td>UpdaterId</td>
   <td><input type="text" id="sendPath" name="updaterId" size="30"  value="welcome"></td>
  </tr>
    <tr>
   <td>enterpriseNo</td>
   <td><input type="text" id="sendPath" name="enterpriseNo" size="30"  value="EP0000000202"></td>
  </tr>
  <tr>
   <td colspan="2" style="text-align: center;"><input type="button" id="send" value="send"></td>
  </tr>
 </table>
</form>

</body>
</html>
