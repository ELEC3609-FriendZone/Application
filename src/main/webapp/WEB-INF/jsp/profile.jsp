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
		<h1>Profile Page</h1>
	</div>
	<table>
		<tr>
			<td>
				<div class="user-details">
					<h2>Profile</h2>
					<i><h3>${firstName} ${lastName}</h3></i>
				</div>
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="details">
				<div class="links">
					<a href="/profile/">edit</a>
				</div>
				<div class="profile-details">
					<h3>Details</h3>
					<table>
						<tr>
							<td><b>Unikey:</b></td>
							<td>${unikey}</td>
						</tr>
						<tr>
							<td><b>SID:</b></td>
							<td>${SID}</td>
						</tr>
						<tr>
							<td><b>Email:</b></td>
							<td>${primaryEmail}</td>
						</tr>
						<tr>
							<td><b>Mobile:</b></td>
							<td>${mobile}</td>
						</tr>
					</table>
				</div>
			</td>
			<td>
				<div class="links">
					<a href="/profile/">edit</a>
				</div>
				<div class="page-body">
					<h2>Preferences</h2>
					<h3>Study Levels</h3>
					<ul>${studyLevels}
					</ul>
					<h3>Role</h3>
					<ul>${preferredRole}
					</ul>
					<h3>Languages</h3>
					<ul>
						<c:forEach items="${languages}" var="language">
							<li>${language}</li>
						</c:forEach>
					</ul>
					<h3>Social Media</h3>
					<ul>
						<li>${firstSocialMedia}</li>
						<li>${secondSocialMedia}</li>
					</ul>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>
