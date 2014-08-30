
<html>
<head>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user</title>
<script type="text/javascript">
 
$(function() {
 $('#send').click(function() {
  $('#form').submit();
 });
});
</script>
</head>
<body>
<form id="form" action="${pageContext.request.contextPath}/qna/add" method="post">

 <table>
 <tr>
   <td>queryContent</td>
   <td><input type="text" id="toEmail" name="queryContent" size="30" value="seoul"></td>
  </tr>
  <tr>
   <td>querySubject</td>
   <td><input type="text" id="toEmail" name="querySubject" size="30" value="123456"></td>
  </tr>
   <tr>
   <td>userNo</td>
   <td><input type="text" id="toEmail" name="userNo" size="30" value="US0000000228"></td>
  </tr>
   <tr>
   <td>managerNo</td>
   <td><input type="text" id="toEmail" name="managerNo" size="30" value="MG0000000093"></td>
  </tr>  
  <tr>
   <td colspan="2" style="text-align: center;"><input type="button" id="send" value="send"></td>
  </tr>
 </table>
</form>

</body>
</html>
