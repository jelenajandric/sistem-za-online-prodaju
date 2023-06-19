<%@page import="org.unibl.etf.adminapp.dto.Client" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="clientBean" type="org.unibl.etf.adminapp.beans.ClientBean" scope="session"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>ETFBL_IP - Spisak korisnika</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
<div class="container-xxl position-relative bg-white d-flex p-0">
    <%@include file="sidebar.jsp" %>

    <div class="content">
        <%@include file="navbar.jsp" %>
        <br>
        <h5 style="color: red"><%=session.getAttribute("notification") != null ? session.getAttribute("notification").toString() : ""%>
        </h5>
        <br>
        <div class="container-xl">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-6">
                                <h2>Prikaz <b>Korisnika</b></h2>
                            </div>
                            <div class="col-sm-6">
                                <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i
                                        class="material-icons">&#xE147;</i> <span>Dodaj novog korisnika</span></a>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th>Redni broj</th>
                            <th>Ime</th>
                            <th>Prezime</th>
                            <th>Username</th>
                            <th>Grad</th>
                            <th>Email</th>
                            <th>Aktiviran nalog</th>
                            <th>Opcije</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <%
                                int i = 1;
                                for (Client client : clientBean.getAllClients()) {
                            %>

                            <td><% out.println(i++); %></td>
                            <td><% out.println(client.getName()); %></td>
                            <td><% out.println(client.getSurname()); %></td>
                            <td><% out.println(client.getUsername()); %></td>
                            <td><% out.println(client.getCity()); %></td>
                            <td><% out.println(client.getEmail()); %></td>
                            <td><% out.println(client.isActivated() == true ? "DA" : "NE"); %></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/hello-servlet?action=update-client&client-username=<%=client.getUsername()%>"
                                   class="edit"><i class="material-icons" data-toggle="tooltip"
                                                   title="Edit">&#xE254;</i></a>
                                <a href="${pageContext.request.contextPath}/hello-servlet?action=delete-client&client-username=<%=client.getUsername()%>"
                                   class="delete"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Add Modal HTML -->
        <div id="addEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form method="POST" action="${pageContext.request.contextPath}/hello-servlet?action=add-client">
                        <div class="modal-header">
                            <h4 class="modal-title">Dodaj korisnika</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Ime</label>
                                <input type="text" class="form-control" id="client-name" name="client-name" required>
                            </div>
                            <div class="form-group">
                                <label>Prezime</label>
                                <input type="text" class="form-control" id="client-surname" name="client-surname"
                                       required>
                            </div>
                            <div class="form-group">
                                <label>Korisnicko ime</label>
                                <input type="text" class="form-control" id="client-username" name="client-username"
                                       required>
                            </div>
                            <div class="form-group">
                                <label>Lozinka</label>
                                <input type="password" class="form-control" id="client-password" name="client-password"
                                       required>
                            </div>
                            <div class="form-group">
                                <label>Grad</label>
                                <input type="text" class="form-control" id="client-city" name="client-city" required>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input type="email" class="form-control" id="client-email" name="client-email" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Odustani">
                            <input type="submit" class="btn btn-success" value="Dodaj">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/main.js"></script>

</body>
</html>