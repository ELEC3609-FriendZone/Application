<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
						<h3>Availability</h3>
						
						<table>
							<tr>
								<td>Day</td>
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
								<td>8pm</td>
							</tr>
							<tr>
								<td>Monday</td>
								<c:forEach items="${availabilityMonday}" var="aMonday">
									<td>
										<c:if test="${aMonday=='true'}">
										 X
										</c:if>
									</td>
									
								</c:forEach>
							</tr>
							<tr>
								<td>Tuesday</td>
								<c:forEach items="${availabilityTuesday}" var="aTuesday">
									<td>
										<c:if test="${aTuesday=='true'}">
										 X
										</c:if>
									</td>
								</c:forEach>
							</tr>
							<tr>
								<td>Wednesday</td>
								<c:forEach items="${availabilityWednesday}" var="aWednesday">
									<td>
										<c:if test="${aWednesday=='true'}">
										 X
										</c:if>
									</td>
								</c:forEach>
							</tr>
							<tr>
								<td>Thursday</td>
								<c:forEach items="${availabilityThursday}" var="aThursday">
									<td>
										<c:if test="${aThursday=='true'}">
										 X
										</c:if>
									</td>
								</c:forEach>
							</tr>
							<tr>
								<td>Friday</td>
								<c:forEach items="${availabilityFriday}" var="aFriday">
									<td>
										<c:if test="${aFriday=='true'}">
										 X
										</c:if>
									</td>
								</c:forEach>
							</tr>
							<tr>
								<td>Saturday</td>
								<c:forEach items="${availabilitySaturday}" var="aSaturday">
									<td>
										<c:if test="${aSaturday=='true'}">
										 X
										</c:if>
									</td>
								</c:forEach>
							</tr>
							<tr>
								<td>Sunday</td>
								<c:forEach items="${availabilitySunday}" var="aSunday">
									<td>
										<c:if test="${aSunday=='true'}">
										 X
										</c:if>
									</td>
								</c:forEach>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>