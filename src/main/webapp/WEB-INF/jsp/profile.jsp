<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>Friendzone-Profile</title>
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
				margin-left:2%;
				margin-right:10%;
			}
			.profile-details {
				background: rgba(260,260, 260, 1);
				margin-left:10%;
				margin-right:2%;
			}
			.user-details{
				margin-top:1%;
				margin-left:10%;
			}
			table{
				width:100%;
			}
			.links{
				text-align:right;
				margin-right:10%;
			}
			.details{
				vertical-align:top;
				margin-right:10%;
				width: 20%;
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
					<div class="user-details">
						<h2>Profile</h2>
						<i><h3>${firstName} ${lastName}</h3></i>
					</div>
				</td>
				<td></td>
			</tr>
			<tr>
				<td class="details">
					<div class="links">
						<a href="/profile/">edit</a>
					</div>			
					<div class="profile-details">
						<h3>Details</h3>
						<table>
							<tr>
								<td>
									<b>Unikey:</b> 
								</td>
								<td>
									${unikey}
								</td>
							</tr>
							<tr>
								<td>
									<b>SID:</b> 
								</td>
								<td>
									${SID}
								</td>
							</tr>
							<tr>
								<td>
									<b>Email:</b>
								</td>
								<td>
									 ${primaryEmail}
								</td>
							</tr>
							<tr>
								<td>
									<b>Mobile:</b> 
								</td>
								<td>
									${mobile}
								</td>
							</tr>
						</table>
					</div>
				</td>
				<td>
					<div class="links">
						<a href="/profile/">edit</a>
					</div>		
					<div class="page-body">
						<h2>Preferences</h2>
						<h3>Study Levels</h3>
						<ul>
							${studyLevels}
						</ul>
						<h3>Role</h3>
						<ul>
							${preferredRole}
						</ul>
						<h3>Languages</h3>
						<ul>
							<c:forEach items="${languages}" var="language">
								<li>${language}</li>
							</c:forEach>
						</ul>
						<h3>Social Media</h3>
						<ul>
							<li>${firstSocialMedia}</li>
							<li>${secondSocialMedia}</li>
						</ul>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>