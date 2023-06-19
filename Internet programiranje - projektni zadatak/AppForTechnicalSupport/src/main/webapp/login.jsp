<%@page import="org.unibl.etf.beans.AdminBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="adminBean" class="org.unibl.etf.beans.AdminBean" scope="session"></jsp:useBean>
<jsp:useBean id="adminManager" class="org.unibl.etf.services.AdminManager" scope="application"></jsp:useBean>
<jsp:setProperty property="username" name="adminBean" param="username" />
<jsp:setProperty property="password" name="adminBean" param="password" />
<!DOCTYPE html>
<%
	if (request.getParameter("submit") != null) {
		AdminBean a = adminManager.login(adminBean.getUsername(), adminBean.getPassword());
		if (a != null) {
			adminBean.setName(a.getName());
			adminBean.setSurname(a.getSurname());
			adminBean.setLoggedIn(true);
			session.setAttribute("notification", "");
			session.setAttribute("adminBean", adminBean);
			response.sendRedirect("messages.jsp");
		} else {
			session.setAttribute("notification", "Unijeli ste neispravno korisni&#269;ko ime ili lozinku.");
			adminBean.setLoggedIn(false);
		}
	} else {
		session.setAttribute("notification", "");
	}
%>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link href="styles/login.css" type="text/css" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>ETFBL_IP - Prijavi se</title>
</head>
<body>
	<div class="login-wrapper">
		<h2>Prijava na sistem</h2>
		<div>
			<form method="POST" action="login.jsp">
				Korisni&#269;ko ime
				<br> 
				<input class="input-data" type="text" name="username" id="username" />
				<br> 
				<br>
				Lozinka 
				<br> 
				<input class="input-data" type="password" name="password" id="password" />
				<br>
				<br>
				<div class="button-wrapper"> 
					<input class="submit-input" type="submit" value="Prijavi se" name="submit" />
				</div>
				<br>
			</form>
		</div>
	</div>
	<div class="notification-wrapper">
		<h3><%=session.getAttribute("notification").toString()%></h3>
	</div>
</body>
</html>