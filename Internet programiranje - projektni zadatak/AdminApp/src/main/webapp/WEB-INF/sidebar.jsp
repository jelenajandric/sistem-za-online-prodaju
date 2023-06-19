<jsp:useBean id="adminBean" type="org.unibl.etf.adminapp.beans.AdminBean" scope="session"/>
<html>
<head>
    <meta charset="utf-8">
    <title>ETFBL_IP</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet"/>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <script src="js/main.js"></script>

</head>
<body>
<div class="sidebar pe-4 pb-3">
    <nav class="navbar bg-light navbar-light">
        <a href="${pageContext.request.contextPath}/hello-servlet?action=statistics" class="navbar-brand mx-4 mb-3">
            <h3 class="text-primary"><i class="fa fa-hashtag me-2"></i>ETFBL_IP</h3>
        </a>
        <div class="d-flex align-items-center ms-4 mb-4">
            <div class="position-relative">
                <img class="rounded-circle" src="img/default-avatar.jpg" alt="" style="width: 40px; height: 40px;">
                <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
            </div>
            <div class="ms-3">
                <h6 class="mb-0"><% out.println(adminBean.getAdmin().getName() + " " + adminBean.getAdmin().getSurname()); %></h6>
                <span>Admin</span>
            </div>
        </div>
        <div class="navbar-nav w-100">
            <a href="${pageContext.request.contextPath}/hello-servlet?action=statistics"
               class="nav-item nav-link"><i class="fa fa-bar-chart me-2"></i>Statistika</a>
            <a href="${pageContext.request.contextPath}/hello-servlet?action=categories"
               class="nav-item nav-link"><i class="fa fa-th me-2"></i>Kategorije</a>
            <a href="${pageContext.request.contextPath}/hello-servlet?action=appusers"
               class="nav-item nav-link"><i class="fa fa-table me-2"></i>Korisnici</a>
        </div>
    </nav>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
