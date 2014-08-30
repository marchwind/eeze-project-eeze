
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
<form id="form" action="${pageContext.request.contextPath}/qna/list" method="post">

 <table> 
     <tr>
   <td>currentPage</td>
   <td><input type="text" id="sendPath" name="currentPage" size="1"  value="1"></td>
  </tr>
    <tr>
   <td>unitPerPage</td>
   <td><input type="text" id="sendPath" name="unitPerPage" size="25"  value="25"></td>
  </tr>
      <tr>
   <td>queryContent</td>
   <td><input type="text" id="sendPath" name="queryContent" size="10"  value="q_CNTT"></td>
  </tr>
    <tr>
   <td>userName</td>
   <td><input type="text" id="sendPath" name="userName" size="25"  value="US0000000370"></td>
  </tr>
      <tr>
   <td>querySubject</td>
   <td><input type="text" id="sendPath" name="querySubject" size="25"  value="q_SBJT"></td>
  </tr>
  <tr>
   <td colspan="2" style="text-align: center;"><input type="button" id="send" value="send"></td>
  </tr>
 </table>
</form>

</body>
</html>
