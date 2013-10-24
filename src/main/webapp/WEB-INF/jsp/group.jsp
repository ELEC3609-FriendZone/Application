<html>
	<head>
		<title>Friendzone-Group</title>
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
		<div id="left">
			<h1>Subject</h1>
			<h3> Subject Description </h3>
		</div>
		<div id="center">
			<ul> 
				<li class="left-panel"><h4> Group Members </h4></li>
				<li class="right-panel">
					<h1> Activity </h1> 
					<li class="body-divider"> </li>
				
				</li>
			</ul>
		</div>
			<!-- Footer -->
	<div>
		<jsp:include page="footer.jsp" />
	</div>
	</body>
</html>