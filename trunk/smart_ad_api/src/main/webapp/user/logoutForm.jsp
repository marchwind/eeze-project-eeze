
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
<form id="form" action="${pageContext.request.contextPath}/user/logout" method="post">
 <table> 
		<tr>
			<td>userId:<input type="text" id="userId" name="userId" size="30"  value="US0000000211"></td>
		</tr>
		<tr>
			<td style="text-align: center;"><input type="button" id="send" value="send"></td>
		</tr>
 </table>
</form>

</body>
</html>
