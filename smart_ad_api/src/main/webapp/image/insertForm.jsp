
<html>
<head>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Image</title>
<script type="text/javascript">
 
$(function() {
 $('#send').click(function() {
  $('#form').submit();
 });
});
</script>
</head>
<body>
<form id="form" action="${pageContext.request.contextPath}/image/insert" method="post">

 <table>
 <tr>
   <td>facilitymgmntNo</td>
   <td><input type="text" id="toEmail" name="facilitymgmntNo" size="30" value="FC0000000192"></td>
  </tr>
  <tr>
   <td>equipmentmgmtNo</td>
   <td><input type="text" id="toEmail" name="equipmentmgmtNo" size="30" value="EQ0000000052"></td>
  </tr>
  <tr>
   <td>imageName</td>
   <td><input type="text" id="subject" name="imageName" size="30"  value="su"></td>
  </tr>
   <tr>
   <td>imagestorePath</td>
   <td><input type="text" id="sendPath" name="imagestorePath" size="30"  value="o"></td>
  </tr>
     <tr>
   <td>imageUrl</td>
   <td><input type="text" id="sendPath" name="imageUrl" size="30"  value="o"></td>
  </tr>
  <tr>
   <td>RegisterId</td>
   <td><input type="text" id="subject" name="registerId" size="30"  value="HEEEEE"></td>
  </tr>
   <tr>
   <td>UpdaterId</td>
   <td><input type="text" id="sendPath" name="updaterId" size="30"  value="welcome"></td>
  </tr>
  


  
  <tr>
   <td colspan="2" style="text-align: center;"><input type="button" id="send" value="send"></td>
  </tr>
 </table>
</form>

</body>
</html>
