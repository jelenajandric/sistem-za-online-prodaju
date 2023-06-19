<%@page import="org.unibl.etf.beans.MessageBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="messageManager" class="org.unibl.etf.services.MessageManager" scope="application"></jsp:useBean>
<jsp:useBean id="adminBean" class="org.unibl.etf.beans.AdminBean" scope="session"></jsp:useBean>
<%
if(!(adminBean.isLoggedIn())) response.sendRedirect("login.jsp");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>ETFBL_IP - Pregled poruke</title>
	<link href="styles/see-message.css" type="text/css" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<%@include file="WEB-INF/header.jsp" %>
	
	<br>
	<%
		String id = request.getParameter("id");
		MessageBean mess = messageManager.seeMessage(Integer.parseInt(id));
		if(mess==null) {
			out.println("Ne postoji poruka sa id-em poslatim u zahtjevu");
			response.sendRedirect("messages.jsp");
		} else {
	%> 
	<div class="see-message-container">
		<div class="message-wrapper">
			<p style="font-weight:bold"><% out.println("Posiljalac: "); %></p>
			<% out.println(mess.getSenderName() + " " + mess.getSenderSurname() + "<br />"); %>
			
			<p style="font-weight:bold"><% out.println("<br /> Tekst: "); %></p>
			<% out.println(mess.getContent() + "<br />"); %>
		</div>
		<br>
		<a href="reply_message.jsp?id=<%=mess.getId()%>"> Odgovori>>></a>
	</div>
		
	<%
		}
	%>
	
	<br>
	<%@include file="WEB-INF/footer.jsp" %>
</body>
</html>