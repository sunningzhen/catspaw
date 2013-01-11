<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Access Denied</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="-1">
<link rel="stylesheet" type="text/css" href="../resources/css/reset.css" />
</head>
<body>
<h1>Access Denied</h1>
<!-- org.springframework.security.web.WebAttributes.ACCESS_DENIED_403 -->
${requestScope.SPRING_SECURITY_403_EXCEPTION}
</body>
</html>