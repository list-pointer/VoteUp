<%@page import="java.sql.ResultSet" %>
<%@page import="com.mysql.cj.protocol.Resultset" %>
<%@page import="java.sql.PreparedStatement" %>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.Connection" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="ie=edge" http-equiv="X-UA-Compatible">
    <title>VoteUp Result Page</title>
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
                onclick="form.action='index.jsp';">Answer Questions
        </button> &emsp;
        <button class="btn btn-outline-danger my-2 my-sm-0" name="action" type="submit"
                onclick="form.action='userlogout';">Logout
        </button>
    </form>
</nav>
<div>
    <!--  	<h1 class="display-4">Cast Your Vote!</h1>  -->
    <%
        //To tell browser to not store cache for this page
        response.setHeader("Cache-Control", "no-cache, no-store, must-validate"); //HTTP 1.1
        response.setHeader("Pragma", "no-cache"); //HTTP 1.0
        response.setHeader("Expires", "0"); //Proxy server

        if (session.getAttribute("uid") == null)
            response.sendRedirect("userlogin.jsp");
        //if(session.getAttribute("quesNO").equals("0"))
        //session.setAttribute("quesNO", "1");
        //else
        //session.setAttribute("quesNO", Integer.toString((Integer.parseInt(session.getAttribute("quesNO").toString())+1)));
    %>
    <br>

    <%
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_voteup", "root", "3569");
            System.out.println("** Connection has been established **");

            PreparedStatement statement = connection.prepareStatement("select * from answertable where u_id=" + session.getAttribute("uid") + " order by q_id");
            ResultSet rs = statement.executeQuery();

            PreparedStatement statement1 = connection.prepareStatement("select count(*) from answertable where u_id=" + session.getAttribute("uid"));
            ResultSet countrs = statement1.executeQuery();
            countrs.next();
            out.print("<h4 align=\"right\"> Total question answered = " + countrs.getInt(1) + " </h4>");

            if (!rs.next())
                out.print("<p class=\"lead\" style=\"margin-top: 30px\"><h2>No Questions Answered</h2></p>");
            else {
                do {
                    ResultSet quesrs = connection.prepareStatement("select question from questiontable where q_id=" + rs.getInt(2)).executeQuery();
                    quesrs.next();

                    ResultSet yesVoteResultSet = connection.prepareStatement("select count(*) from answertable where q_id=" + rs.getInt(2) + " and vote=1").executeQuery();
                    ResultSet noVoteResultSet = connection.prepareStatement("select count(*) from answertable where q_id=" + rs.getInt(2) + " and vote=0").executeQuery();
                    yesVoteResultSet.next();
                    noVoteResultSet.next();
                    int yes = yesVoteResultSet.getInt(1);
                    int no = noVoteResultSet.getInt(1);
                    yesVoteResultSet.close();
                    noVoteResultSet.close();

    %>
    <p class="lead" style="margin-top: 30px">
    <h2><% out.print(rs.getInt(2) + ". " + quesrs.getString(1)); %></h2> </p>
    <p class="lead" style="margin-top: 30px">
    <h2> Your Vote =
        <%
            if (rs.getInt(3) == 1)
                out.print("Yes");
            else if (rs.getInt(3) == 0)
                out.print("No");


            out.print("&nbsp;&nbsp; Overall answer = ");
            if (yes > no) out.print("Yes");
            else if (yes < no) out.print("No");
            else out.print("Tie"); %></h2>   </p>

    <form action="question" method="post">
        <hr class="my-4">

    </form>
    <%
                } while (rs.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
</div>
</body>
</html>