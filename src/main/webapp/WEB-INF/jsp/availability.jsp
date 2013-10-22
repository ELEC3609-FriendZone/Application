<html>
<<<<<<< HEAD
<head>
<title>Friendzone-Availability</title>
<link rel="stylesheet" type="text/css" href="../../styles/styles.css">
</head>
<body>
	<div id="header-wrapper">
		<img href="/mainHome" src="../images/title.png" alt="Friendzone">
	</div>
	<div id="left" class="Subject Description">
		<h1>${unitcode}</h1>
		<h3>${unitname}</h3>
	</div>
	<div id="center" class="page-content">
		<table>
			<tbody>
				<tr>
					<td><br></td>
					<td style="vertical-align: bottom; text-align: right;">
						<div class="other-page-links">
							<div style="text-align: right;">
								<a href="/subject/">Details</a> | <a href="/meeting/">New
									Meeting</a> | <a href="/meeting/">View Meeting</a> | <a
									href="/availability/">My Availability</a> | <a href="/subject/">Chat</a>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="vertical-align: top;">
						<ul>
							<li class="left-panel">
								<h4>Group Members</h4> <br> ${team}
							</li>
						</ul>
					</td>
					<td style="vertical-align: top;">
						<ul>
							<li class="right-panel">
								<h4>Availability</h4>
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
										<!-- td>
											<c:choose>
												<c:when test="${aMonday=='true'}">
													<input type="checkbox" name="vehicle" value="${aMonday}" checked="true">
												</c:when>
												<c:otherwise>
													<input type="checkbox" name="vehicle" value="${aMonday}">
												</c:otherwise>
											</c:choose>
										</td-->
										<c:choose>
											<c:when test="${aMonday=='true'}">
												<td class="table-available"></td>
											</c:when>
											<c:otherwise>
												<td class="table-unavailable"></td>
											</c:otherwise>
										</c:choose>
										
									</c:forEach>
								</tr>
								<tr>
									<td class="table-heading">Tuesday</td>
									<c:forEach items="${availabilityTuesday}" var="aTuesday">
										<!-- td>
											<c:choose>
												<c:when test="${aTuesday=='true'}">
													<input type="checkbox" name="vehicle" value="${aTuesday}" checked="true">
												</c:when>
												<c:otherwise>
													<input type="checkbox" name="vehicle" value="${aTuesday}">
												</c:otherwise>
											</c:choose>
										</td-->
										<c:choose>
											<c:when test="${aTuesday=='true'}">
												<td class="table-available"></td>
											</c:when>
											<c:otherwise>
												<td class="table-unavailable"></td>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</tr>
								<tr>
									<td class="table-heading">Wednesday</td>
									<c:forEach items="${availabilityWednesday}" var="aWednesday">
										<!-- td>
											<c:choose>
												<c:when test="${aWednesday=='true'}">
													<input type="checkbox" name="vehicle" value="${aWednesday}" checked="true">
												</c:when>
												<c:otherwise>
													<input type="checkbox" name="vehicle" value="${aWednesday}">
												</c:otherwise>
											</c:choose>
										</td-->
										<c:choose>
											<c:when test="${aWednesday=='true'}">
												<td class="table-available"></td>
											</c:when>
											<c:otherwise>
												<td class="table-unavailable"></td>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</tr>
								<tr>
									<td class="table-heading">Thursday</td>
									<c:forEach items="${availabilityThursday}" var="aThursday">
										<!-- td>
											<c:choose>
												<c:when test="${aThursday=='true'}">
													<input type="checkbox" name="vehicle" value="${aThursday}" checked="true">
												</c:when>
												<c:otherwise>
													<input type="checkbox" name="vehicle" value="${aThursday}">
												</c:otherwise>
											</c:choose>
										</td-->
										<c:choose>
											<c:when test="${aThursday=='true'}">
												<td class="table-available"></td>
											</c:when>
											<c:otherwise>
												<td class="table-unavailable"></td>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</tr>
								<tr>
									<td class="table-heading">Friday</td>
									<c:forEach items="${availabilityFriday}" var="aFriday">
										<!-- td>
											<c:choose>
												<c:when test="${aFriday=='true'}">
													<input type="checkbox" name="vehicle" value="${aFriday}" checked="true">
												</c:when>
												<c:otherwise>
													<input type="checkbox" name="vehicle" value="${aFriday}">
												</c:otherwise>
											</c:choose>
										</td-->
										<c:choose>
											<c:when test="${aFriday=='true'}">
												<td class="table-available"></td>
											</c:when>
											<c:otherwise>
												<td class="table-unavailable"></td>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</tr>
								<tr>
									<td class="table-heading">Saturday</td>
									<c:forEach items="${availabilitySaturday}" var="aSaturday">
										<!--td>
											<c:choose>
												<c:when test="${aSaturday=='true'}">
													<input type="checkbox" name="vehicle" value="${aSaturday}" checked="true">
												</c:when>
												<c:otherwise>
													<input type="checkbox" name="vehicle" value="${aSaturday}">
												</c:otherwise>
											</c:choose>
										</td-->
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
										<!--td>
											<c:choose>
												<c:when test="${aSunday=='true'}">
													<input type="checkbox" name="vehicle" value="${aSunday}" checked="true">
												</c:when>
												<c:otherwise>
													<input type="checkbox" name="vehicle" value="${aSunday}">
												</c:otherwise>
											</c:choose>
										</td-->
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
							<p> Tick the boxes below to edit your availability </p>
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
								    	<td><input type=checkbox name="avail${i}${j}"></td>
								     </c:forEach>
								   </tr>
								</c:forEach>
							</table>
							<p align="right"><input type="submit" value="Change Availability" class="btn"/></p>
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

