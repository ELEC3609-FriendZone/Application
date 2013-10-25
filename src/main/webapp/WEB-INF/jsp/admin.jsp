<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
								<h2>Use Group Drafter:</h2>
								<a class="button-green" href="/admin/form"> Form Groups </a>
							</li>
						</ul>
					</td>
					<td style="vertical-align: top">
						<ul>
							<li class="right-panel">
								<h1>Administrator</h1>
														
								<h2>Make Project</h2>
								<form method="post" action="/admin/makeProject">
									<table>
									<tr>
										<td>UnitCode</td>
										<td><input type="text" name="ucode"></td>
									</tr>
									<tr>
										<td>Project Name</td>
										<td><input type="text" name="pname"></td>
									</tr>
									<tr>
										<td>Min Group Members:</td>
										<td><input type="text" name="mingm"></td>
									</tr>
									<tr>
										<td>Max Group Members:</td>
										<td><input type="text" name="maxgm"></td>
									</tr>
									<tr>
										<td>Deadline:</td>
										<td><input type="text" name="deadline"></td>
									</tr>
									</table>
									<input type="submit" value="Make Project" class="button-orange" />
								</form>
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