<%@page import="org.unibl.etf.adminapp.dto.Client" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="clientBean" type="org.unibl.etf.adminapp.beans.ClientBean" scope="session"/>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>ETFBL_IP - Azuriranje korisnika</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <link href="css/updateclient.css" rel="stylesheet">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">


    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
<div class="container-xxl position-relative bg-white d-flex p-0">
    <%@include file="sidebar.jsp" %>

    <div class="content">
        <%@include file="navbar.jsp" %>
        <br>
        <h5<%=session.getAttribute("notification")!=null?session.getAttribute("notification").toString():""%></h5>
        <br>

        <div class="container-fluid pt-4 px-4">
            <div class="row vh-100 bg-light rounded align-items-center justify-content-center mx-0">
                <%
                    String username = session.getAttribute("updateClientUsername").toString();
                    Client client = clientBean.getClientWithUsername(username);
                %>
                <form method="POST"
                      action="${pageContext.request.contextPath}/hello-servlet?action=update-client-confirm&client-username=<%=username%>">
                    <div class="title-container">
                        <h4 class="title">Azuriranje korisnika</h4>
                    </div>
                    <br>
                    <div class="form-wrapper">
                        <p>
                        <div class="form-group">
                            <label>Ime</label>
                            <input type="text" class="form-control" id="client-name-update" name="client-name"
                                   value="<%=client.getName()%>" required>
                        </div>
                        <div class="form-group">
                            <label>Prezime</label>
                            <input type="text" class="form-control" id="client-surname-update" name="client-surname"
                                   value="<%=client.getSurname()%>" required>
                        </div>
                        <div class="form-group">
                            <label>Korisnicko ime</label>
                            <input type="text" class="form-control" id="client-username-update" name="client-username"
                                   value="<%=client.getUsername()%>" required readonly disabled>
                        </div>
                        <div class="form-group">
                            <label>Lozinka</label>
                            <input type="password" class="form-control" id="client-password-update"
                                   name="client-password" value="<%=client.getPassword()%>" required>
                        </div>
                        <div class="form-group">
                            <label>Grad</label>
                            <input type="text" class="form-control" id="client-city-update" name="client-city"
                                   value="<%=client.getCity()%>" required>
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" class="form-control" id="client-email-update" name="client-email"
                                   value="<%=client.getEmail()%>" required>
                        </div>
                        </p>
                    </div>
                    <br>
                    <div class="submit-button-wrapper">
                        <input type="submit" class="btn btn-success" value="Sacuvaj izmjene">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>