<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>Friendzone-Availability</title>
		<link rel="stylesheet" type="text/css" href="../../styles/styles.css" />
	</head>
	<body>
		<div id="header-wrapper">
			<img href="/mainHome" src="../images/title.png" alt="Friendzone">
		</div>
		<table>
			<tr>
				<td>
					<div class="subject-details">
						<h1>${unitcode}</h1>
						<i><h3>${unitname}</h3></i>
					</div>
				</td>
				<td></td>
			</tr>
			<tr>
				<td class="table-data-team">		
					<div class="team-member-details">
						<ul> 
							<li class="left-panel"><h4> Group Members </h4> <br> ${team} </li>
						</ul>
					</div>
				</td>
				<td>
					<div class="other-page-links">
						<a href="/subject/">Details</a> |
						<a href="/meeting/">New Meeting</a> |
						<a href="/meeting/">View Meeting</a> |
						<a href="/availability/">My Availability</a> |
						<a href="/subject/">Chat</a>
					</div>		
					<div class="page-body">
						<br>
						<ul>
						<li class="right-panel">
						<h2>Availability</h2>
						<form method="post" action="change/">
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
										<td>
											<c:choose>
												<c:when test="${aMonday=='true'}">
													<input type="checkbox" name="vehicle" value="${aMonday}" checked="true">
												</c:when>
												<c:otherwise>
													<input type="checkbox" name="vehicle" value="${aMonday}">
												</c:otherwise>
											</c:choose>
										</td>
										
									</c:forEach>
								</tr>
								<tr>
									<td class="table-heading">Tuesday</td>
									<c:forEach items="${availabilityTuesday}" var="aTuesday">
										<td>
											<c:choose>
												<c:when test="${aTuesday=='true'}">
													<input type="checkbox" name="vehicle" value="${aTuesday}" checked="true">
												</c:when>
												<c:otherwise>
													<input type="checkbox" name="vehicle" value="${aTuesday}">
												</c:otherwise>
											</c:choose>
										</td>
									</c:forEach>
								</tr>
								<tr>
									<td class="table-heading">Wednesday</td>
									<c:forEach items="${availabilityWednesday}" var="aWednesday">
										<td>
											<c:choose>
												<c:when test="${aWednesday=='true'}">
													<input type="checkbox" name="vehicle" value="${aWednesday}" checked="true">
												</c:when>
												<c:otherwise>
													<input type="checkbox" name="vehicle" value="${aWednesday}">
												</c:otherwise>
											</c:choose>
										</td>
									</c:forEach>
								</tr>
								<tr>
									<td class="table-heading">Thursday</td>
									<c:forEach items="${availabilityThursday}" var="aThursday">
										<td>
											<c:choose>
												<c:when test="${aThursday=='true'}">
													<input type="checkbox" name="vehicle" value="${aThursday}" checked="true">
												</c:when>
												<c:otherwise>
													<input type="checkbox" name="vehicle" value="${aThursday}">
												</c:otherwise>
											</c:choose>
										</td>
									</c:forEach>
								</tr>
								<tr>
									<td class="table-heading">Friday</td>
									<c:forEach items="${availabilityFriday}" var="aFriday">
										<td>
											<c:choose>
												<c:when test="${aFriday=='true'}">
													<input type="checkbox" name="vehicle" value="${aFriday}" checked="true">
												</c:when>
												<c:otherwise>
													<input type="checkbox" name="vehicle" value="${aFriday}">
												</c:otherwise>
											</c:choose>
										</td>
									</c:forEach>
								</tr>
								<tr>
									<td class="table-heading">Saturday</td>
									<c:forEach items="${availabilitySaturday}" var="aSaturday">
										<td>
											<c:choose>
												<c:when test="${aSaturday=='true'}">
													<input type="checkbox" name="vehicle" value="${aSaturday}" checked="true">
												</c:when>
												<c:otherwise>
													<input type="checkbox" name="vehicle" value="${aSaturday}">
												</c:otherwise>
											</c:choose>
										</td>
									</c:forEach>
								</tr>
								<tr>
									<td class="table-heading">Sunday</td>
									<c:forEach items="${availabilitySunday}" var="aSunday">
										<td>
											<c:choose>
												<c:when test="${aSunday=='true'}">
													<input type="checkbox" name="vehicle" value="${aSunday}" checked="true">
												</c:when>
												<c:otherwise>
													<input type="checkbox" name="vehicle" value="${aSunday}">
												</c:otherwise>
											</c:choose>
										</td>
									</c:forEach>
								</tr>
							</table>
							<p align="right"><input type="submit" value="Change" class="button-orange"/></p>
						</form>
						</li>
						</ul>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>