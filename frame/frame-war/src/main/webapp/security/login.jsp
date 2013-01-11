<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="Pragma" content="no-cache">
		<meta http-equiv="Cache-Control" content="no-cache">
		<meta http-equiv="Expires" content="-1">
		<link rel="stylesheet" type="text/css" href="../resources/css/reset.css" />
	</head>
	<body>
        <P>
            <FONT color="red">${param.error}</FONT>
        </P>
		<!-- org.springframework.security.web.WebAttributes.AUTHENTICATION_EXCEPTION -->
        <h1>${requestScope.SPRING_SECURITY_LAST_EXCEPTION}</h1>
        <h1>${sessionScope.SPRING_SECURITY_LAST_EXCEPTION}</h1>
        <FORM action="${pageContext.request.contextPath }/security/j_spring_security_check" method="POST">
        	<table>
			    <tr>
			    	<td>User:</td>
			    	<td><input type="text" name="j_username"></td>
			   	</tr>
			    <tr>
			    	<td>Password:</td>
			    	<td><input type="password" name="j_password"/></td>
			    </tr>
			    <tr>
			    	<td><input type="checkbox" name="_spring_security_remember_me"/></td>
			    	<td>Remember me on this computer.</td>
			    </tr>
			    <tr>
			    	<td><input name="submit" type="submit"/></td>
			    	<td><input name="reset" type="reset"/></td>
			    </tr>
		  	</table>
        </FORM>
    </body>
    <script type="text/javascript">
    	document.getElementsByName('j_username')[0].focus();
    </script>
</html>