<%@page import="org.unibl.etf.beans.MessageBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="adminBean" class="org.unibl.etf.beans.AdminBean" scope="session"></jsp:useBean>
<jsp:useBean id="messageManager" class="org.unibl.etf.services.MessageManager" scope="application"></jsp:useBean>

<%
	if(!(adminBean.isLoggedIn())) response.sendRedirect("login.jsp");
%>
        
<!DOCTYPE html>

<%
	if (request.getParameter("submit") != null) {
		if(request.getParameter("reply_content")==null && "".equals(request.getParameter("reply_content"))) {
			session.setAttribute("notification", "Morate unijeti tekst poruke");
		} else {
			messageManager.replyMessage(Integer.parseInt(request.getParameter("id")), request.getParameter("reply_content"));
			response.sendRedirect("messages.jsp");
		}
	} else {
		session.setAttribute("notification", "");
	}
%>
<html>
<head>
	<link href="styles/reply-message.css" type="text/css" rel="stylesheet">
	<meta charset="ISO-8859-1">
	<title>ETFBL_IP - Slanje odgovora klijentu</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<%@include file="WEB-INF/header.jsp" %>
	<br>
	<% 	
		String id = request.getParameter("id");
		MessageBean mess = messageManager.getMessageById(Integer.parseInt(id));
		if(mess==null) {
			out.println("Ne postoji poruka sa id-em poslatim u zahtjevu");
			response.sendRedirect("messages.jsp");
		} else {
	%>
	<div class="reply-message-container">
		<div class="message-wrapper">
			<p> <%out.println("Posiljalac: ");%> </p>
			<% out.println(mess.getSenderName() + " " + mess.getSenderSurname() + "<br />"); %>
			
			<p><% out.println("<br /> Tekst: "); %></p>
			<% out.println(mess.getContent() + "<br />"); %>
			
			<br>
			<form method="POST" action="reply_message.jsp?id=<%=id%>">
				<p> Odgovor: </p>
				<br> 
				<textarea name="reply_content" id="reply_content" rows="4" cols="40"> </textarea>
				<br> <br>
				<input type="submit" value="Posalji" name="submit"/>
				<br>
				<h3><%=session.getAttribute("notification").toString()%></h3>
			</form>
		</div>
	</div>
	
	<%
		}
	%>
	
	<br>
	<%@include file="WEB-INF/footer.jsp" %>
</body>
</html>