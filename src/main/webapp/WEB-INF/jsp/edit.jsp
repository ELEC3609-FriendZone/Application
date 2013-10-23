<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Friendzone-Profile</title>
<link rel="stylesheet" type="text/css" href="../../styles/styles.css" />
</head>
<body>
	<div id="header-wrapper">
		<img src="images/title.png" alt="Friendzone" href="/">
	</div>
	<div>
		<h1>Edit Profile Page</h1>
	</div>
	<form class="form" method="post" action="/profile/editComplete">
		<table align="center">
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
				<td><input type="text" name="secondaryEmail"></td>
			</tr>
			<tr>
				<td>Mobile:</td>
				<td><input type="text" name="mobile"></td>
			</tr>
			<tr>
				<td>Preferred Role:</td>
				<td><c:forEach items="${roles}" var="option">
						<input type="radio" name="preferredRole" value="${option}"> ${option}
					</c:forEach> 
				</td>
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
					</select>
					<input type="text" name="socialMedia2Value"> 
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
				<td colspan="2" rowspan="1" align="center"><input value="Change Profile Details" class="button-orange" type="submit"></td>
			</tr>
		</table>
	</form>
</body>
</html>
