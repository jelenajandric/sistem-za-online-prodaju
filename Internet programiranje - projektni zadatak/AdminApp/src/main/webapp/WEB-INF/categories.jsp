<%@page import="org.unibl.etf.adminapp.dto.Category" %>
<%@page import="org.unibl.etf.adminapp.dto.Attribute" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="categoryBean" type="org.unibl.etf.adminapp.beans.CategoryBean" scope="session"/>
<jsp:useBean id="attributeBean" type="org.unibl.etf.adminapp.beans.AttributeBean" scope="session"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>ETFBL_IP - Prikaz kategorija i atributa</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link href="css/categories.css" rel="stylesheet">
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

        <div class="categories-container">
            <%
                for (Category category : categoryBean.getAll()) {
            %>
            <h4>
                Naziv kategorije: <%out.println(category.getName());%>
            </h4>
            Atributi:
            <ul>
                <%
                    for (Attribute attribute : attributeBean.getAllAttributesForSpecificCategory(category.getName())) {

                %>
                <li>
                    <% out.println(attribute.getName());
                    }%>
                </li>
                <a href="${pageContext.request.contextPath}/hello-servlet?action=update-category&category-name=<%=category.getName()%>"
                   class="edit"><i class="material-icons" data-toggle="tooltip" title="Azuriraj"
                                   style="color: darkgoldenrod">&#xE254;</i></a>
                <a href="${pageContext.request.contextPath}/hello-servlet?action=delete-category&category-name=<%=category.getName()%>"
                   class="delete"><i class="material-icons" data-toggle="tooltip" title="Obrisi" style="color: red">&#xE872;</i></a>
                <a href="${pageContext.request.contextPath}/hello-servlet?action=add-attribute&category-name=<%=category.getName()%>"><i
                        class="material-icons" data-toggle="tooltip" title="Dodaj atribute" style="color: green">add</i></a>
            </ul>
            <br>
            <%
                }
            %>

            <br>
            <a href="#addCategoryModal" class="btn btn-success" data-toggle="modal"><i
                    class="material-icons">&#xE147;</i> <span>Dodaj kategoriju</span></a>
            <br>

        </div>

        <!-- Add Modal HTML -->
        <div id="addCategoryModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form method="POST" action="${pageContext.request.contextPath}/hello-servlet?action=add-category">
                        <div class="modal-header">
                            <h4 class="modal-title">Dodaj kategoriju</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Naziv</label>
                                <input type="text" class="form-control" id="category-name" name="category-name"
                                       required>
                            </div>
                            <div style="display: flex; justify-content: space-between;">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Odustani">
                                <input type="submit" class="btn btn-success" value="Dodaj">
                            </div>
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