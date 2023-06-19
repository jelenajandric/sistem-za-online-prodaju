<%@page import="org.unibl.etf.beans.AdminBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<br>
<br>
	Prijavljeni korisnik: &nbsp;<%=((AdminBean)(session.getAttribute("adminBean"))).getUsername()%>
<br>