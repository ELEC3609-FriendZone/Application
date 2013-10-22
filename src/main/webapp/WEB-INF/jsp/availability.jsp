<html>
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
										<tbody>
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
												</c:forEach>
												<td><c:choose>
														<c:when test="${aMonday=='true'}">
															<input name="vehicle" value="${aMonday}" checked="true"
																type="checkbox">
														</c:when>
														<c:otherwise>
															<input name="vehicle" value="${aMonday}" type="checkbox">
														</c:otherwise>
													</c:choose></td>
											</tr>
											<tr>
												<td class="table-heading">Tuesday</td>
												<c:forEach items="${availabilityTuesday}" var="aTuesday">
												</c:forEach>
												<td><c:choose>
														<c:when test="${aTuesday=='true'}">
															<input name="vehicle" value="${aTuesday}" checked="true"
																type="checkbox">
														</c:when>
														<c:otherwise>
															<input name="vehicle" value="${aTuesday}" type="checkbox">
														</c:otherwise>
													</c:choose></td>
											</tr>
											<tr>
												<td class="table-heading">Wednesday</td>
												<c:forEach items="${availabilityWednesday}" var="aWednesday">
												</c:forEach>
												<td><c:choose>
														<c:when test="${aWednesday=='true'}">
															<input name="vehicle" value="${aWednesday}"
																checked="true" type="checkbox">
														</c:when>
														<c:otherwise>
															<input name="vehicle" value="${aWednesday}"
																type="checkbox">
														</c:otherwise>
													</c:choose></td>
											</tr>
											<tr>
												<td class="table-heading">Thursday</td>
												<c:forEach items="${availabilityThursday}" var="aThursday">
												</c:forEach>
												<td><c:choose>
														<c:when test="${aThursday=='true'}">
															<input name="vehicle" value="${aThursday}" checked="true"
																type="checkbox">
														</c:when>
														<c:otherwise>
															<input name="vehicle" value="${aThursday}"
																type="checkbox">
														</c:otherwise>
													</c:choose></td>
											</tr>
											<tr>
												<td class="table-heading">Friday</td>
												<c:forEach items="${availabilityFriday}" var="aFriday">
												</c:forEach>
												<td><c:choose>
														<c:when test="${aFriday=='true'}">
															<input name="vehicle" value="${aFriday}" checked="true"
																type="checkbox">
														</c:when>
														<c:otherwise>
															<input name="vehicle" value="${aFriday}" type="checkbox">
														</c:otherwise>
													</c:choose></td>
											</tr>
											<tr>
												<td class="table-heading">Saturday</td>
												<c:forEach items="${availabilitySaturday}" var="aSaturday">
												</c:forEach>
												<td><c:choose>
														<c:when test="${aSaturday=='true'}">
															<input name="vehicle" value="${aSaturday}" checked="true"
																type="checkbox">
														</c:when>
														<c:otherwise>
															<input name="vehicle" value="${aSaturday}"
																type="checkbox">
														</c:otherwise>
													</c:choose></td>
											</tr>
											<tr>
												<td class="table-heading">Sunday</td>
												<c:forEach items="${availabilitySunday}" var="aSunday">
												</c:forEach>
												<td><c:choose>
														<c:when test="${aSunday=='true'}">
															<input name="vehicle" value="${aSunday}" checked="true"
																type="checkbox">
														</c:when>
														<c:otherwise>
															<input name="vehicle" value="${aSunday}" type="checkbox">
														</c:otherwise>
													</c:choose></td>
											</tr>
										</tbody>
									</table>
									<p align="right">
										<input value="Change" class="button-orange" type="submit">
									</p>
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

