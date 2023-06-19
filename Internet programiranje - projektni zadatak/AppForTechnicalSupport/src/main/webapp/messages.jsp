<%@page import="org.unibl.etf.beans.MessageBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="messages" class="org.unibl.etf.services.MessageManager" scope="application"></jsp:useBean>
<jsp:useBean id="adminBean" class="org.unibl.etf.beans.AdminBean" scope="session"></jsp:useBean>
<%
	if(!(adminBean.isLoggedIn())) response.sendRedirect("login.jsp");
	if (request.getParameter("submit") != null) {
		if(request.getParameter("search")!=null && !("".equals(request.getParameter("search"))))  {
			response.sendRedirect("messages.jsp?search-mail-content="+request.getParameter("search"));
		}
	}

%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>ETFBL_IP - Poruke</title>
	<link href="styles/messages.css" type="text/css" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<%@include file="WEB-INF/header.jsp" %>
	<br>
	
	<div class="search-container">
        <form class="serach-form">
            <input type="search" style="width: 100%; font-size:19px" type="search" name="search" id="search" placeholder="Pretrazi">
            <button type="submit" class="search-submit-button" name="submit">Pretrazi</button>
        </form>
    </div>
    <br>
    
	<p> Neprocitane poruke: </p>
	<table style="width:100%; background-color: lightsteelblue;">
	<thead>
		<tr>
		    <th>Posiljalac</th>
		    <th>Tekst poruke</th>
		    <th>Otvorite i odgovorite</th>
	  	</tr>
	</thead>
	<tbody>
	<%
		String search=null;
		if(request.getParameter("search-mail-content")!=null) {
			search=request.getParameter("search-mail-content");
		}
		for(MessageBean mb:messages.getAllMessages(search)) {
			if(!mb.isSeen()) {
	%>
		<tr>
			<td><div class="field-container">
			<%
				out.println(mb.getSenderName()+ " " + mb.getSenderSurname());
			%> 
			</div></td>
			
			<td><div class="field-container">
			<%
				out.println(mb.getContent());
			%> 
			</div></td>
			
			<td><div>
				<a href="see_message.jsp?id=<%=mb.getId()%>"> Pogledaj poruku>>></a>
			</div></td>
		</tr>
	<%
			}
		}
	%>
	</tbody>
	</table>
	<br>
	<br>
	<br>
	
	<p> Procitane poruke: </p>
	<table style="width:100%;">
	<thead>
		<tr>
		    <th>Posiljalac</th>
		    <th>Tekst poruke</th>
		    <th>Otvorite i odgovorite</th>
	  	</tr>
	</thead>
	<tbody>
	<%
		for(MessageBean mb:messages.getAllMessages(search)) {
			if(mb.isSeen()) {
	%>
		<tr>
			<td><div class="field-container">
			<%
				out.println(mb.getSenderName()+ " " + mb.getSenderSurname());
			%> 
			</div></td>
			
			<td><div class="field-container">
			<%
				out.println(mb.getContent());
			%> 
			</div></td>
			
			<td><div>
				<a href="see_message.jsp?id=<%=mb.getId()%>"> Pogledaj poruku>>></a>
			</div></td>
		</tr>
	<%
			}
		}
	%>
	</tbody>
	</table>
	<br>
	
	<%@include file="WEB-INF/footer.jsp" %>
</body>
</html>