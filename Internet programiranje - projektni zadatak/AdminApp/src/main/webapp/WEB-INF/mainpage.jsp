<%@page import="org.unibl.etf.adminapp.dto.Statistic" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="statisticBean" type="org.unibl.etf.adminapp.beans.StatisticBean" scope="session"/>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>ETFBL_IP - Statistika</title>
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
        <div class="container-xl">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-6">
                                <h2>Prikaz logova webshop backend aplikacije</h2>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th>Redni broj</th>
                            <th>Korisnicko ime</th>
                            <th>Akcija</th>
                            <th>Vraceni id</th>
                            <th>Datum</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            int i = 1;
                            for (Statistic statistic : statisticBean.getAll()) {
                        %>
                        <tr>
                            <td><% out.println(i++); %></td>
                            <td><% out.println(statistic.getClientUsername()); %></td>
                            <td><% out.println(statistic.getWhatIsHappened()); %></td>
                            <td><% out.println(statistic.getReturnedId()); %></td>
                            <td><% out.println(statistic.getDate()); %></td
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
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