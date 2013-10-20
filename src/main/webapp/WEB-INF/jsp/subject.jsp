<html>
	<head>
		<title>Friendzone-Subject</title>
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
						$team
					</div>
				</td>
				<td>
					<div class="other-page-links">
						<a href="/subject/">Details</a>
						<a href="/meeting/">New Meeting</a> |
						<a href="/meeting/">View Meeting</a> |
						<a href="/availability/">My Availability</a> |
						<a href="/subject/">Chat</a>
					</div>		
					<div class="page-body">
						<h3>Subject Description</h3>
						<p>$subject-description</p>
						<h3>Project Description</h3>
						<p>$project-description</p>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>