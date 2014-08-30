
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
	<form id="form" action="${pageContext.request.contextPath}/email/info"
		method="post">

		<table>
			<tr>
				<td>emilCrtf</td>
				<td><input type="text" id="sendPath" name="emilCrtf" size="30"
					value="EC0000000038"></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><input
					type="button" id="send" value="send"></td>
			</tr>
		</table>
	</form>

</body>
</html>
