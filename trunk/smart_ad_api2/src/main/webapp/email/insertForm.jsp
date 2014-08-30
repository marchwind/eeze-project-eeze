
<html>
<head>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>userEmail</title>
<script type="text/javascript">
 
$(function() {
 $('#send').click(function() {
  $('#form').submit();
 });
});
</script>
</head>
<body>
	<form id="form"
		action="${pageContext.request.contextPath}/email/insert" method="post">

		<table>
			<tr>
				<td>emailcrtfYn</td>
				<td><input type="text" id="toEmail" name="emailcrtfYn"
					size="30" value="Y"></td>
			</tr>
			<tr>
				<td>UserNo</td>
				<td><input type="text" id="subject" name="UserNo" size="30"
					value="US0000000113"></td>
			</tr>
			<tr>
				<td>registerId</td>
				<td><input type="text" id="subject" name="registerId" size="30"
					value="HEEEEE"></td>
			</tr>
			<tr>
				<td>updaterId</td>
				<td><input type="text" id="sendPath" name="updaterId" size="30"
					value="welcome"></td>
			</tr>



			<tr>
				<td colspan="2" style="text-align: center;"><input
					type="button" id="send" value="send"></td>
			</tr>
		</table>
	</form>

</body>
</html>
