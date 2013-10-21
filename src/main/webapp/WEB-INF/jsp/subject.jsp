<html>
	<head>
		<title>Friendzone-Subject</title>
		<link rel="stylesheet" type="text/css" href="../../styles/styles.css" />

	</head>
	<body>
		<div id="header-wrapper">
			<img href="/mainHome/" src="../images/title.png" alt="Friendzone">
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
						<h3>Subject Description</h3>
						<p>${subject-description}</p>
						<h3>Project Description</h3>
						<p>${project-description}</p>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>