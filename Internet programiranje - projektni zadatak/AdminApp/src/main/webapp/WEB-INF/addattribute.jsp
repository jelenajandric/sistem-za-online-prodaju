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
    <title>ETFBL_IP - Dodavanje atributa</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <link href="css/addattribute.css" rel="stylesheet">

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
            String categoryName = request.getAttribute("addAttributeCategoryName").toString();
            ArrayList<Attribute> attributes = attributeBean.getAllAttributesForSpecificCategory(categoryName);
        %>
        <form method="POST"
              action="${pageContext.request.contextPath}/hello-servlet?action=add-attribute-confirm&category-name=<%=categoryName%>&function=add">
            <div class="title-container">
                <h4 class="title">Dodaj atribute</h4>
            </div>
            <div class="form-container">
                <br>
                <div class="form-group">
                    <label>Naziv kategorije </label>
                    <input type="text" class="form-control" id="category-name-addform" name="category-name-addform"
                           value="<%=categoryName%>" readonly disabled>
                </div>
                <br>
                <div class="form-group">
                    <label>Atributi: </label>
                    <%
                        for (Attribute attribute : attributes) {
                    %>
                    <input type="text" class="form-control" name="attribute-name-addform"
                           value="<%=attribute.getName() %>" readonly disabled>
                    <br>
                    <%}%>
                </div>
                <br>
                <input class="attribute-name-input" type="text" name="attribute-name" id="attribute-name"
                       placeholder="Unesite naziv atributa" required>
                <br>
                <br>
                <input type="submit" class="btn btn-success" value="Dodaj">
            </div>
        </form>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>