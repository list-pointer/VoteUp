import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="ie=edge" http-equiv="X-UA-Compatible">
    <title>VoteUp Question Page</title>
    <link href="https://fonts.googleapis.com/css?family=Karla:400,700&display=swap" rel="stylesheet">
    <link href="https://cdn.materialdesignicons.com/4.8.95/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/signUp.css" rel="stylesheet">
</head>
<body class="jumbotron" style="margin-bottom: -50px;">
<nav class="navbar navbar-light bg-light" style="margin-top: -30px;">
    <a class="navbar-brand"><b><h1>VoteUp</h1></b></a>
    <form class="form-inline">
        <button class="btn btn-outline my-2 my-sm-0" name="Welcome" type="button">Welcome ${u_name}</button> &emsp;
        <button class="btn btn-outline-danger my-2 my-sm-0" name="result" type="submit"
                onclick="form.action='userresult.jsp';">Show Result
        </button> &emsp;
        <button class="btn btn-outline-danger my-2 my-sm-0" name="action" type="submit"
                onclick="form.action='userlogout';">Logout
        </button>
    </form>
</nav>
<div>
    <br>
    <h2>Cast Your Vote!</h2>

    <%
        //To tell browser to not store cache for this page
        response.setHeader("Cache-Control", "no-cache, no-store, must-validate"); //HTTP 1.1
        response.setHeader("Pragma", "no-cache"); //HTTP 1.0
        response.setHeader("Expires", "0"); //Proxy server

        if (session.getAttribute("uid") == null)
            response.sendRedirect("userlogin.jsp");
        else {
            if (session.getAttribute("quesNO").equals("0"))
                session.setAttribute("quesNO", "1");
            //else
            //session.setAttribute("quesNO", Integer.toString((Integer.parseInt(session.getAttribute("quesNO").toString())+1)));

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_voteup", "root", "root");
                System.out.println("** Connection has been established **");

                PreparedStatement statement = connection.prepareStatement("select * from questiontable where q_id=" + session.getAttribute("quesNO"));
                ResultSet rs = statement.executeQuery();

                PreparedStatement statement1 = connection.prepareStatement("select count(*) from questiontable");
                ResultSet countrs = statement1.executeQuery();
                countrs.next();
                out.print("<h4 align=\"right\"> Total question = " + countrs.getInt(1) + " </h4>");

                if (!rs.next())
                    out.print("<p class=\"lead\" style=\"margin-top: 30px\"><h2>No Questions available</h2></p>");
                else {
                    do {
    %>
    <p class="lead" style="margin-top: 30px">
    <h2> ${quesNO}. <% out.print(rs.getString(2)); %></h2>    </p>

    <form action="question" method="post">
        <hr class="my-4">
        <input class="btn btn-primary btn-lg" name="action" type="submit" value="YES"
               style="width: 100px;margin-left: 2px;">
        <input class="btn btn-warning btn-lg" name="action" type="submit" value="NO"
               style="width: 100px;margin-left: 50px;">
        <input class="btn btn-secondary btn-lg" name="action" type="submit" value="SKIP"
               style="width: 100px;margin-left: 50px;">
    </form>

    <%
                    } while (rs.next());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    %>
</div>
</body>
</html>