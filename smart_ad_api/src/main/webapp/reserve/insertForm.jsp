
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
<form id="form" action="${pageContext.request.contextPath}/reserve/add" method="post">

 <table> 
     <tr>
   <td>enterpriseName</td>
   <td><input type="text" id="sendPath" name="enterpriseName" size="1"  value="enterpriseName"></td>
  </tr>
    <tr>
   <td>workerName</td>
   <td><input type="text" id="sendPath" name="workerName" size="25"  value="worker"></td>
  </tr>
      <tr>
   <td>visitCount</td>
   <td><input type="text" id="sendPath" name="visitCount" size="10"  value="10"></td>
  </tr>
    <tr>
   <td>workContent</td>
   <td><input type="text" id="sendPath" name="workContent" size="25"  value="workContent"></td>
  </tr>
      <tr>
   <td>facilityNo</td>
   <td><input type="text" id="sendPath" name="facilityNo" size="25"  value="FC0000000852"></td>
  </tr>
  <tr>
   <td>reserveArray</td>
   <td><input type="text" id="sendPath" name="reserveArray" size="25"  value="2014-08-01/1"></td>
  </tr>
    <tr>
   <td>reserveArray</td>
   <td><input type="text" id="sendPath" name="reserveArray" size="25"  value="2014-08-02/1"></td>
  </tr>
    <tr>
   <td>reserveArray</td>
   <td><input type="text" id="sendPath" name="reserveArray" size="25"  value="2014-08-03/1"></td>
  </tr>
    <tr>
   <td>reserveArray</td>
   <td><input type="text" id="sendPath" name="reserveArray" size="25"  value="2014-08-04/1"></td>
  </tr>
    <tr>
   <td>reserveArray</td>
   <td><input type="text" id="sendPath" name="reserveArray" size="25"  value="2014-08-04/1"></td>
  </tr>
  
   <td colspan="2" style="text-align: center;"><input type="button" id="send" value="send"></td>
  </tr>
 </table>
</form>

</body>
</html>
