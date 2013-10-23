<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Friendzone-Availability</title>
<link rel="stylesheet" type="text/css" href="../../styles/styles.css">
</head>
<body>
	<div id="header-wrapper">
		<table style="width: 100%;">
			<tr>
				<td class="left"> <a href ="/mainHome/"><img src="../images/title.png" alt="Friendzone"></a></td>
				<td style="text-align: right;"> 
					<a href="/subject/">Details</a>  
					<a href="/meeting/">New Meeting</a> 
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
								<h4>Group Members</h4> <br> ${team}
							</li>
						</ul>
					</td>
					<td >
						<ul>
							<li class="right-panel">
								<h1>Meeting Page</h1>
								${studentTeams} <br>
								${meetingAttendees} <br>
								${teamMembers} <br>
								${meetingLocation}
								
							</li>
						</ul>
					</td>
				</tr>
			</tbody>
			</table>	
	</div>
</body>
</html>

