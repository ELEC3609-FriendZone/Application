<html>
	<head>
		<title>Friendzone-Subject</title>
		<link rel="stylesheet" type="text/css" href="../../styles/styles.css" />

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
					<div class="page-body">
						<h3>Subject Description</h3>
						<p>${subject-description}</p>
						<h3>Project Description</h3>
						<p>${project-description}</p>
					</div>
				</td>
			</tr>
		</table>
			<!-- Footer -->
	<div>
		<jsp:include page="footer.jsp" />
	</div>
	</body>
</html>