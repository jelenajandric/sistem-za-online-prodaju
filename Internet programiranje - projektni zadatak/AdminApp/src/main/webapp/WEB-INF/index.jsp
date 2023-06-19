<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>ETFBL_IP - Login</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<div class="container-xxl position-relative bg-white d-flex p-0">
    <div class="container-fluid">
        <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
            <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
                <div class="bg-light rounded p-4 p-sm-5 my-4 mx-3">
                    <div class="d-flex align-items-center justify-content-between mb-3">
                        <h4 class="text-primary"><i class="fa fa-hashtag me-2"></i>ETFBL_IP</h4>
                        <h3>Login</h3>
                    </div>
                    <form method="POST" action="${pageContext.request.contextPath}/hello-servlet?action=login">
                        <div class="form-floating mb-3">
                            <input type="username" class="form-control" id="username" name="username"
                                   placeholder="Korisnicko ime" required>
                            <label for="username">Username</label>
                        </div>
                        <div class="form-floating mb-4">
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="Lozinka" required>
                            <label for="password">Password</label>
                        </div>
                        <button type="submit" class="btn btn-primary py-3 w-100 mb-4">Prijavi se</button>
                        <h3><%=session.getAttribute("notification") != null ? session.getAttribute("notification").toString() : ""%>
                        </h3>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>