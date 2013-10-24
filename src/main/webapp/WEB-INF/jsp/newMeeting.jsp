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
					<a href="/availability"> My Availability</a>
					<a href="/meeting/">View Meetings</a> 
					<a href="/meeting/new/">New Meeting</a>  
					<a href="/subject/">Chat</a>	
					<a href="/profile/" class="button-small1"> ${Firstname} ${Lastname}</a>
					<a href="/logout/" class="button-small2"> Log out</a>
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
								<h2>Member Availabilities:</h2>
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
														<c:when test="${aMonday != '0'}">
															<td title="${aMonday}" class="table-unavailable">${aMonday}</td>
														</c:when>
														<c:otherwise><td></td></c:otherwise>
													</c:choose>
													
												</c:forEach>
											</tr>
											<tr>
												<td class="table-heading">Tuesday</td>
												<c:forEach items="${availabilityTuesday}" var="aTuesday">
													<c:choose>
														<c:when test="${aTuesday != '0'}">
															<td class="table-unavailable">${aTuesday}</td>
														</c:when>
														<c:otherwise><td></td></c:otherwise>
													</c:choose>
													
												</c:forEach>
											</tr>
											<tr>
												<td class="table-heading">Wednesday</td>
												<c:forEach items="${availabilityWednesday}" var="aWednesday">
													<c:choose>
														<c:when test="${aWednesday != '0'}">
															<td class="table-unavailable">${aWednesday}</td>
														</c:when>
														<c:otherwise><td></td></c:otherwise>
													</c:choose>
												</c:forEach>
											</tr>
											<tr>
												<td class="table-heading">Thursday</td>
												<c:forEach items="${availabilityThursday}" var="aThursday">
													<c:choose>
														<c:when test="${aThursday != '0'}">
															<td class="table-unavailable">${aThursday}</td>
														</c:when>
														<c:otherwise><td></td></c:otherwise>
													</c:choose>
												</c:forEach>
											</tr>
											<tr>
												<td class="table-heading">Friday</td>
												<c:forEach items="${availabilityFriday}" var="aFriday">
													<c:choose>
														<c:when test="${aFriday != '0'}">
															<td class="table-unavailable">${aFriday}</td>
														</c:when>
														<c:otherwise><td></td></c:otherwise>
													</c:choose>
												</c:forEach>
											</tr>
										</table>
										<br>
										The Maximum number of members that have a possible meeting time together is: ${maxCount}/${teamSize}
										<br>
										Available Team members: ${availMembers}
										<br>
									</ul>
								</c:forEach>
								<br>
								<h2>Create Meeting</h2>
								Choose Team:
								<select name="teamChosen">
									<c:forEach items="${teamNames}" var="option">
										<option value="${option}"> ${option} </option>
									</c:forEach>
								</select>
								<br>
								Choose Time:
								<form method="post" action="/meeting/create">
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
										<c:forEach var="i" begin="0" end="6">
											<tr>
												<c:if test="${i==0}">
											 		<td class="table-heading">Monday</td>
											 	</c:if>
											 	<c:if test="${i==1}">
											 		<td class="table-heading">Tuesday</td>
											 	</c:if>
											 	<c:if test="${i==2}">
											 		<td class="table-heading">Wednesday</td>
											 	</c:if>
											 	<c:if test="${i==3}">
											 		<td class="table-heading">Thursday</td>
											 	</c:if>
											 	<c:if test="${i==4}">
											 		<td class="table-heading">Friday</td>
											 	</c:if>
											 	<c:if test="${i==5}">
											 		<td class="table-heading">Saturday</td>
											 	</c:if>
											 	<c:if test="${i==6}">
											 		<td class="table-heading">Sunday</td>
											 	</c:if>
										    <c:forEach var="j" begin="0" end="11">
										    	<td><!-- input type="radio" name="avail${i}${j}" value="avail${i}${j}"-->
										    	<input type="radio" name="studyLevel" value="avail${i}${j}"></td>
										     </c:forEach>
										   </tr>
										</c:forEach>
									</table>
									<p align="right"><input type="submit" value="Create Meeting" class="button-orange"/></p>
								</form>
							</li>
						</ul>
					</td>
				</tr>
			</tbody>
			</table>	
	</div>
</body>
</html>

