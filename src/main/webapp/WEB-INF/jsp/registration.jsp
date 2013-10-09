<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>	Friendzone - Registration </title>
	</head>
	<body>
		<h1> Worst looking registration form ever </h1>
		<form method="post" action="add/">
			First name:							<input type="text" name="firstName"> 
												<br>
			Last name:							<input type="text" name="lastName"> 
												<br>
			SID:								<input type="text" name="sid"> 
												<br>
			Unikey:								<input type="text" name="unikey"> 
												<br>
			Study level:						<c:forEach items="${studyLevels}" var="option">
													<input type="radio" name="studyLevel" value="${option}"> ${option}
												</c:forEach> 
												<br>
			Degree:								<input type="text" name="course">
												<br>
			Mobile:								<input type="text" name="mobile"> 
												<br>
			Primary email:						<input type="text" name="primaryEmail"> 
												<br>
			Secondary email:					<input type="text" name"secondaryEmail"> 
												<br>
			Languages:							<br> 
												<ul>
													<c:forEach items="${programmingLanguages}" var="language">
														<li>
															<input type="checkbox" name="languages" value="${language}"> ${language} 
															<br>
														</li>
													</c:forEach>
												</ul>
			Social Media 1:						<select name="socialMedia1Provider">
													<c:forEach items="${socialMediaProviders}" var="option">
			 												<option value="${option}"> ${option} </option>
													</c:forEach>
												</select>
												<input type="text" name="socialMedia1Value">
												<br>
			Social Media 2:						<select name="socialMedia2Provider">
													<c:forEach items="${socialMediaProviders}" var="option">
														<option value="${option}"> ${option} </option>
													</c:forEach>
												</select>
												<input type="text" name="socialMedia2Value"> <br>
			Is English your first language?		<input type="radio" name="esl" value="false"> Yes
												<input type="radio" name="esl" value="true"> No
												<br>
			Experience:			<br>
								<textarea rows="4" cols="50" name="experience">Enter a brief description of your work and project experience (up to 200 characters).</textarea>
								<br>
			
            <input type="submit" value="Submit registration" class="btn"/>
			
		</form>	
	</body>
</html>