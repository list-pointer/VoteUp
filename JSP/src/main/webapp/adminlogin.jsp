<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta charset="ISO-8859-1">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="ie=edge" http-equiv="X-UA-Compatible">
    <title>VoteUp Login</title>
    <link
            href="https://fonts.googleapis.com/css?family=Karla:400,700&display=swap"
            rel="stylesheet">
    <link
            href="https://cdn.materialdesignicons.com/4.8.95/css/materialdesignicons.min.css"
            rel="stylesheet">
    <link
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
            rel="stylesheet">
    <link href="assets/css/login.css" rel="stylesheet">
    <script>
        function onlyNumberKey(evt) {
            var ASCIICode = (evt.which) ? evt.which : evt.keyCode
            if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57))
                return false;
            return true;
        }
    </script>
</head>

<body>
<main class="d-flex align-items-center min-vh-100 py-3 py-md-0">
    <div class="container">
        <div class="card login-card">
            <div class="row no-gutters">
                <div class="col-md-5">
                    <img alt="login" class="login-card-img" src="assets/images/admin-login.jpg">
                </div>
                <div class="col-md-7">
                    <div class="card-body">
                        <label> <font color="red"> ${wrongCredentials} </font></label> <br><br>
                        <%
                            session.removeAttribute("wrongCredentials");
                        %>
                        <p class="login-card-description">Sign into your account</p>

                        <form action="adminLogin" method="post">
                            <div class="form-group">
                                <label class="sr-only" for="email">Admin ID</label> <input
                                    class="form-control" id="email" name="email"
                                    placeholder="Admin Id" type="text" maxlength=10 onkeypress="return onlyNumberKey(event)">
                            </div>
                            <div class="form-group mb-4">
                                <label class="sr-only" for="password">Password</label> <input
                                    class="form-control" id="password" name="password"
                                    placeholder="***********" type="password">
                            </div>
                            <input class="btn btn-block login-btn" id="login" name="login"
                                   type="submit" value="Login">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>