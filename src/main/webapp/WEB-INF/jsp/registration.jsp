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
			<a href="index.jsp"class="appTitle"><h1>FriendZone</h1></a>
		</div>
		<div class="page-body">
			<h1>Registration Form </h1>
			<form method="post" action="add/">
				<table class="formTable" align="center">
					<tr>
						<td>First name:</td>
						<td><input type="text" name="firstName"> </td>
					</tr>
					<tr>
						<td>Last name:</td>
						<td><input type="text" name="lastName"> </td>
					</tr>
					<tr>
						<td>SID:</td>
						<td><input type="text" name="sid"> </td>
					</tr>
					<tr>
						<td>Unikey:</td>
						<td><input type="text" name="unikey"> </td>
					</tr>
					<tr>
						<td>Study level:</td>
						<td><c:forEach items="${studyLevels}" var="option">
								<input type="radio" name="studyLevel" value="${option}"> ${option}
							</c:forEach> 
						</td>
					</tr>
					<tr>
						<td>Degree:</td>
						<td><input type="text" name="course"></td>
					</tr>
					<tr>
						<td>Primary email:</td>
						<td><input type="text" name="primaryEmail"></td>
					</tr>
					<tr>
						<td>Secondary email:</td>
						<td><input type="text" name"secondaryEmail"></td>
					</tr>
					<tr>
						<td>Languages:</td>
						<td><ul>
								<c:forEach items="${programmingLanguages}" var="language">
									<li>
										<input type="checkbox" name="languages" value="${language}"> ${language} 
										<br>
									</li>
								</c:forEach>
							</ul>
						</td>
					</tr>
					<tr>
						<td>Social Media 1:</td>
						<td><select name="socialMedia1Provider">
								<c:forEach items="${socialMediaProviders}" var="option">
									<option value="${option}"> ${option} </option>
								</c:forEach>
							</select>
							<input type="text" name="socialMedia1Value">
						</td>
					</tr>
					<tr>
						<td>Social Media 2:</td>
						<td><select name="socialMedia2Provider">
								<c:forEach items="${socialMediaProviders}" var="option">
									<option value="${option}"> ${option} </option>
								</c:forEach>
								<input type="text" name="socialMedia2Value"> 
							</select>
						</td>
					</tr>
					<tr>
						<td>Is English your first language?</td>
						<td><input type="radio" name="esl" value="false"> Yes
							<input type="radio" name="esl" value="true"> No
						</td>
					</tr>
					<tr>
						<td>Experience:</td>
						<td><textarea rows="4" cols="50" name="experience">Enter a brief description of your work and project experience (up to 200 characters).</textarea> </td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Submit registration" class="btn"/></td>
					</tr>
				</table>
			</form>	
		</div>
	</body>
</html>