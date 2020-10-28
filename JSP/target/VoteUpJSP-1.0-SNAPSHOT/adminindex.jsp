<%@page import="java.sql.ResultSet" %>
<%@page import="com.mysql.cj.protocol.Resultset" %>
<%@page import="java.sql.PreparedStatement" %>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.Connection" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="ie=edge" http-equiv="X-UA-Compatible">
    <title>Admin Index Page</title>
    <link href="https://fonts.googleapis.com/css?family=Karla:400,700&display=swap" rel="stylesheet">
    <link href="https://cdn.materialdesignicons.com/4.8.95/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/signUp.css" rel="stylesheet">
</head>
<body class="jumbotron" style="margin-bottom: -50px;">
<nav class="navbar navbar-light bg-light" style="margin-top: -30px;">
    <a class="navbar-brand"><b><h1>VoteUp</h1></b></a>
    <form class="form-inline">
        <button class="btn btn-outline-danger my-2 my-sm-0" name="result" type="submit"
                onclick="form.action='adminresult.jsp';">Show Result
        </button> &emsp;
        <button class="btn btn-outline-danger my-2 my-sm-0" name="action" type="submit"
                onclick="form.action='adminlogout';">Logout
        </button>
    </form>
</nav>
<div>
    <%
        //To tell browser to not store cache for this page
        response.setHeader("Cache-Control", "no-cache, no-store, must-validate"); //HTTP 1.1
        response.setHeader("Pragma", "no-cache"); //HTTP 1.0
        response.setHeader("Expires", "0"); //Proxy server

        if (session.getAttribute("a_uname") == null)
            response.sendRedirect("adminlogin.jsp");
    %>

    <form action="AddQuestion" method="post"><br> <br>
        <h4> Add Question Text </h4>
        <input class="form-control" id="question" name="question" placeholder="Add Question here"
               type="text">
        <!-- Maximum Votes
        <input class="form-control" id="maxvotes" name="maxvotes" placeholder="Add maximum votes here"
                type="text">
         <input class="btn btn-block login-btn" type="submit" value="Add Question"> --> <br>
        <input class="btn btn-primary btn-lg" name="action" type="submit" value="Add Question"
               style="width: 50% ; align: center; margin-left: 2px;">
        <br><br>

        <label> <font color="red"> ${quesInsertFail} </font></label>
        <label> <font color="green"> ${quesInsertOk} </font> </label>
        <%
            session.removeAttribute("quesInsertOk");
            session.removeAttribute("quesInsertFail");
        %>
    </form>
</div>
</body>
</html>