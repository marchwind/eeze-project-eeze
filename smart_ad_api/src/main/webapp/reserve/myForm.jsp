
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
<form id="form" action="${pageContext.request.contextPath}/user/reserve/list" method="post">

 <table> 
     <tr>
   <td>currentPage</td>
   <td><input type="text" id="sendPath" name="currentPage" size="1"  value="1"></td>
  </tr>
    <tr>
   <td>unitPerPage</td>
   <td><input type="text" id="sendPath" name="unitPerPage" size="25"  value="10"></td>
  </tr>
      <tr>
   <td>facilityiesNo</td>
   <td><input type="text" id="sendPath" name="facilityNo" size="10"  value="FC0000000852"></td>
  </tr>
    <tr>
   <td colspan="2" style="text-align: center;"><input type="button" id="send" value="send"></td>
  </tr>
 </table>
</form>

</body>
</html>
