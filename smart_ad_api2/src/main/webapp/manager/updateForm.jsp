
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
<form id="form" action="${pageContext.request.contextPath}/pmc/manager/update" method="post">

 <table> 
    <tr>
   <td>managerMode</td>
   <td><input type="text" id="sendPath" name="managerMode" size="30"  value="Y"></td>
  </tr>
      <tr>
   <td>managerId</td>
   <td><input type="text" id="sendPath" name="managerNo" size="30"  value="MG0000000084"></td>
  </tr>
      <tr>
   <td>managerPassword</td>
   <td><input type="text" id="sendPath" name="managerPassword" size="30"  value="1234"></td>
  </tr>
      <tr>
   <td>managerEmail</td>
   <td><input type="text" id="sendPath" name="managerEmail" size="30"  value="Y"></td>
  </tr>
      <tr>
   <td>managerName</td>
   <td><input type="text" id="sendPath" name="managerName" size="30"  value="minsu"></td>
  </tr>
      <tr>
   <td>department</td>
   <td><input type="text" id="sendPath" name="department" size="30"  value="tera"></td>
  </tr>
       <tr>
   <td>position</td>
   <td><input type="text" id="sendPath" name="position" size="30"  value="사원"></td>
  </tr>
  <tr>
   <td colspan="2" style="text-align: center;"><input type="button" id="send" value="send"></td>
  </tr>
 </table>
</form>

</body>
</html>
