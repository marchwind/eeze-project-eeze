
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
<form id="form" action="${pageContext.request.contextPath}/scanmanagement/update" method="post">

 <table>
 <tr>
   <td>scanNo</td>
   <td><input type="text" id="toEmail" name="scanNo" size="30" value="SM0000000016"></td>
  </tr>
<tr>
   <td>scanName</td>
   <td><input type="text" id="toEmail" name="scanName" size="30" value="seoul"></td>
  </tr>
   <tr>
   <td>registerId</td>
   <td><input type="text" id="sendPath" name="registerId" size="30"  value="welcome"></td>
  </tr>
    <tr>
   <td>updaterId</td>
   <td><input type="text" id="sendPath" name="updaterId" size="30"  value="SM0000000016"></td>
  </tr>
  <tr>
   <td colspan="2" style="text-align: center;"><input type="button" id="send" value="send"></td>
  </tr>
 </table>
</form>

</body>
</html>
