<%@page import="org.unibl.etf.adminapp.dto.Category" %>
<%@page import="org.unibl.etf.adminapp.dto.Attribute" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="categoryBean" type="org.unibl.etf.adminapp.beans.CategoryBean" scope="session"/>
<jsp:useBean id="attributeBean" type="org.unibl.etf.adminapp.beans.AttributeBean" scope="session"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>ETFBL_IP - Brisanje kategorije</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <link href="css/deletecategory.css" rel="stylesheet">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>
<div class="container-xxl position-relative bg-white d-flex p-0">
    <%@include file="sidebar.jsp" %>

    <div class="content">
        <%@include file="navbar.jsp" %>
        <br>
        <h5><%=session.getAttribute("notification") != null ? session.getAttribute("notification").toString() : ""%>
        </h5>
        <br>
        <%
            String name = session.getAttribute("deleteCategoryName").toString();
            ArrayList<Attribute> attributes = attributeBean.getAllAttributesForSpecificCategory(name);
        %>

        <div class="title-container">
            <h4 class="title">Brisanje kategorije</h4>
        </div>
        <div class="form-container">
            <p>Da li ste sigurni da zelite obrisati ovu kategoriju?</p>
            <br>
            <form method="POST"
                  action="${pageContext.request.contextPath}/hello-servlet?action=delete-category-confirm&category-name=<%=name%>">
                <div class="form-group">
                    <label>Naziv kategorije </label>
                    <input type="text" class="form-control" id="category-name-deleteform"
                           name="category-name-deleteform" value="<%=name%>" readonly disabled>
                </div>
                <br>
                <div class="form-group">
                    <label>Atributi: </label>
                    <%
                        for (Attribute attribute : attributes) {
                    %>
                    <input type="text" class="form-control" name="attribute-name-deleteform"
                           value="<%=attribute.getName() %>" readonly disabled>
                    <br>
                    <%}%>
                </div>
                <div class="text-warning"><small>Kada jednom obrisete kategoriju ona nece moci biti vracena</small>
                </div>
                <div class="delete-button-wrapper">
                    <input type="submit" class="btn btn-danger" value="Obrisi">
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>