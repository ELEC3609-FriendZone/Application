<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>Friendzone-Availability</title>
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
				margin-left:2%;
				margin-right:10%;
			}
			.team-member-details {
				background: rgba(260,260, 260, 1);
				margin-top:1%;
				margin-left:10%;
				margin-right:2%;
			}
			.other-page-links {
				text-align: right;
				margin-right:10%;
			}
			table{
				width:100%;
			}
			.subject-details {
				margin-top:1%;
				margin-left:10%;
			}
			.table-data-team{
				vertical-align:top;
				width:20%;
			}
			.table-heading{
				background: rgba(102, 153, 204, 1);
				text-align:center;
				color: rgba(260,260, 260, 1);
			}

		</style>
	</head>
	<body>
		<div class="page-header">
			<a href="/mainHome/"class="appTitle"><h1>FriendZone</h1></a>
		</div>
		<table>
			<tr>
				<td>
					<div class="subject-details">
						<h2>${unitcode}</h2>
						<i><h3>${unitname}</h3></i>
					</div>
				</td>
				<td></td>
			</tr>
			<tr>
				<td class="table-data-team">		
					<div class="team-member-details">
						<h3>Team members</h3>
						${team}
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
							<p align="right"><input type="submit" value="Change" class="btn"/></p>
						</form>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>