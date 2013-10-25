<html>
<head>
	<title>Administrator Page</title>
<link rel="stylesheet" type="text/css" href="../../styles/styles.css">
</head>
<body>
	<div id="header-wrapper">
		<table style="width: 100%;">
			<tr>
				<td class="left"> <a href ="/mainHome/"><img src="../images/title.png" alt="Friendzone"></a></td>
				<td style="text-align: right;">
					<a href="/profile/" class="button-small1"> ${Firstname} ${Lastname}</a>
					<a href="/" class="button-small2"> Log out</a>
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
								
							</li>
						</ul>
					</td>
					<td style="vertical-align: top">
						<ul>
							<li class="right-panel">
								<h1>Administrator</h1>
								<h2>All Subjects</h2>
								${unitNames}
								
							</li>
						</ul>
					</td>
				</tr>
			</tbody>
			</table>	
	</div>
	<!-- Footer -->
	<div>
		<jsp:include page="footer.jsp" />
	</div>
</body>

</html>