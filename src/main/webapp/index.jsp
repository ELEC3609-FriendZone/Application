<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>	Friendzone - Registration </title>
		<style type="text/css">
			body {
				margin-top: 0px;
				margin-right: 0px;
				margin-bottom: 0px;
				margin-left: 0px;
				background: rgba(238, 236, 225, 1);
			}
			
			.page-header {
				FONT-FAMILY: 'Times New Roman';
				TEXT-TRANSFORM: capitalize;
				background: rgba(102, 153, 204, 1);
				height: 10%;
				width: 100%;
			}
			
			h1{
				text-align:center;
			}
			
			.appTitle{
				color: rgba(0, 0, 0, 1);
			}
	
			.page-body {
				background: rgba(260,260, 260, 1);
				margin-top:1%;
				margin-left:10%;
				margin-right:10%;
			}
			
			.formTable{
			
			}
		</style>
	</head>
	<body>
		<div class="page-header">
			<a href="/"class="appTitle"><h1>FriendZone</h1></a>
		</div>
		<div class="page-body">
			<form name="login" method="get" action="/people/">
				<table class="formTable" align="center">
					<tr>
						<td><h1>Login</h1></td>
					</tr>
					<tr>
						<td>Username: </td>
						<td><input type="text" name="username"> </td>
					</tr>
					<tr>
						<td> Password: </td>
						<td><input type="password" name="password"></td>
					</tr>
					<tr>
						<td><input type="submit" value="Login" class="btn"/></td>
					</tr>
					<tr>
						<td> Not a member? </td>
						<td><a href="/registration/"class="appTitle"> Register</a> now </td>
					</tr>
				</table>
			</form>	
		</div>
	</body>
</html>