
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
<form id="form" action="${pageContext.request.contextPath}/pmc/manager/list" method="post">

 <table> 
    <tr>
   <td>managerMode</td>
   <td><input type="text" id="sendPath" name="managerMode" size="30"  value="Y"></td>
  </tr>
      <tr>
   <td>managerId</td>
   <td><input type="text" id="sendPath" name="managerId" size="30"  value="ID"></td>
  </tr>
        <tr>
   <td>department</td>
   <td><input type="text" id="sendPath" name="department" size="30"  value="bLT"></td>
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
   <td colspan="2" style="text-align: center;"><input type="button" id="send" value="send"></td>
  </tr>
 </table>
</form>

</body>
</html>
