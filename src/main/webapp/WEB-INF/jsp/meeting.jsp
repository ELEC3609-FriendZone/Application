<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Friendzone-Availability</title>
<link rel="stylesheet" type="text/css" href="../../styles/styles.css">
</head>
<body>
	<div id="header-wrapper">
		<table style="width: 100%;">
			<tr>
				<td class="left"> <a href ="/mainHome/"><img src="../images/title.png" alt="Friendzone"></a></td>
				<td style="text-align: right;"> 
					<a href="/subject/">Details</a>  
					<a href="/meeting/new">New Meeting</a> 
					<a href="">View Meeting</a> 
					<a href="/availability"> My Availability</a> 
					<a href="/subject/">Chat</a>
				</td>
			</tr>
		</table>
	</div>
	<div id="left" class="Subject Description">
		<h1>${unitcode}</h1>
		<h3>${unitname}</h3>
	</div>
	<div id="page-content">
		<table>
			<tbody>
				<tr>
					<td style="vertical-align: top">
						<ul>
							<li class="left-panel">
								<h4>Group Members</h4> 
								<br> TeamID:${teamID}
								<br> Team Name: ${teamName}
							</li>
						</ul>
					</td>
					<td style="vertical-align: top">
						<ul>
							<li class="right-panel">
								<h1>Group Meetings</h1>
								Time stamp format: YYYY-MM-DD HH:MM:SS.nanoseconds
								<table>
									<tr>
										<td>ID:</td>
										<c:forEach items="${meetingID}" var="id">
											<td>${id}</td>
										</c:forEach>
										
									</tr>
									<tr>
										<td>Start:</td>
										<c:forEach items="${meetingStart}" var="start">
											<td>${start}</td>
										</c:forEach>
									</tr>
									<tr>
										<td>End:</td>
										<c:forEach items="${meetingEnd}" var="end">
											<td>${end}</td>
										</c:forEach>
									</tr>
									<tr>
										<td>Location:</td>
										<c:forEach items="${meetingLocation}" var="l">
											
											<c:choose>
												<c:when test="${l== null}">
													<td>Not Specified</td>
												</c:when>
												<c:otherwise>
													<td>${l}</td>
												</c:otherwise>
											</c:choose>
										</c:forEach>
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

