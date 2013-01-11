<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%String error = request.getParameter("error");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<body>
        <P>
            <FONT color="red">${error}</FONT>
        </P>
        <FORM action="" method="POST">
        	<table>
			    <tr>
			    	<td>User:</td>
			    	<td><input type="text" name="username"></td>
			   	</tr>
			    <tr>
			    	<td>Password:</td>
			    	<td><input type="password" name="password"/></td>
			    </tr>
			    <tr>
			    	<td><input type="checkbox" name="rememberMe"/></td>
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
    	document.getElementById('username').focus();
    </script>
</html>