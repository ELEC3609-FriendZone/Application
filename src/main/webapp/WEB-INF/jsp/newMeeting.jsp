<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Friendzone-new Meeting</title>
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
					<a href="/meeting/">View Meeting</a> 
					<a href=""> My Availability</a> 
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
					<td >
						<ul>
							<li class="right-panel">
								<h1>Create a New Meeting</h1>
								<c:forEach items="${teamNames}" var="tname">
									<ul>
										<li>${tname}</li>
										<table>
											<tr class="table-heading">
												<td>Day</td>
												<td>8am</td>
												<td>9am</td>
												<td>10am</td>
												<td>11am</td>
												<td>12pm</td>
												<td>1pm</td>
												<td>2pm</td>
												<td>3pm</td>
												<td>4pm</td>
												<td>5pm</td>
												<td>6pm</td>
												<td>7pm</td>
											</tr>
											<tr>
												<td class="table-heading">Monday</td>
												<c:forEach items="${availabilityMonday}" var="aMonday">
													<c:choose>
														<c:when test="${aMonday=='true'}">
															<td class="table-available"></td>
														</c:when>
														<c:otherwise>
															<td class="table-unavailable">${aMonday}</td>
														</c:otherwise>
													</c:choose>
													
												</c:forEach>
											</tr>
											<tr>
												<td class="table-heading">Tuesday</td>
												<c:forEach items="${availabilityTuesday}" var="aTuesday">
													<c:choose>
														<c:when test="${aTuesday=='true'}">
															<td class="table-available"></td>
														</c:when>
														<c:otherwise>
															<td class="table-unavailable">${aTuesday}</td>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</tr>
											<tr>
												<td class="table-heading">Wednesday</td>
												<c:forEach items="${availabilityWednesday}" var="aWednesday">
													</td>
													<c:choose>
														<c:when test="${aWednesday=='true'}">
															<td class="table-available"></td>
														</c:when>
														<c:otherwise>
															<td class="table-unavailable">${aWednesday}</td>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</tr>
											<tr>
												<td class="table-heading">Thursday</td>
												<c:forEach items="${availabilityThursday}" var="aThursday">
													<c:choose>
														<c:when test="${aThursday=='true'}">
															<td class="table-available"></td>
														</c:when>
														<c:otherwise>
															<td class="table-unavailable">${aThursday}</td>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</tr>
											<tr>
												<td class="table-heading">Friday</td>
												<c:forEach items="${availabilityFriday}" var="aFriday">
													<c:choose>
														<c:when test="${aFriday=='true'}">
															<td class="table-available"></td>
														</c:when>
														<c:otherwise>
															<td class="table-unavailable">${aFriday}</td>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</tr>
											<tr>
												<td class="table-heading">Saturday</td>
												<c:forEach items="${availabilitySaturday}" var="aSaturday">
													<c:choose>
														<c:when test="${aSaturday=='true'}">
															<td class="table-available"></td>
														</c:when>
														<c:otherwise>
															<td class="table-unavailable"></td>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</tr>
											<tr>
												<td class="table-heading">Sunday</td>
												<c:forEach items="${availabilitySunday}" var="aSunday">
													<c:choose>
														<c:when test="${aSunday=='true'}">
															<td class="table-available"></td>
														</c:when>
														<c:otherwise>
															<td class="table-unavailable"></td>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</tr>
										</table>
									</ul>
								</c:forEach>
							</li>
						</ul>
					</td>
				</tr>
			</tbody>
			</table>	
	</div>
</body>
</html>

