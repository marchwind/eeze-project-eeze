
<html>
<head>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Enterprise</title>
<script type="text/javascript">
 
$(function() {
 $('#send').click(function() {
  $('#form').submit();
 });
});
</script>
</head>
<body>
<form id="form" action="${pageContext.request.contextPath}/faq/update" method="post">

 <table>
   <tr>
   <td>faqNo</td>
   <td><input type="text" id="sendPath" name="faqNo" size="30"  value="FQ0000000028"></td>
  </tr>
 <tr>
   <td>faqSubject</td>
   <td><input type="text" id="toEmail" name="faqSubject" size="30" value="seoul"></td>
  </tr>
  <tr>
   <td>faqContent</td>
   <td><input type="text" id="toEmail" name="faqContent" size="30" value="eoclehdrla@gmail.com"></td>
  </tr>
  
  <tr>
   <td>RegisterId</td>
   <td><input type="text" id="subject" name="registerId" size="30"  value="HEEEEE"></td>
  </tr>
   <tr>
   <td>UpdaterId</td>
   <td><input type="text" id="sendPath" name="updaterId" size="30"  value="welcome"></td>
  </tr>
 </table>
</form>

</body>
</html>
