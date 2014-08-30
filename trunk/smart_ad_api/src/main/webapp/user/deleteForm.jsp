

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
<form id="form" action="${pageContext.request.contextPath}/qna/delete" method="post">

 <table> 
    <tr>
   <td>qnaNo</td>
   <td><input type="text" id="sendPath" name="qnaNo" size="30"  value=QA0000000027></td>
  </tr>
  <tr>
   <td colspan="2" style="text-align: center;"><input type="button" id="send" value="send"></td>
  </tr>
 </table>
</form>

</body>
</html>
