<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="ie=edge" http-equiv="X-UA-Compatible">
    <title>VoteUp SignUp</title>
    <link href="https://fonts.googleapis.com/css?family=Karla:400,700&display=swap" rel="stylesheet">
    <link href="https://cdn.materialdesignicons.com/4.8.95/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/signUp.css" rel="stylesheet">
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
<main>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-6 login-section-wrapper">
                <label> <font color="red"> ${insertFailed} </font></label> <br><br>
                <%
                    session.removeAttribute("insertFailed");
                %>
                <div class="login-wrapper my-auto">
                    <h1 class="login-title">Register Here</h1>

                    <form action="userSignup" method="post">
                        <div class="form-group">
                            <label for="uid">UID</label>
                            <input class="form-control" id="uid" name="uid" placeholder="Enter UID"
                                   type="text" minlength=10 maxlength=10 onkeypress="return onlyNumberKey(event)"
                                   required/>
                        </div>
                        <div class="form-group mb-4">
                            <label for="uName">Name</label>
                            <input class="form-control" id="uName" name="uName" placeholder="Enter Name" type="text"
                                   required/>
                        </div>
                        <div class="form-group mb-4">
                            <label for="email">Email</label>
                            <input class="form-control" id="email" name="email" placeholder="Enter Email" type="email"
                                   required/>
                        </div>
                        <div class="form-group mb-4">
                            <label for="password">Password</label>
                            <input class="form-control" id="password" minlength=8 name="password"
                                   placeholder="Enter Password"
                                   type="password" required/>
                        </div>
                        <input class="btn btn-block login-btn" id="login" name="login" type="submit" value="Register"
                               required/>
                    </form>
                    <p class="login-wrapper-footer-text" style="margin-top: 50px;">Already Have an Account? <a
                            class="text-reset"
                            href="userlogin.jsp">Login</a>
                    </p>
                </div>
            </div>
            <div class="col-sm-6 px-0 d-none d-sm-block">
                <img alt="login image" class="login-img" src="assets/images/signup.jpg">
            </div>
        </div>
    </div>
</main>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>