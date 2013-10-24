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
				<td style="text-align: right;"> 
					<div class="links">
						<a href="/profile/edit/">Edit</a>
					</div>
				</td>
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
						<ul class="left">
							<li class="left-panel">
								<h4>Details</h4>
								<table id="detail-table">
									<tr>
										<td><b>Unikey: </b></td>
										<td>${unikey}</td>
									</tr>
									<tr>
										<td><b>SID:</b></td>
										<td>${SID}</td>
									</tr>
									<tr>
										<td><b>Email: </b></td>
										<td>${primaryEmail}</td>
									</tr>
									<tr>
										<td><b>Mobile: </b></td>
										<td>${mobile}</td>
									</tr>
								</table>
							</li>
						</ul>
					</td>
					<td style="vertical-align: top">
						<ul class="left">
							<li class="right-panel">
								<h1>Preferences</h1>
								<table id="preferences-table">
									<tr>
										<td><h5>Study Level:</h5></td>
										<td>
											<ul>${studyLevels}</ul>
										</td>
									</tr>
									<tr>
										<td><h5>Role:</h5></td>
										<td>
											<ul>${preferredRole}</ul>
										</td>
									</tr>
									<tr>
										<td><h5>Languages</h5></td>
										<td>
											<ul>
												<c:forEach items="${languages}" var="language">
													<li>${language}</li>
												</c:forEach>
											</ul>
										</td>
									</tr>
									<tr>
										<td><h5>Social Media</h5></td>
										<td>
											<ul>
												<li>${firstSocialMedia}</li>
												<li>${secondSocialMedia}</li>
											</ul>
										</td>
									</tr>
									<tr>
										<td><h5>English as a second Language?</h5></td>
										<td>
											<ul>
												<c:choose>
													<c:when test="${ESL == true}"> Yes </c:when>
													<c:otherwise> No </c:otherwise>
												</c:choose>
											</ul>
										</td>
									</tr>
									<tr>
										<td><h5>Experience</h5></td>
										<td>
											<ul>${experience}</ul>
										</td>
									</tr>
								</table>
							</li>
						</ul>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>
