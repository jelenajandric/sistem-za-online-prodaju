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
    <title>ETFBL_IP - Azuriranje kategorije</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <link href="css/updatecategory.css" rel="stylesheet">

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
        <h5><%=session.getAttribute("notification") != null ? session.getAttribute("notification").toString() : ""%>
        </h5>
        <br>
        <%
            String categoryName = session.getAttribute("old-category-name").toString();
            ArrayList<Attribute> attributes = attributeBean.getAllAttributesForSpecificCategory(categoryName);
        %>

        <div class="title-container">
            <h4 class="title">Azuriraranje kategorije i atributa</h4>
        </div>
        <div class="forms-container">
            <br>
            <p>
            <form method="POST"
                  action="${pageContext.request.contextPath}/hello-servlet?action=update-category-confirm&category-name=<%=categoryName%>">
                <div class="form-group">
                    <label>Naziv kategorije </label>
                    <input type="text" class="form-control" name="category-name-update" value="<%=categoryName%>"
                           required>
                    <input type="submit" class="btn btn-success" value="Sacuvaj">
                    <br>
                </div>

                <label>Atributi: </label>
            </form>
            <%
                for (Attribute attribute : attributes) {
            %>
            <form method="POST"
                  action="${pageContext.request.contextPath}/hello-servlet?action=update-attribute&attribute-name=<%=attribute.getName()%>">
                <div class="form-group">
                    <input type="text" class="form-control" name="attribute-name-update"
                           value="<%=attribute.getName() %>" required>
                    <input type="submit" class="btn btn-success" value="Sacuvaj">
                </div>

                <a href="${pageContext.request.contextPath}/hello-servlet?action=delete-attribute&attribute-name=<%=attribute.getName()%>"
                   class="delete"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                <br>
            </form>
            <%}%>
            <br>

            <form method="POST"
                  action="${pageContext.request.contextPath}/hello-servlet?action=add-attribute-confirm&category-name=<%=categoryName%>&function=update">
                <input class="attribute-name-input" type="text" name="attribute-name" id="attribute-name"
                       placeholder="Unesite naziv atributa" required>
                <br>
                <div class="submit-button-wrapper">
                    <input type="submit" class="btn btn-success" value="Dodaj">
                </div>
                <br>
            </form>
            </p>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>