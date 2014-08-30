
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
<form id="form" action="${pageContext.request.contextPath}/pmc/user/search" method="post">

 <table> 
     <tr>
   <td>managerMode</td>
   <td><input type="text" id="sendPath" name="managerMode" size="30"  value="Y"></td>
  </tr>
   		<tr>
			<td>userId:<input type="text" id="userId" name="userId" size="30"  value="bhna"></td>
		</tr>
		<tr>
			<td>userName:<input type="text" id="userName" name="userName" size="30"  value="강민수"></td>
		</tr>
		<tr>
			<td>enterpriseName:<input type="text" id="userId" name="enterpriseName" size="30"  value="tera"></td>
		</tr>
		<tr>
   <td>currentPage</td>
   <td><input type="text" id="sendPath" name="currentPage" size="1"  value="1"></td>
  </tr>
    <tr>
   <td>unitPerPage</td>
   <td><input type="text" id="sendPath" name="unitPerPage" size="10"  value="10"></td>
  </tr>
  
		<tr>
			<td style="text-align: center;"><input type="button" id="send" value="send"></td>
		</tr>
 </table>
</form>

</body>
</html>
