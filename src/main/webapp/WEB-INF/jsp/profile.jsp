<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Friendzone-Profile</title>
<link rel="stylesheet" type="text/css" href="../../styles/styles.css" />
</head>
<body>
	<div id="header-wrapper">
		<table style="width: 100%;">
			<tr>
				<td class="left"><a href="/mainHome/"><img
						src="../images/title.png" alt="Friendzone"></a></td>
			</tr>
		</table>
	</div>
	<div class="left">
		<h1>Profile</h1>
		<h3>${firstName} ${lastName}</h3>
	</div>
	<div id="page-content">
		<table>
			<tbody>
				<tr>
					<td style="vertical-align: top">
						<ul>
							<li class="left-panel">
								<h4>Details</h4> <br>
							<b>Unikey:</b> ${unikey} <br>
							<b>SID:</b> ${SID} <br>
							<b>Email:</b> ${primaryEmail} <br>
							<b>Mobile:</b> ${mobile}
							</li>
						</ul>
					</td>
					<td style="vertical-align: top">
						<ul class="left">
							<li class="right-panel">
								<h4>Preferences</h4>
								<p><h5>Study Levels:</h5>
								<ul>${studyLevels}
								</ul>
								
								<p><h5>Role</h5>
								<ul>${preferredRole}
								</ul>
								<p><h5>Languages</h5>
								<ul>
									<c:forEach items="${languages}" var="language">
										<li>${language}</li>
									</c:forEach>
								</ul>
								<p> <h5>Social Media</h5>
								<ul>
									<li>${firstSocialMedia}</li>
									<li>${secondSocialMedia}</li>
								</ul>
							</li>
						</ul>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>
