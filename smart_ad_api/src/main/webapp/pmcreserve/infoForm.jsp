
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
<form id="form" action="${pageContext.request.contextPath}/pmc/reserve/get" method="post">

 <table>
    <tr>
   <td>managerMode</td>
   <td><input type="text" id="sendPath" name="managerMode" size="30"  value="Y"></td>
  </tr> 
     <tr>
   <td>reserveNo</td>
   <td><input type="text" id="sendPath" name="reserveNo" size="25"  value="RS0000000388"></td>
  </tr>
      <tr>
   <td>reserveDetailNo</td>
   <td><input type="text" id="sendPath" name="reserveDetailNo" size="25"  value="RD0000000314"></td>
  </tr>
   <td colspan="2" style="text-align: center;"><input type="button" id="send" value="send"></td>
  </tr>
 </table>
</form>

</body>
</html>