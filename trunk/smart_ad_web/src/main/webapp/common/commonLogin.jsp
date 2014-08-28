<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">

(function() {
	if(user.userNo == "" || user.userNo == null) {
		if(confirm(msg.needLogin)) {
			goPage('${contextPath}/member/login.do');
		} else {
			goBack();
		}
	}
})();
</script>
